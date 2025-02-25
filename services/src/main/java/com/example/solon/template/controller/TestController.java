package com.example.solon.template.controller;

import cn.hutool.core.io.FileUtil;
import cn.xbatis.core.sql.executor.chain.QueryChain;
import com.example.solon.template.common.aop.TokenValidator;
import com.example.solon.template.dao.entity.User;
import com.example.solon.template.dao.mapper.UserMapper;
import com.example.solon.template.model.common.response.Paging;
import com.example.solon.template.model.common.response.ResponseResult;
import com.example.solon.template.model.ro.SearchRo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.handle.UploadedFile;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

import java.io.IOException;

@Slf4j
@Api(tags = "一些基础demo接口")
@Valid
@Mapping("/test")
@Controller
public class TestController {

    @Inject
    private UserMapper userMapper;

    //    @NoRepeatSubmit  //重复提交验证
//    @Whitelist     //白名单验证
    @ApiOperation(value = "测试get")
    @Mapping(value = "/get-user", method = MethodType.GET)
    public ResponseResult<User> hello(@Param(name = "id", required = true) Long id) {
        return ResponseResult.success(userMapper.getById(id));
    }

    @ApiOperation(value = "测试aop")
    @Get
    @Mapping("/aop")
    @TokenValidator
    public ResponseResult<Void> aop() {
        return ResponseResult.success();
    }

    @ApiOperation(value = "添加user")
    @Post
    @Mapping("/save")
    public ResponseResult<Void> save(@Validated @Body User user) {
        userMapper.save(user);

        return ResponseResult.success();
    }

    @ApiOperation(value = "查询用户列表")
    @Post
    @Mapping("/list")
    public ResponseResult<Paging<User>> list(@Validated @Body SearchRo ro) {
        return ResponseResult.success(Paging.of(
                QueryChain.of(userMapper)
                        .forSearch()
                        .like(User::getName, ro.getKeyword())
                        .paging(ro.getPager())));
    }

    @ApiOperation(value = "测试异常")
    @Post
    @Mapping("/test-error")
    public ResponseResult<Void> error(@Validated @Body User user,
                                      @NotNull(message = "tag不能为null") @Param(value = "tag") String tag) {

        // 📢注意Solon的验证必须要Content-type严格，否则无法正确反序列化Body,验证会失败
//        throw new RuntimeException("asdf");
        return ResponseResult.success();
    }

    @ApiOperation(value = "测试302")
    @Get
    @Mapping("/test-302")
    public void t302() {

        Context.current().redirect("https://spring.org");
    }

    /**
     * 可用 UploadedFile[] file 多文件上传
     * <p>
     * 可用通过 multipart 提交的数据，且不带 UploadedFile 参数；须加 multipart 申明
     * 可以通过Context.file("file")或者.files("file")获取文件
     * <p>
     * 严格建议使用UploadedFile参数，为了安全性关掉ctx.autoMultipart后，后一种方式不能使用
     * <p>
     * 文件下载 方法返回DownloadedFile 或者返回File 或者直接输出到ctx.outputAsFile(file) 即可，
     * 如 return new DownloadedFile("text/json", bytes, "test.txt");
     */
    @ApiOperation(value = "文件上传")
    @Mapping(value = "/upload", method = MethodType.POST)
    public ResponseResult<String> upload(@Param("file") UploadedFile file) { //表单变量名要跟参数名对上
        try {
            //把它转为本地文件
            file.transferTo(FileUtil.createTempFile("test", "logo.jpg", true));
            //用完之后，删除"可能的"临时文件 //v2.7.2 后支持
            /*
             * 如果过滤器中添加了
             * if (ctx.isMultipartFormData()) {
             *   ctx.filesDelete(); //v2.7.3 后支持
             * }
             * 则不需要手动删除
             */
            file.delete();
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }

        return ResponseResult.success(file.getName());
    }
}
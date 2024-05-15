package com.example.solon.template.controller;

import com.example.solon.template.common.aop.TokenValidator;
import com.example.solon.template.common.response.ResponseResult;
import com.example.solon.template.dao.entity.User;
import com.example.solon.template.dao.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

@Valid
@Api(tags = "一些基础demo接口")
@Controller
public class TestController {

    @Inject
    private UserMapper userMapper;

//    @NoRepeatSubmit  //重复提交验证
//    @Whitelist     //白名单验证
    @ApiOperation(value = "测试get")
    @Mapping(value = "/get-user", method = MethodType.GET)
    public ResponseResult<User> hello(@Param(required = true) Long id) {
        return ResponseResult.success(userMapper.selectByPrimaryKey(1));
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
        userMapper.insert(user);

        return ResponseResult.success();
    }
}
package com.example.solon.template.dao.entity;

import cn.xbatis.db.annotations.LogicDelete;
import cn.xbatis.db.annotations.Table;
import cn.xbatis.db.annotations.TableField;
import cn.xbatis.db.annotations.TableId;
import lombok.Data;
import org.noear.snack.core.utils.DateUtil;
import org.noear.solon.validation.annotation.Max;
import org.noear.solon.validation.annotation.Min;
import org.noear.solon.validation.annotation.NotNull;

import java.util.Date;

@Data
@Table("user")
public class User {

    @TableId

    private Long id;

    @TableField
    @NotNull(message = "名字必须指定")
    private String name;

    @Max(value = 150, message = "最大岁数为150岁")
    @Min(0)
    private Integer age;

    @TableField("create_time")
    private Date createTime;

    @LogicDelete
    @TableField("is_del")
    private Boolean isDel;

    @Override
    public String toString() {

        return String.format("id:%d, name:%s, age:%d, createTime:%s", id, name, age,
                createTime != null ? DateUtil.format(createTime, DateUtil.FORMAT_19_b) : null);
    }
}
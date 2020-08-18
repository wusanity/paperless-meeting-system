package com.szsm.videomeeting.base.context;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 *  <p> 修改时间 </p>
 *
 * @description:
 * @author: wuzhike
 * @date: 2020/7/30 0018 1:30
 */
@Getter
@Setter
public abstract class BaseEntity<T extends Model> extends Model {


    @ApiModelProperty(value = "创建日期")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Past(message = "创建时间必须是过去时间")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Integer createBy;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
//    @Future(message = "修改时间必须是将来时间")
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;

    /**
     * 是否删除：0未删除，1已删除
     */

    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;
}

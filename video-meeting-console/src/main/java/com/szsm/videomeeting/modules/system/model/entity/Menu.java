package com.szsm.videomeeting.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szsm.videomeeting.base.context.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>  系统管理-权限权限表  </p>
 *
 * @author: wuzhike
 * @date: 2020-07-30
 */
@Data
@ApiModel(description = "系统管理-权限表 ")
@TableName("t_sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@ApiModelProperty(value = "主键")
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	/**
	 * url
	 */
	@ApiModelProperty(value = "url")
	@TableField("url")
	private String url;

	/*@Override
	protected Serializable pkVal() {
		return this.id;
	}*/

}

package com.szsm.videomeeting.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szsm.videomeeting.base.context.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>  系统管理-用户基础信息表 </p>
 *
 * @author: wuzhike
 * @date: 2020-07-30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(description = "系统管理-用户基础信息表")
@TableName("t_sys_user")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 账号
     */
	@ApiModelProperty(value = "账号")
	@TableField("username")
	private String username;
    /**
     * 登录密码
     */
	@ApiModelProperty(value = "登录密码")
	@TableField("password")
	private String password;
    /**
     * 昵称
     */
	@ApiModelProperty(value = "昵称")
	@TableField("nick_name")
	private String nickName;
    /**
     * 盐值
     */
    @ApiModelProperty(value = "盐值")
    @TableField("salt")
    private String salt;
    /**
     * token
     */
	@ApiModelProperty(value = "token")
	@TableField("token")
	private String token;

	@TableField(exist = false)
	private List<UserRole> userRoleList;

	/*@Override
	protected Serializable pkVal() {
		return this.id;
	}*/

}

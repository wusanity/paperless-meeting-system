package com.szsm.videomeeting.modules.meeting.model.entity;

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

/**
 *文件表实体
 * @author LiuJun
 * @date 2020-08-20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(description = "文件信息表")
@TableName("file_info")
public class FileInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "会议议程id")
    @TableField("p_id")
    private String pId;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @TableField("file_Name")
    private String fileName;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "保存至服务器的名称")
    @TableField("save_File_Name")
    private String saveFileName;

    /**
     * 文件存储路径
     */
    @ApiModelProperty(value = "文件存储路径")
    @TableField("file_Path")
    private String filePath;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    @TableField("file_Size")
    private String fileSize;
}

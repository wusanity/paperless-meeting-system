package com.szsm.videomeeting.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 会议人员excel模板实体
 * @author: LiuJun
 * @date: 2020/8/21 15:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingMembersExcel extends BaseRowModel {
    /**
     * value: 表头名称
     * index: 列的号, 0表示第一列
     */
    @ExcelProperty(value = "序号", index = 0)
    private int index;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "职位",index = 2)
    private String tittle;

    @ExcelProperty(value = "设备",index = 3)
    private String equipment ;

    @ExcelProperty(value = "设备状态",index = 4)
    private String equipmentStatus ;

    @ExcelProperty(value = "关联设备",index = 5)
    private String attachedEquipment ;
}

package com.szsm.videomeeting.base.config.netty.dto;

import lombok.Data;



/**
 * @Author: huangxin
 * @Date: Created in 下午10:23 2019/1/1
 * @Description:
 */
@Data
public class BaseDataBody {


    /**
     * 是否有数据
     */
    private boolean flag;

    BaseDataBody(boolean flag){
        this.flag = flag;
    }

    /**
     * 根据协议号拿到数据体详情
     *
     * @param operateType 数据操作类型
     * @return 具体数据体
     */
    public static BaseDataBody loadBodyInfo(Integer operateType) {
        switch (operateType) {
            case DataContext.HEART_BEAT_PACKAGE:
                return new BaseDataBody(true);
            case DataContext.JOIN_PACKAGE:
                return new BaseDTO();
            default:
                return null;
        }
    }

}

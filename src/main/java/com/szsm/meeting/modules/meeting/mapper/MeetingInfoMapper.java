package com.szsm.meeting.modules.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.model.entity.MeetingInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 11:38
 * @Description:
 */
/*@Component*/
public interface MeetingInfoMapper extends BaseMapper<MeetingInfo> {

    List<MeetingInfo> getList(Page<MeetingInfo> page, @Param("filter") MeetingInfoDTO meetingInfoDTO);
}

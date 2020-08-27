package com.szsm.meeting.modules.kk.model.vo;

import com.szsm.meeting.base.context.BasePageQuery;
import com.szsm.meeting.base.context.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonVO extends BasePageQuery {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}

package com.szsm.videomeeting.model.dto;

import com.szsm.videomeeting.base.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO extends BasePageQuery {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}

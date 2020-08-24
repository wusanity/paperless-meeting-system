package com.szsm.videomeeting.base.config.netty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRequiredDTO extends ResponseContext{

    /**
     * 文件路径
     */
    List<String> url;
}

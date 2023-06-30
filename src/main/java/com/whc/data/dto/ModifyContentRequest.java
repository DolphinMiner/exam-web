package com.whc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-30 12:59
 * @Description:
 */
@Data
@NoArgsConstructor
public class ModifyContentRequest {
    @JsonProperty("prompt")
    private String prompt;
}

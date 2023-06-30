package com.whc.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-30 13:00
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyContentResponse {
    private int code;
    private String message;
    private String answer;
}

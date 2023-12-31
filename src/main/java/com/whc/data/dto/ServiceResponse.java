package com.whc.data.dto;

import com.whc.data.entity.DemoData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 14:33
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private int code;
    private String message;
    private List<DemoData> data;
}

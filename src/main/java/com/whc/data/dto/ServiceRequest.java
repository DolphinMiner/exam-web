package com.whc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceRequest {
    @JsonProperty("id")
    private long id;
    @JsonProperty("col_a")
    private String colA;
    @JsonProperty("col_b")
    private boolean colB;
    @JsonProperty("col_c")
    private String colC;
}

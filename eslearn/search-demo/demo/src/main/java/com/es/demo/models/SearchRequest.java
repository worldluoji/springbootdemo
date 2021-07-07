package com.es.demo.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    @NotBlank(message = "input can not be null")
    private String keyword;
}

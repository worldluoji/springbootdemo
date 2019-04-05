package com.onlinecoffe.springonlinecoffee.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class NewOrderRequest {
    @NotEmpty
    private List<String> items;
    @NotNull
    private String customer;
}

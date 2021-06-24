package com.example.detail.models;


import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Student {
    @Size(max=16)
    private String name;
    private short age;
}

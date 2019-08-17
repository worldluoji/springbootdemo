package com.springboot.logindemo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String userId;
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotNull
    private boolean support;
    @NotEmpty
    private String password;
}

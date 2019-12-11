package com.abc.springsession.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Entity
@ApiModel
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    @ApiModelProperty(value = "unique Id for each employee")
    private Integer id;
    @NotBlank
    @ApiModelProperty(value = "employee's first name")
    private String firstName;
    @NotBlank
    @ApiModelProperty(value = "employee's last name")
    private String lastName;
    @NotBlank
    @ApiModelProperty(value = "employee's email")
    private String email;
    @NotBlank
    @ApiModelProperty(value = "password")
    private String password;
}

package com.tts.UsersAPI.model;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty (notes = "The user id")
    private String id;

    @NotNull
    @Size(max = 20)
    @ApiModelProperty (notes = "The first name of the user")
    private String firstName;

    @NotNull
    @Size(min = 2)
    @ApiModelProperty (notes = "The last name of the user")
    private String lastName;

    @NotNull
    @Size(min = 4, max = 20)
    @ApiModelProperty (notes = "The state the user lives in")
    private String state;
}

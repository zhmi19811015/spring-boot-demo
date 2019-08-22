package com.ming.springbootjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by echisan on 2018/6/23
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser {

    private String username;
    private String password;
    private Integer rememberMe;

}

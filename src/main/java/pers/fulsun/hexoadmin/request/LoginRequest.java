package pers.fulsun.hexoadmin.request;

import lombok.Data;

@Data
public class LoginRequest extends BaseRequest {
    private String username;
    private String password;
}
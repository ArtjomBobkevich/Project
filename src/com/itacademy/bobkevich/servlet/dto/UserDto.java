package com.itacademy.bobkevich.servlet.dto;

import com.itacademy.bobkevich.servlet.entity.PersonRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String login;
    private String password;
    private String personRole;

    @Override  /*тут намутил*/
    public String toString() {
        return  login;
    }
}

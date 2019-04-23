package com.itacademy.bobkevich.servlet.dto;

import com.itacademy.bobkevich.servlet.entity.PersonRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewPersonDto {

    private String login;
    private String first_name;
    private String last_name;
    private String age;
    private String mail;
    private String password;
    private PersonRole personRole;
}

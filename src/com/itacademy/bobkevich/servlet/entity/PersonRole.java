package com.itacademy.bobkevich.servlet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class PersonRole {

    private Long id;
    private String nameOfRole;
}

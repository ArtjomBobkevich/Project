package com.itacademy.bobkevich.servlet.dto;

import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.Person;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewResourceFullInfoDto {

    private Long id;
    private String resourceName;
    private String typeFile;
    private String category;
    private String person;
    private String url;
    private String  size;
}

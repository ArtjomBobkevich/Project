package com.itacademy.bobkevich.servlet.dto;

import com.itacademy.bobkevich.servlet.entity.Category;
import com.itacademy.bobkevich.servlet.entity.TypeFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewResourceBasicInfoDto {

    private Long id;
    private String resourceName;
    private String typeFile;
    private String category;

    @Override
    public String toString() {
        return
                "Title= " + resourceName + System.lineSeparator() +
                "typeFile= " + typeFile + System.lineSeparator() +
                "category= " + category + System.lineSeparator() +
                        System.lineSeparator();
    }
}

package com.itacademy.bobkevich.servlet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Resource {

    private Long id;
    private String resourceName;
    private TypeFile typeFile;
    private Category category;
    private Person person;
    private String url;
    private String size;
}

package com.itacademy.bobkevich.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewCommentFullINfoDto {

    private Long id;
    private String resource_id;
    private String text;
}

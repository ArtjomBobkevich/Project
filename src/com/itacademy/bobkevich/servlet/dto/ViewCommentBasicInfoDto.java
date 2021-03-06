package com.itacademy.bobkevich.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewCommentBasicInfoDto {

    private Long id;
    private String text;
}

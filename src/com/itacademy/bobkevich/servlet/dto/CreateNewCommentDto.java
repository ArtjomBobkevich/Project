package com.itacademy.bobkevich.servlet.dto;

import com.itacademy.bobkevich.servlet.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewCommentDto {

    private Long id;
    private Resource resourceId;
    private String text;
}

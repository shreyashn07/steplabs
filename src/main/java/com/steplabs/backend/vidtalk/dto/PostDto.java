package com.steplabs.backend.vidtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostDto {

    Long postId;
    String title;
    String description;
    String content;
    Long userProfID;
    String profilePicUrl;


}

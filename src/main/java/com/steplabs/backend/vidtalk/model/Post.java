package com.steplabs.backend.vidtalk.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SqlResultSetMapping(
        name = "findAllDataMapping",
        classes = @ConstructorResult(
                targetClass = com.steplabs.backend.vidtalk.dto.PostDto.class,
                columns = {
                        @ColumnResult(name = "id",type=Long.class),
                        @ColumnResult(name = "title",type= String.class),
                        @ColumnResult(name = "description",type=String.class),
                        @ColumnResult(name = "content",type=String.class),
                        @ColumnResult(name = "userId",type=Long.class),
                        @ColumnResult(name = "profile_Pic_Url",type=String.class)
                }
        )
)


@Data
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "This is the auto generated post id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    @ApiModelProperty(notes = "Heading or title of the post")
    private String title;

    @NotNull
    @Size(max = 250)
    @ApiModelProperty(notes = "The main text content of the post")
    private String description;

    @NotNull
    @Lob
    @ApiModelProperty(notes = "The url of the content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_prof_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("user_prof_id")
    @ApiModelProperty(notes = "Userid of the person who posted the post")
    private UserProfile userProfile;



}


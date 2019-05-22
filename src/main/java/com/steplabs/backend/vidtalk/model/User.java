package com.steplabs.backend.vidtalk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.sound.sampled.spi.AudioFileReader;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "User")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Automatically generated id of the user")
    private Long Id;

    @NonNull
    @Size(max=30)
    @ApiModelProperty(notes = "Email of the user for logging in")
    private String email;

    @NotNull
    @Size(max=200)
    @ApiModelProperty(notes = "Fcmid of the user mobile device")
    private String fcmId;

    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "Roles that user is going to have")
    List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    @ApiModelProperty(notes = "Variable linking the user with his user profile")
    private UserProfile userProfile;




}

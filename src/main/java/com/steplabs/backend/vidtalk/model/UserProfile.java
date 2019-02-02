package com.steplabs.backend.vidtalk.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name="Userprofile")
public class UserProfile extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Size(max = 250)
    private String about;

    @NotNull
    @Size(max =200)
    private String profilePicUrl;

    @NotNull
    private boolean gender;

    @NotNull
    @Size(max = 50)
    private String location;

    @NotNull
    @Size(max = 100)
    private String interests;

    @NotNull
    @Size(max = 100 )
    private String languages;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "userProfile",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Post> posts= new ArrayList();




}

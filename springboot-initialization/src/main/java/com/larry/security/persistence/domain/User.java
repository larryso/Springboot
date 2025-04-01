package com.larry.security.persistence.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;



@Entity
@Table(name = "USER_PROFILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @Column(name = "USER_PK")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    private Long userPK;

    @NaturalId
    @Column(name = "USER_ID", updatable = false, nullable = false, unique = true)
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

}

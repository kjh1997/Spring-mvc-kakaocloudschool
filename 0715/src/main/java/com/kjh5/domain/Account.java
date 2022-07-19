package com.kjh5.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public boolean isValidToken(String token){
        return this.emailCheckToken.equals(token);
    };
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    private String password;
    private boolean emailVerified;
    private LocalDateTime joinedAt;
    private String emailCheckToken;
    private String bio;
    private String url;
    private String occupation;
    private String location;
    @Lob @Basic(fetch = FetchType.EAGER)
    @Column(length=2000)
    private String profileImage;

    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb;
    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;
    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;
    private LocalDateTime emailCheckTokenGeneratedAt;
    public void completeSignUp() {

        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }
    public void generateEmailCheckToken() {
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
        this.emailCheckToken = UUID.randomUUID().toString();
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}

package com.kjh5.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh5.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Transactional
public class Profile {
    @Length(max = 35)
    private String bio;

    @Length(max = 50)
    private String url;

    @Length(max = 50)
    private String occupation;

    @Length(max = 50)
    private String location;

    private String profileImage;

    public Profile(Account account) {
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.location = account.getLocation();
        this.occupation = account.getOccupation();
        this.occupation = account.getProfileImage();

    }


}

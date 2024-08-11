package org.acme.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRsp {
    @JsonProperty("sub")
    private String sub;
    private String name;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("email_verified")
    private String emailVerified;
    @JsonProperty("preferred_username")
    private String preferredUsername;
}

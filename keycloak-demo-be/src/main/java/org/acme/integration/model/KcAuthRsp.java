package org.acme.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KcAuthRsp {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Integer expireIn;
    @JsonProperty("refresh_expires_in")
    private Integer refreshExpireIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("not-before-policy")
    private Integer notBeforePolicy;
    @JsonProperty("session_state")
    private String sessionState;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("id_token")
    private String idToken;
}

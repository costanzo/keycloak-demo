package org.acme.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonProperty("name")
    private String name;
    @JsonProperty("given_name")
    private String firstName;
    @JsonProperty("family_name")
    private String lastName;
    private String email;
    private String token;
}

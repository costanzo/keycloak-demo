package org.acme.service;

import org.acme.integration.model.KcAuthRsp;
import org.jboss.logging.Logger;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.integration.KeycloakService;
import org.acme.service.model.AuthorizationCodeReq;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthResource {
    private static final Logger LOGGER = Logger.getLogger(AuthResource.class);
    @Inject
    @RestClient
    KeycloakService keycloakService;

    @POST
    @Path("/oauth2/authorization_code")
    public Object oauth2(AuthorizationCodeReq req) {
        LOGGER.info("req: "+req);
        KcAuthRsp response = keycloakService.getTokenByCode("authorization_code", req.getCode(), System.getProperty("kc.client.id"), System.getProperty("kc.client.secret"));
        LOGGER.info("response: "+response);
        return new User("Shuyi", "Sun", "abc@123.com", "tempToken");
    }

    public static class User {
        String lastName;
        String firstName;
        String email;
        String token;

        public User(String lastName, String firstName, String email, String token) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.email = email;
            this.token = token;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "User{" +
                    "lastName='" + lastName + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", email='" + email + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}

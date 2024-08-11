package org.acme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.WebApplicationException;
import org.acme.integration.model.KcAuthRsp;
import org.acme.integration.model.UserInfoRsp;
import org.acme.service.model.SessionState;
import org.acme.service.model.User;
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

import static org.acme.utils.JWTUtils.parseIdToken;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AuthResource {
    private static final Logger LOGGER = Logger.getLogger(AuthResource.class);

    private static String accessToken = "";

    @Inject
    @RestClient
    KeycloakService keycloakService;

    @POST
    @Path("/oauth2/authorization_code")
    public Object oauth2(AuthorizationCodeReq req) throws Exception{
        LOGGER.info("req: "+req);
        KcAuthRsp response = keycloakService.getTokenByCode(
                "authorization_code",
                req.getCode(),
                System.getProperty("kc.client.id"),
                System.getProperty("kc.client.secret"),
                "http://localhost:3000/auth/callback");
        accessToken = response.getAccessToken();
        LOGGER.info("response: "+response);
        User user = parseIdToken(response.getIdToken());
        user.setToken(response.getIdToken());
        LOGGER.info("user: "+user);
        return user;
    }

    @GET
    @Path("/oauth2/session")
    public Object session() {
        try {
            UserInfoRsp response = keycloakService.getUserInfo("Bearer " + accessToken);
            LOGGER.info("response: " + response);
            return new SessionState();
        } catch (WebApplicationException e) {
            LOGGER.error("Error: ", e);
            if (e.getResponse().getStatus() == 401) {
                return new SessionState(false);
            }
        } catch (Exception e) {
            LOGGER.error("Error: ", e);
        }
        return new SessionState();
    }
}

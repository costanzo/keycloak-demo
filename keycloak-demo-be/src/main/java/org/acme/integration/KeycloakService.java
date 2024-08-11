package org.acme.integration;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.acme.integration.model.KcAuthRsp;
import org.acme.integration.model.UserInfoRsp;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
@Path("/realms/demo/protocol/openid-connect")
@RegisterRestClient
public interface KeycloakService {
    @Path("/token")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    KcAuthRsp getTokenByCode(@FormParam("grant_type") String grantType,
                             @FormParam("code") String code,
                             @FormParam("client_id") String clientId,
                             @FormParam("client_secret") String clientSecret,
                             @FormParam("redirect_uri") String redirectUri
                             );

    @Path("/userinfo")
    @GET
    UserInfoRsp getUserInfo(@HeaderParam("Authorization") String accessToken);
}

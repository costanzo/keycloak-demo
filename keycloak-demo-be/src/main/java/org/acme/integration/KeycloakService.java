package org.acme.integration;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.acme.integration.model.KcAuthRsp;
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
                             @FormParam("client_secret") String clientSecret);
}

package org.acme.service.model;

public class AuthorizationCodeReq {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AuthorizationCodeReq{" +
                "code='" + code + '\'' +
                '}';
    }
}

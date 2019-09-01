package com.nexters.wiw.strolling_of_time.dto;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

public class LoginRequestDto {
    @NotNull
    public static String basicAuthHeaderOf(String email, String password){
        String basicAuth = String.format("%s:%s", email, password);
        String base64 = Base64.encodeBase64String(basicAuth.getBytes());
        return "Basic "+base64;
    }
}

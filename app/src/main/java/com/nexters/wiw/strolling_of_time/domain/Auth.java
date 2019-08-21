package com.nexters.wiw.strolling_of_time.domain;

import org.apache.commons.codec.binary.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auth {
  private String token;
  private String tokenType;
  private long expiresIn;

  public static String basicAuthHeaderOf(String email, String password){
    String basicAuth = String.format("%s:%s", email, password);
    String base64 = Base64.encodeBase64String(basicAuth.getBytes());
    return "Basic "+base64;
  }
}

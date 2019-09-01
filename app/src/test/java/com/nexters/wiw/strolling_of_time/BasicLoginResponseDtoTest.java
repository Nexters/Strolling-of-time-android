package com.nexters.wiw.strolling_of_time;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class BasicLoginResponseDtoTest {
  private String email = "test@test.test";
  private String password = "testtest";

  @Test
  public void basic_auth_인코딩_디코딩_테스트(){
    byte[] result;
    String auth = basicAuthHeaderOf(email, password);
    assert Base64.isBase64(auth);
  }
  private String basicAuthHeaderOf(String email, String password){
    String basicAuth = String.format("%s:%s", email, password);
    return "Basic " + Base64.encodeBase64String(basicAuth.getBytes());
  }
}

package com.nexters.wiw.strolling_of_time;

import com.nexters.wiw.strolling_of_time.dto.LoginResponseDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpResponseDto;

import java.util.Optional;

import retrofit2.Response;

abstract class RetrofitResponseMixin {
    private static final String METHOD_URL_STATUS_CODE = "%s: %s: %d";

    static void printResponseDetail(Response response) throws Throwable {
        String requestURL = String.valueOf(response.raw().request().url());
        String httpMethod = response.raw().request().method();
        int code = getStatusCode(response);
        System.out.println(String.format(METHOD_URL_STATUS_CODE, httpMethod, requestURL, code));
    }
    private static int getStatusCode(Response response) throws Throwable {
        return Optional.of(response)
                .filter(Response::isSuccessful)
                .map(Response::code)
                .orElseThrow(Exception::new);
    }
    static String getToken(Response<LoginResponseDto> response) throws Throwable{
        return "Bearer " + Optional.of(response)
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(LoginResponseDto::getToken)
                .orElseThrow(Exception::new);
    }
    static long getUserId(Response<SignUpResponseDto> response) throws Throwable{
        return Optional.of(response)
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(SignUpResponseDto::getId)
                .orElseThrow(Exception::new);
    }
}

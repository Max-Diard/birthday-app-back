package com.tp.birthdayapp.util;

import org.springframework.http.HttpHeaders;

public class Utils {

    public static HttpHeaders setErrorHeadersRequestResponse(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("errorMessage", e.getMessage());
        return responseHeaders;
    }
}

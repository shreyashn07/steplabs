package com.steplabs.backend.vidtalk;


import lombok.Data;

@Data
public class RestResponse<T> {

    private boolean success;
    private int statusCode;
    private T data;
    private String error;
    public static final int STATUS_SUCCESS_OK = 200;
    public static final int STATUS_NOT_AVAILABLE = -1;

    private RestResponse() {
    }


    public static <T> RestResponse<T> createSuccessResponse(T data) {
        RestResponse<T> res = new RestResponse();
        res.data = data;
        res.success = true;
        res.statusCode = 200;
        return res;
    }

    public static RestResponse createFailureResponse(String errorMessage, int statusCode) {
        RestResponse res = new RestResponse();
        res.error = errorMessage;
        res.success = false;
        res.statusCode = statusCode;
        return res;
    }
}

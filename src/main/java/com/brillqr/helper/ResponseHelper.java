package com.brillqr.helper;

import java.util.HashMap;
import com.brillqr.dto.Response;

public class ResponseHelper {

    private static Response response = new Response();

    public static Response getResponseTemplate(){
        response.setData(new HashMap<String,Object>(1));
        response.setError(new HashMap<String,Object>(1));
        return response;
    }
}
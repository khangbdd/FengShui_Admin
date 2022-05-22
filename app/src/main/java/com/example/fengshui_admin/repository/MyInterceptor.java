package com.example.fengshui_admin.repository;


import java.io.IOException;

import kotlin.jvm.Throws;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.ConnectionShutdownException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            Response response = chain.proceed(request);

            assert response.body() != null;
            String bodyString = response.body().string();

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "";
                if ( e.getClass() ==  SocketTimeoutException.class) {
                    msg = "Timeout - Please check your internet connection";
                } else if (e.getClass() == UnknownHostException.class) {
                    msg = "Unable to make a connection. Please check your internet";
                } else if ( e.getClass() == ConnectionShutdownException.class) {
                    msg = "Connection shutdown. Please check your internet";
                } else if (e.getClass() == IOException.class) {
                    msg = "Server is unreachable, please try again later.";
                } else {
                    msg = e.getMessage();
                }
            return new Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(502)
                    .message(msg)
                    .body(ResponseBody.create(null, "{${e}}")).build();
        }
    }
}
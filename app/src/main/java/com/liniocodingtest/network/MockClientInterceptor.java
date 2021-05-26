package com.liniocodingtest.network;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockClientInterceptor implements Interceptor {

    Context context;

    public MockClientInterceptor(Context context) {
        this.context = context;
    }

    @Override
    @NotNull
    public Response intercept(Chain chain) {

        HttpUrl url = chain.request().url();

        if (url.encodedPath().contains("collections")) {
            String response = this.getJsonFromAssets("json/collections.json");
            return new Response.Builder()
                    .code(200)
                    .message(response)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json"), response.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }

        return new Response.Builder().code(404).build();
    }

    public String getJsonFromAssets(String path) {
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

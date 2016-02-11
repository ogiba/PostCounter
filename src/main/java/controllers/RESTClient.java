package controllers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Robert on 2016-02-11.
 */
public class RESTClient {
    private String BASE_URL = "http://resttest.cba.pl";

    public String get(String endpoint){
        String response = "";
        try {
            response = run(BASE_URL + endpoint);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return response;
    }

    private String run(String url)throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

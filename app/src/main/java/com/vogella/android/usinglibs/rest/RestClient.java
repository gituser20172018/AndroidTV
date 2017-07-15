package com.vogella.android.usinglibs.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.vogella.android.usinglibs.pojo.InstagramProfileData;

import java.io.IOException;



import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class RestClient {

    private static InstagramApiInterface instagramApiInterface;
//    private static String baseUrl = "https://www.instagram.com/cuilongzhe/media/" ;

    private static String baseUrl = "https://www.instagram.com" ;

    public static InstagramApiInterface getClient() {
        if (instagramApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instagramApiInterface = client.create(InstagramApiInterface.class);
        }
        return instagramApiInterface;
    }

    public interface InstagramApiInterface {

        @Headers("User-Agent: Retrofit2.0Tutorial-App")

        @GET("/{id}/media")
        Call<InstagramProfileData> fetchProfileData(@Path("id") String id);
    }

}

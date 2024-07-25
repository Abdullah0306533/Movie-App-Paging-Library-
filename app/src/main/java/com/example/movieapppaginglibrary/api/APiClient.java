package com.example.movieapppaginglibrary.api;

import static com.example.movieapppaginglibrary.utils.Utils.API_KEY;
import static com.example.movieapppaginglibrary.utils.Utils.BASE_URL;

import androidx.annotation.NonNull;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides a singleton instance of the Retrofit client for making API calls.
 */
public class APiClient {

    private static Api apiInterface; // Singleton instance of the API interface

    /**
     * Returns the singleton instance of the API interface.
     * <p>
     * Initializes the Retrofit client and API interface if they are not already created.
     * </p>
     *
     * @return the singleton API interface instance.
     */
    public static Api getApiInterface() {
        if (apiInterface == null) {
            OkHttpClient.Builder client = getBuilder(); // Get OkHttpClient.Builder instance

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Base URL for the API
                    .client(client.build()) // Set custom OkHttpClient with interceptors
                    .addConverterFactory(GsonConverterFactory.create()) // Convert JSON responses using Gson
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Convert Retrofit calls to RxJava3 Single
                    .build();

            apiInterface = retrofit.create(Api.class); // Create API interface instance
        }
        return apiInterface;
    }

    /**
     * Creates and configures an OkHttpClient.Builder with an interceptor.
     * <p>
     * The interceptor adds the API key to every request.
     * </p>
     *
     * @return a configured OkHttpClient.Builder instance.
     */
    @NonNull
    private static OkHttpClient.Builder getBuilder() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request original = chain.request(); // Original request
            HttpUrl originalHttpUrl = original.url(); // Original URL
            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY) // Add API key query parameter
                    .build();
            Request.Builder builder = original.newBuilder().url(url); // Rebuild request with new URL
            Request request = builder.build();

            return chain.proceed(request); // Proceed with the new request
        });
        return client; // Return configured OkHttpClient.Builder
    }
}

package com.example.movieapppaginglibrary.di;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapppaginglibrary.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * AppModule provides application-level dependencies.
 *
 * <p>This module is installed in the SingletonComponent, meaning that the dependencies provided here
 * will have a singleton scope and will be available throughout the application lifecycle.</p>
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    /**
     * Provides a singleton instance of {@link RequestManager}, which is used for loading images with Glide.
     *
     * <p>Glide is a fast and efficient open-source media management and image loading framework for Android.
     * This method sets a default request option to show an error drawable if image loading fails.</p>
     *
     * @param context the application context, annotated with {@link ApplicationContext} to specify the context type
     * @return a singleton instance of {@link RequestManager} configured with default request options
     */
    @Provides
    @Singleton
    public RequestManager getGlide(@ApplicationContext Context context) {
        return Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
                        .error(R.drawable.ic_image).placeholder(
                                R.drawable.ic_image));
    }
}

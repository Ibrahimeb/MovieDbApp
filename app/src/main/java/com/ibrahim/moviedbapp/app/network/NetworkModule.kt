package com.ibrahim.moviedbapp.app.network

import android.content.Context
import com.ibrahim.moviedbapp.app.ApplicationContext
import com.ibrahim.moviedbapp.app.di.AppModule
import com.ibrahim.moviedbapp.app.di.AppScope
import com.ibrahim.moviedbapp.commons.Utils.hasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    @AppScope
    fun providerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @AppScope
    fun providerCache(fileCache: File) = Cache(fileCache, 10 * 1000 * 1000) //10MB Cache

    @Provides
    @AppScope
    fun providerCacheFile(@ApplicationContext context: Context) = File(context.cacheDir, "okhttp_cache")

    @Provides
    @AppScope
    fun providerOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache, context: Context) =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor { chain ->

                // Get the request from the chain.
                var request = chain.request()

                /*
                *  Leveraging the advantage of using Kotlin,
                *  we initialize the request and change its header depending on whether
                *  the device is connected to Internet or not.
                */
                request = if (hasNetwork(context)!!)
                /*
                *  If there is Internet, get the cache that was stored 5 seconds ago.
                *  If the cache is older than 5 seconds, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-age' attribute is responsible for this behavior.
                */
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                /*
                *  If there is no Internet, get the cache that was stored 7 days ago.
                *  If the cache is older than 7 days, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-stale' attribute is responsible for this behavior.
                *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                */
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                // End of if-else statement

                // Add the modified request to the chain.
                chain.proceed(request)
            }
            .build()



}
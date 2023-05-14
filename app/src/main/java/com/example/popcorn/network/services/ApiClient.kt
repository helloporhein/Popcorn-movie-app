package com.example.popcorn.network.services

import com.example.popcorn.data_models.FeedItem
import com.example.popcorn.data_models.Media
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.InvocationTargetException

object ApiClient {
    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "27e1a1478d20909063f5d6d01eda076e"

    private val moshi: Moshi = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(Media::class.java, "media_type")
                .withSubtype(Media.Movie::class.java, "movie")
                .withSubtype(Media.Tv::class.java, "tv")
                .withSubtype(Media.Person::class.java, "person")
        )
        .add(
            PolymorphicJsonAdapterFactory.of(FeedItem::class.java, "type")
                .withSubtype(FeedItem.Header::class.java, "header")
                .withSubtype(FeedItem.HorizontalList::class.java, "horizontal_list")
        )
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getClient(addApiKey: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (addApiKey) {
            builder.addInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api_key",
                    API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
        }

        try {
            val StethoInterceptorClass =
                Class.forName("com.facebook.stetho.okhttp3.StethoInterceptor")
            val stethoInterceptor =
                StethoInterceptorClass.getConstructor().newInstance() as Interceptor
            builder.addNetworkInterceptor(stethoInterceptor)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace();
        } catch (e: NoSuchMethodException) {
            e.printStackTrace();
        } catch (e: IllegalAccessException) {
            e.printStackTrace();
        } catch (e: InvocationTargetException) {
            e.printStackTrace();
        }
        return builder.build()
    }

    private fun getTmdbClient(): TmdbService {
        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(getClient(addApiKey = true))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TmdbService::class.java)
    }


    val TMDB = getTmdbClient()

}
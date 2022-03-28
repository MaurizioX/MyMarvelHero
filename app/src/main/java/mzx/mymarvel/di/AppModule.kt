package mzx.mymarvel.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mzx.mymarvel.BuildConfig
import mzx.mymarvel.data.net.MarvelApiClient
import mzx.mymarvel.data.net.NetworkInterceptor
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.data.service.impl.CharacterServiceImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    @Singleton
    fun bindsCharacterService(characterServiceImpl: CharacterServiceImpl): CharacterService

    @Binds
    fun providesInterceptor(interceptor: NetworkInterceptor): Interceptor

    companion object {

        @Provides
        fun providesMarvelApiClient(okHttpClient: OkHttpClient): MarvelApiClient =
            Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create()

        @Provides
        fun provides(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .addNetworkInterceptor(interceptor)
            .build()
    }
}
package mzx.mymarvel.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mzx.mymarvel.BuildConfig
import mzx.mymarvel.data.net.MarvelApiClient
import mzx.mymarvel.data.service.CharacterService
import mzx.mymarvel.data.service.impl.CharacterServiceImpl
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

    companion object {

        @Provides
        fun providesMarvelApiClient(): MarvelApiClient = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}
package mzx.mymarvel.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mzx.mymarvel.domain.usecase.GetCharacterListUseCase
import mzx.mymarvel.domain.usecase.impl.GetCharacterListUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {
    @Binds
    fun bindGetCharacterListUseCase(getCharacterListUseCaseImpl: GetCharacterListUseCaseImpl): GetCharacterListUseCase
}
package com.griddynamics.unittests.di

import com.griddynamics.unittests.domain.repository.CommitsRepository
import com.griddynamics.unittests.domain.repository.ReposRepository
import com.griddynamics.unittests.domain.usecase.GetCommitsUseCase
import com.griddynamics.unittests.domain.usecase.GetReposByUserUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetReposByUserUseCase(reposRepository: ReposRepository): GetReposByUserUseCase {
        return GetReposByUserUseCase(reposRepository = reposRepository)
    }

    @Provides
    fun provideGetCommitsUseCase(commitsRepository: CommitsRepository): GetCommitsUseCase {
        return GetCommitsUseCase(commitsRepository = commitsRepository)
    }
}
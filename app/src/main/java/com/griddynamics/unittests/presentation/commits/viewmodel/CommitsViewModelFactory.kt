package com.griddynamics.unittests.presentation.commits.viewmodel

import android.app.Application
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.griddynamics.unittests.domain.usecase.GetCommitsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class CommitsViewModelFactory @AssistedInject constructor(
    val application: Application,
    private val getRepoCommitsUseCase: GetCommitsUseCase,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return CommitsViewModel(
            application = application,
            getRepoCommitsUseCase = getRepoCommitsUseCase,
            savedStateHandle = handle
        ) as T
    }
}
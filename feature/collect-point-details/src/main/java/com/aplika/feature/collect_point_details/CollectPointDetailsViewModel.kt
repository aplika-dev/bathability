package com.aplika.feature.collect_point_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.domain.usecase.GetCollectPointDetailsUseCase
import com.aplika.core.navigation.destination.CollectPointDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CollectPointDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCollectPointDetailsUseCase: GetCollectPointDetailsUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<CollectPointDetailsState> =
        getCollectPointDetailsUseCase(id = savedStateHandle.get<String?>(CollectPointDetailsDestination.Arguments.ID).orEmpty())
            .map { CollectPointDetailsState() }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CollectPointDetailsState()
            )

}
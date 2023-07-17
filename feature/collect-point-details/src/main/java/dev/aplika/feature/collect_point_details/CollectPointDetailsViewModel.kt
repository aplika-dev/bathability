package dev.aplika.feature.collect_point_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetCollectPointDetailsUseCase
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.feature.collect_point_details.mapper.BeachCollectToUIStateMapper
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
    private val beachCollectToUIStateMapper: BeachCollectToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<CollectPointDetailsUIState> =
        getCollectPointDetailsUseCase(id = savedStateHandle.get<String?>(CollectPointDetailsDestination.Arguments.ID).orEmpty())
            .map { beachCollectToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CollectPointDetailsUIState()
            )

}
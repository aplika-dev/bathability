package dev.aplika.feature.collect_point_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetSantaCatarinaCollectPointDetailsUseCase
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.feature.collect_point_details.mapper.SantaCatarinaCollectPointToUIStateMapper
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
    getSantaCatarinaCollectPointDetailsUseCase: GetSantaCatarinaCollectPointDetailsUseCase,
    private val santaCatarinaCollectPointToUIStateMapper: SantaCatarinaCollectPointToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<CollectPointDetailsUIState> =
        getSantaCatarinaCollectPointDetailsUseCase(id = savedStateHandle.get<String?>(CollectPointDetailsDestination.Arguments.ID).orEmpty())
            .map { santaCatarinaCollectPointToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CollectPointDetailsUIState()
            )

}
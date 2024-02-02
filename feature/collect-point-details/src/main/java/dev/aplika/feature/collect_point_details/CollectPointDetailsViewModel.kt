package dev.aplika.feature.collect_point_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetCollectPointDetailedByIdAndLocalityGroupUseCase
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.feature.collect_point_details.mapper.CollectPointDetailedToUIStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aplika.core.domain.model.LocalityGroup
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
    getCollectPointDetailedByIdAndLocalityGroupUseCase: GetCollectPointDetailedByIdAndLocalityGroupUseCase,
    private val collectPointDetailedToUIStateMapper: CollectPointDetailedToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val id by lazy { checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.ID)) }
    private val localityGroup by lazy { LocalityGroup.valueOf(checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.LOCALITY_GROUP))) }

    val uiState: StateFlow<CollectPointDetailsUIState> =
        getCollectPointDetailedByIdAndLocalityGroupUseCase(id = id, localityGroup = localityGroup)
            .map { collectPointDetailedToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CollectPointDetailsUIState()
            )

}
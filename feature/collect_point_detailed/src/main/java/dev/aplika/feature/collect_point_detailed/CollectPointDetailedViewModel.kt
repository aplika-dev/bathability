package dev.aplika.feature.collect_point_detailed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetCollectPointWithCollectsByIdAndLocalityGroupUseCase
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.feature.collect_point_detailed.mapper.CollectPointWithCollectsTaskToUIStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.core.ui.extensions.asTaskFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CollectPointDetailedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCollectPointWithCollectsByIdAndLocalityGroupUseCase: GetCollectPointWithCollectsByIdAndLocalityGroupUseCase,
    private val collectPointWithCollectsTaskToUIStateMapper: CollectPointWithCollectsTaskToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val id by lazy { checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.ID)) }
    private val localityGroup by lazy { LocalityGroup.valueOf(checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.LOCALITY_GROUP))) }

    val uiState: StateFlow<CollectPointDetailedUIState> =
        getCollectPointWithCollectsByIdAndLocalityGroupUseCase(id = id, localityGroup = localityGroup)
            .asTaskFlow()
            .map { collectPointWithCollectsTaskToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CollectPointDetailedUIState.Loading
            )

}
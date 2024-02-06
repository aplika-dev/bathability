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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class CollectPointDetailedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCollectPointWithCollectsByIdAndLocalityGroupUseCase: GetCollectPointWithCollectsByIdAndLocalityGroupUseCase,
    private val collectPointWithCollectsTaskToUIStateMapper: CollectPointWithCollectsTaskToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val id by lazy { checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.ID)) }
    private val localityGroup by lazy { LocalityGroup.valueOf(checkNotNull(savedStateHandle.get<String>(CollectPointDetailsDestination.Arguments.LOCALITY_GROUP))) }

    private val _uiState = MutableStateFlow<CollectPointDetailedUIState>(value = CollectPointDetailedUIState.IsLoading)
    val uiState: StateFlow<CollectPointDetailedUIState> = _uiState

    init {
        loadCollectPointDetails()
    }

    private fun loadCollectPointDetails() {
        getCollectPointWithCollectsByIdAndLocalityGroupUseCase(id = id, localityGroup = localityGroup)
            .asTaskFlow()
            .map { collectPointWithCollectsTaskToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .onEach { state -> _uiState.update { state } }
            .launchIn(viewModelScope)

    }

    fun reloadCollectPointDetails() {
        loadCollectPointDetails()
    }

}
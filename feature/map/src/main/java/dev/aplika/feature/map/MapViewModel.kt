package dev.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.annotation.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetMonitoringPointsUseCase
import dev.aplika.core.domain.usecase.SyncMonitoringPointsUseCase
import dev.aplika.core.ui.extensions.asTaskFlow
import dev.aplika.feature.map.mapper.MonitoringPointsToUIStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    getMonitoringPointsUseCase: GetMonitoringPointsUseCase,
    syncMonitoringPointsUseCase: SyncMonitoringPointsUseCase,
    private val monitoringPointsToUIStateMapper: MonitoringPointsToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getMonitoringPointsUseCase()
            .map { monitoringPointsToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        syncMonitoringPointsUseCase()
            .asTaskFlow()
            .flowOn(defaultDispatcher)
            .launchIn(viewModelScope)
    }

}
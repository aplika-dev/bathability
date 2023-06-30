package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.domain.usecase.GetCollectPointListUseCase
import com.aplika.core.domain.usecase.SyncCollectPointListUseCase
import com.aplika.core.ui.extensions.asTaskFlow
import com.aplika.feature.map.mapper.CollectPointToMarkerPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getCollectPointListUseCase: GetCollectPointListUseCase,
    private val syncCollectPointListUseCase: SyncCollectPointListUseCase,
    private val collectPointToMarkerPresentationMapper: CollectPointToMarkerPresentationMapper
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getCollectPointListUseCase()
            .map { locationList -> locationList.map { collectPointToMarkerPresentationMapper.map(input = it) } }
            .map { MapUIState(locationList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        syncCollectPointListUseCase()
            .asTaskFlow()
            .launchIn(viewModelScope)
    }

}
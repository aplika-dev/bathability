package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.domain.usecase.GetCollectPointDetailedListUseCase
import com.aplika.core.domain.usecase.SyncCollectPointListUseCase
import com.aplika.core.ui.extensions.asTaskFlow
import com.aplika.feature.map.mapper.CollectPointDetailedToMarkerPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getCollectPointDetailedListUseCase: GetCollectPointDetailedListUseCase,
    private val syncCollectPointListUseCase: SyncCollectPointListUseCase,
    private val collectPointDetailedToMarkerPresentationMapper: CollectPointDetailedToMarkerPresentationMapper
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getCollectPointDetailedListUseCase()
            .map { locationList -> locationList.map { collectPointDetailedToMarkerPresentationMapper.map(input = it) } }
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
package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.domain.usecase.GetLocationListUseCase
import com.aplika.core.domain.usecase.SyncLocationsUseCase
import com.aplika.core.ui.extensions.asTaskFlow
import com.aplika.feature.map.mapper.LocationToLocationPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase,
    private val syncLocationsUseCase: SyncLocationsUseCase,
    private val locationToLocationPresentationMapper: LocationToLocationPresentationMapper
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getLocationListUseCase()
            .map { locationList -> locationList.map { locationToLocationPresentationMapper.map(input = it) } }
            .map { MapUIState(locationList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        syncLocationsUseCase()
            .asTaskFlow()
            .launchIn(viewModelScope)
    }

}
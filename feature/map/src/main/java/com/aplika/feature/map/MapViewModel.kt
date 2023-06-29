package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.domain.usecase.GetLocationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationListUseCase: GetLocationListUseCase
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getLocationListUseCase()
            .map { MapUIState(locationList = it) }
            .onStart {  }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState(locationList = emptyList())
            )

}
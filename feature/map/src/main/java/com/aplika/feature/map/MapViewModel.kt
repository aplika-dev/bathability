package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.domain.usecase.GetBeachCollectsUseCase
import com.aplika.core.domain.usecase.SyncBeachCollectsUseCase
import com.aplika.core.ui.extensions.asTaskFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getBeachCollectsUseCase: GetBeachCollectsUseCase,
    private val syncBeachCollectsUseCase: SyncBeachCollectsUseCase
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getBeachCollectsUseCase()
            .map { MapUIState(locationList = emptyList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        syncBeachCollectsUseCase()
            .asTaskFlow()
            .launchIn(viewModelScope)
    }

}
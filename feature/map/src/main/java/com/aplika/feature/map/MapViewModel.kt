package com.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplika.core.android.di.DefaultDispatcher
import com.aplika.core.domain.usecase.GetBeachCollectsUseCase
import com.aplika.core.domain.usecase.SyncBeachCollectsUseCase
import com.aplika.core.ui.extensions.asTaskFlow
import com.aplika.core.ui.model.Task
import com.aplika.feature.map.mapper.BeachCollectsToUIStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    getBeachCollectsUseCase: GetBeachCollectsUseCase,
    syncBeachCollectsUseCase: SyncBeachCollectsUseCase,
    private val beachCollectsToUIStateMapper: BeachCollectsToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getBeachCollectsUseCase()
            .map { beachCollectsToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        syncBeachCollectsUseCase()
            .asTaskFlow()
            .flowOn(defaultDispatcher)
            .launchIn(viewModelScope)
    }

}
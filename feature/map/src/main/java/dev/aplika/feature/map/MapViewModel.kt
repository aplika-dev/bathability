package dev.aplika.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.aplika.core.android.di.DefaultDispatcher
import dev.aplika.core.domain.usecase.GetAllCollectPointsUseCase
import dev.aplika.core.domain.usecase.FetchAllCollectPointsUseCase
import dev.aplika.feature.map.mapper.MapUIStateMapperArgsToUIStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aplika.core.domain.usecase.ShouldShowInAppReviewUseCase
import dev.aplika.feature.map.model.MapUIStateMapperArgs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MapViewModel @Inject constructor(
    getAllCollectPointsUseCase: GetAllCollectPointsUseCase,
    fetchAllCollectPointsUseCase: FetchAllCollectPointsUseCase,
    shouldShowInAppReviewUseCase: ShouldShowInAppReviewUseCase,
    private val mapUIStateMapperArgsToUIStateMapper: MapUIStateMapperArgsToUIStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val uiState: StateFlow<MapUIState> =
        getAllCollectPointsUseCase()
            .map {
                MapUIStateMapperArgs(
                    collectPoints = it,
                    shouldShowInAppReview = shouldShowInAppReviewUseCase()
                )
            }
            .map { mapUIStateMapperArgsToUIStateMapper.map(input = it) }
            .flowOn(defaultDispatcher)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MapUIState()
            )

    init {
        viewModelScope.launch {
            fetchAllCollectPointsUseCase()
        }
    }

}
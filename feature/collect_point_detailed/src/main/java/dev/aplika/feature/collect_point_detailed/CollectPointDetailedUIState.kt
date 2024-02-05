package dev.aplika.feature.collect_point_detailed

import dev.aplika.feature.collect_point_detailed.state.CollectState
import dev.aplika.feature.collect_point_detailed.state.HeaderState

sealed interface CollectPointDetailedUIState {

    object IsLoading : CollectPointDetailedUIState

    object IsError : CollectPointDetailedUIState

    data class HasContent(
        val headerState: HeaderState,
        val collects: List<CollectState>
    ) : CollectPointDetailedUIState


}

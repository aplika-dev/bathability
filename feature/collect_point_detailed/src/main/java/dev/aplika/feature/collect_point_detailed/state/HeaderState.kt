package dev.aplika.feature.collect_point_detailed.state

sealed interface HeaderState {

    object IsLoading : HeaderState

    data class HasContent(
        val title: String,
        val subtitle: String
    ) : HeaderState

}
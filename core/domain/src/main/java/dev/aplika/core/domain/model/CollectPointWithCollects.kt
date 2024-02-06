package dev.aplika.core.domain.model

data class CollectPointWithCollects(
    val collectPoint: CollectPoint,
    val collects: List<Collect>
)

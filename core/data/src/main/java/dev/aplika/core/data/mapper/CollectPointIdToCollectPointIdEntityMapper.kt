package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPointId
import javax.inject.Inject

class CollectPointIdToCollectPointIdEntityMapper @Inject constructor(
    private val localityGroupToLocalityGroupEntityMapper: LocalityGroupToLocalityGroupEntityMapper
) : Mapper<CollectPointId, CollectPointIdEntity> {
    override fun map(input: CollectPointId): CollectPointIdEntity {
        return CollectPointIdEntity(
            id = input.id,
            localityGroup = localityGroupToLocalityGroupEntityMapper.map(input = input.localityGroup)
        )
    }
}
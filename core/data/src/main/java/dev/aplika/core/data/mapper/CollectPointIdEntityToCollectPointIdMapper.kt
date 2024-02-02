package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.CollectPointId
import javax.inject.Inject

class CollectPointIdEntityToCollectPointIdMapper @Inject constructor(
    private val localityGroupEntityToLocalityGroupMapper: LocalityGroupEntityToLocalityGroupMapper
) : Mapper<CollectPointIdEntity, CollectPointId> {
    override fun map(input: CollectPointIdEntity): CollectPointId {
        return CollectPointId(
            id = input.id,
            localityGroup = localityGroupEntityToLocalityGroupMapper.map(input = input.localityGroup)
        )
    }
}
package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectPointIdEntity
import dev.aplika.core.database.model.LocalityGroupEntity
import dev.aplika.core.domain.model.CollectPointId
import dev.aplika.core.domain.model.LocalityGroup
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
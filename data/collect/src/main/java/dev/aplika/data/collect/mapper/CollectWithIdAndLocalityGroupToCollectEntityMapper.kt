package dev.aplika.data.collect.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.CollectEntity
import dev.aplika.core.domain.model.Collect
import dev.aplika.data.collect.model.CollectWithIdAndLocalityGroup
import javax.inject.Inject

class CollectWithIdAndLocalityGroupToCollectEntityMapper @Inject constructor() : Mapper<CollectWithIdAndLocalityGroup, CollectEntity> {
    override fun map(input: CollectWithIdAndLocalityGroup): CollectEntity {
        return CollectEntity(
            collectPointId = input.collectPointId,
            localityGroup = input.localityGroup,
            date = input.collect.date.time,
            bathabilityStatus = input.collect.bathabilityStatus,
            rainStatus = input.collect.rainStatus,
            escherichiaColiFactor = input.collect.escherichiaColiFactor
        )
    }
}
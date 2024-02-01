package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.database.model.LocalityGroupEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.LocalityGroup
import javax.inject.Inject

class LocalityGroupToLocalityGroupEntityMapper @Inject constructor() : Mapper<LocalityGroup, LocalityGroupEntity> {
    override fun map(input: LocalityGroup): LocalityGroupEntity {
        return when(input) {
            LocalityGroup.SANTA_CATARINA -> LocalityGroupEntity.SANTA_CATARINA
            LocalityGroup.RIO_GRANDE_DO_SUL -> LocalityGroupEntity.RIO_GRANDE_DO_SUL
        }
    }
}
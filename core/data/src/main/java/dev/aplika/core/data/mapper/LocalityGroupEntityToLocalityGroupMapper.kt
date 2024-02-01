package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.database.model.LocalityGroupEntity
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.LocalityGroup
import javax.inject.Inject

class LocalityGroupEntityToLocalityGroupMapper @Inject constructor() : Mapper<LocalityGroupEntity, LocalityGroup> {
    override fun map(input: LocalityGroupEntity): LocalityGroup {
        return when(input) {
            LocalityGroupEntity.SANTA_CATARINA -> LocalityGroup.SANTA_CATARINA
            LocalityGroupEntity.RIO_GRANDE_DO_SUL -> LocalityGroup.RIO_GRANDE_DO_SUL
        }
    }
}
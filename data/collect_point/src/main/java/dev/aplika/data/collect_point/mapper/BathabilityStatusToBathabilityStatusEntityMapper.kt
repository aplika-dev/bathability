package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.domain.model.BathabilityStatus
import javax.inject.Inject

class BathabilityStatusToBathabilityStatusEntityMapper @Inject constructor() : Mapper<BathabilityStatus, BathabilityStatusEntity> {
    override fun map(input: BathabilityStatus): BathabilityStatusEntity {
        return when(input) {
            BathabilityStatus.APPROPRIATE -> BathabilityStatusEntity.APPROPRIATE
            BathabilityStatus.INAPPROPRIATE -> BathabilityStatusEntity.INAPPROPRIATE
            BathabilityStatus.UNDETERMINED -> BathabilityStatusEntity.UNDETERMINED
        }
    }
}
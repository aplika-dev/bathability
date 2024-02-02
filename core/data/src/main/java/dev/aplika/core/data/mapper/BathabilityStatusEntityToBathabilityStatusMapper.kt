package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import javax.inject.Inject

class BathabilityStatusEntityToBathabilityStatusMapper @Inject constructor() : Mapper<BathabilityStatusEntity, BathabilityStatus> {
    override fun map(input: BathabilityStatusEntity): BathabilityStatus {
        return when(input) {
            BathabilityStatusEntity.APPROPRIATE -> BathabilityStatus.APPROPRIATE
            BathabilityStatusEntity.INAPPROPRIATE -> BathabilityStatus.INAPPROPRIATE
            BathabilityStatusEntity.UNDETERMINED -> BathabilityStatus.UNDETERMINED
        }
    }
}
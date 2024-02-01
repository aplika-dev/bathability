package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import javax.inject.Inject

class BathabilityStatusEntityToBathabilityStatusMapper @Inject constructor() : Mapper<BathabilityStatusEntity?, SantaCatarinaBathabilityStatus> {
    override fun map(input: BathabilityStatusEntity?): SantaCatarinaBathabilityStatus {
        return when(input) {
            BathabilityStatusEntity.APPROPRIATE -> SantaCatarinaBathabilityStatus.APPROPRIATE
            BathabilityStatusEntity.INAPPROPRIATE -> SantaCatarinaBathabilityStatus.INAPPROPRIATE
            BathabilityStatusEntity.UNDETERMINED -> SantaCatarinaBathabilityStatus.UNDETERMINED
            null -> SantaCatarinaBathabilityStatus.UNDETERMINED
        }
    }
}
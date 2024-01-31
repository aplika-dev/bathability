package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaBathabilityStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import javax.inject.Inject

class BathabilityStatusEntityToBathabilityStatusMapper @Inject constructor() : Mapper<SantaCatarinaBathabilityStatusEntity?, SantaCatarinaBathabilityStatus> {
    override fun map(input: SantaCatarinaBathabilityStatusEntity?): SantaCatarinaBathabilityStatus {
        return when(input) {
            SantaCatarinaBathabilityStatusEntity.APPROPRIATE -> SantaCatarinaBathabilityStatus.APPROPRIATE
            SantaCatarinaBathabilityStatusEntity.INAPPROPRIATE -> SantaCatarinaBathabilityStatus.INAPPROPRIATE
            SantaCatarinaBathabilityStatusEntity.UNDETERMINED -> SantaCatarinaBathabilityStatus.UNDETERMINED
            null -> SantaCatarinaBathabilityStatus.UNDETERMINED
        }
    }
}
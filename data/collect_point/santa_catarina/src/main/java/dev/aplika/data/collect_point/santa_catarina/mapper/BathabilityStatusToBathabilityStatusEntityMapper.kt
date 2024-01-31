package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaBathabilityStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import javax.inject.Inject

class BathabilityStatusToBathabilityStatusEntityMapper @Inject constructor() : Mapper<SantaCatarinaBathabilityStatus, SantaCatarinaBathabilityStatusEntity> {
    override fun map(input: SantaCatarinaBathabilityStatus): SantaCatarinaBathabilityStatusEntity {
        return when(input) {
            SantaCatarinaBathabilityStatus.APPROPRIATE -> SantaCatarinaBathabilityStatusEntity.APPROPRIATE
            SantaCatarinaBathabilityStatus.INAPPROPRIATE -> SantaCatarinaBathabilityStatusEntity.INAPPROPRIATE
            SantaCatarinaBathabilityStatus.UNDETERMINED -> SantaCatarinaBathabilityStatusEntity.UNDETERMINED
        }
    }
}
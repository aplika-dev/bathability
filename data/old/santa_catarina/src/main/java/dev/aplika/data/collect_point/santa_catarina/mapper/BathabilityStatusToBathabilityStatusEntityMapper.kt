package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.BathabilityStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import javax.inject.Inject

class BathabilityStatusToBathabilityStatusEntityMapper @Inject constructor() : Mapper<SantaCatarinaBathabilityStatus, BathabilityStatusEntity> {
    override fun map(input: SantaCatarinaBathabilityStatus): BathabilityStatusEntity {
        return when(input) {
            SantaCatarinaBathabilityStatus.APPROPRIATE -> BathabilityStatusEntity.APPROPRIATE
            SantaCatarinaBathabilityStatus.INAPPROPRIATE -> BathabilityStatusEntity.INAPPROPRIATE
            SantaCatarinaBathabilityStatus.UNDETERMINED -> BathabilityStatusEntity.UNDETERMINED
        }
    }
}
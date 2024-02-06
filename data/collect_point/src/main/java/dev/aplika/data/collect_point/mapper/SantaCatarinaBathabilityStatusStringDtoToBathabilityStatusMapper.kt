package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import javax.inject.Inject

class SantaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper @Inject constructor() : Mapper<String?, BathabilityStatus> {
    override fun map(input: String?): BathabilityStatus {
        return when (input) {
            APPROPRIATE_CODE -> BathabilityStatus.APPROPRIATE
            INAPPROPRIATE_CODE -> BathabilityStatus.INAPPROPRIATE
            else -> BathabilityStatus.UNDETERMINED
        }
    }

    private companion object {
        private const val APPROPRIATE_CODE = "PRÓPRIO"
        private const val INAPPROPRIATE_CODE = "IMPRÓPRIO"
    }
}
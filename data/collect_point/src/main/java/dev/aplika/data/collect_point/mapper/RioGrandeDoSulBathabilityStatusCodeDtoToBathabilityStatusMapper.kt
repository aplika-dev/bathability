package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import javax.inject.Inject

class RioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper @Inject constructor() : Mapper<Int, BathabilityStatus> {
    override fun map(input: Int): BathabilityStatus {
        return when (input) {
            APPROPRIATE_CODE -> BathabilityStatus.APPROPRIATE
            INAPPROPRIATE_CODE -> BathabilityStatus.INAPPROPRIATE
            else -> BathabilityStatus.UNDETERMINED
        }
    }

    private companion object {
        private const val APPROPRIATE_CODE = 2
        private const val INAPPROPRIATE_CODE = 3
    }
}
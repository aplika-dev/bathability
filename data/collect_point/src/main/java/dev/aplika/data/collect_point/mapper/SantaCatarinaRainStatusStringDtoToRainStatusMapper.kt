package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.RainStatus
import javax.inject.Inject

class SantaCatarinaRainStatusStringDtoToRainStatusMapper @Inject constructor() : Mapper<String, RainStatus> {
    override fun map(input: String): RainStatus {
        return when (input) {
            ABSENT_CODE -> RainStatus.ABSENT
            WEAK_CODE -> RainStatus.WEAK
            MODERATE_CODE -> RainStatus.MODERATE
            else -> RainStatus.UNKNOWN
        }
    }

    private companion object {
        private const val ABSENT_CODE = "Ausente"
        private const val WEAK_CODE = "Fraca"
        private const val MODERATE_CODE = "Moderada"
    }
}
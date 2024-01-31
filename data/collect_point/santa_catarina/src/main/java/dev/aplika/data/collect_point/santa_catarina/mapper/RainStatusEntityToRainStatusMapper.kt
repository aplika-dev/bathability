package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaRainStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import javax.inject.Inject

class RainStatusEntityToRainStatusMapper @Inject constructor() : Mapper<SantaCatarinaRainStatusEntity, SantaCatarinaRainStatus> {
    override fun map(input: SantaCatarinaRainStatusEntity): SantaCatarinaRainStatus {
        return when(input) {
            SantaCatarinaRainStatusEntity.ABSENT -> SantaCatarinaRainStatus.ABSENT
            SantaCatarinaRainStatusEntity.WEAK -> SantaCatarinaRainStatus.WEAK
            SantaCatarinaRainStatusEntity.MODERATE -> SantaCatarinaRainStatus.MODERATE
            SantaCatarinaRainStatusEntity.UNKNOWN -> SantaCatarinaRainStatus.UNKNOWN
        }
    }
}
package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaRainStatusEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import javax.inject.Inject

class RainStatusToRainStatusEntityMapper @Inject constructor() : Mapper<SantaCatarinaRainStatus, SantaCatarinaRainStatusEntity> {
    override fun map(input: SantaCatarinaRainStatus): SantaCatarinaRainStatusEntity {
        return when(input) {
            SantaCatarinaRainStatus.ABSENT -> SantaCatarinaRainStatusEntity.ABSENT
            SantaCatarinaRainStatus.WEAK -> SantaCatarinaRainStatusEntity.WEAK
            SantaCatarinaRainStatus.MODERATE -> SantaCatarinaRainStatusEntity.MODERATE
            SantaCatarinaRainStatus.UNKNOWN -> SantaCatarinaRainStatusEntity.UNKNOWN
        }
    }
}
package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.database.model.SantaCatarinaCollectPointEntity
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollectPoint
import javax.inject.Inject

class CollectPointToCollectPointEntityMapper @Inject constructor() : Mapper<SantaCatarinaCollectPoint, SantaCatarinaCollectPointEntity> {
    override fun map(input: SantaCatarinaCollectPoint): SantaCatarinaCollectPointEntity {
        return SantaCatarinaCollectPointEntity(
            id = input.id,
            city = input.city,
            name = input.name,
            description = input.description,
            latitude = input.latitude,
            longitude = input.longitude
        )
    }
}
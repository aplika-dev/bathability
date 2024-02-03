package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.CollectPointWithCollects
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.network.santa_catarina.model.CollectDto
import dev.aplika.network.santa_catarina.model.CollectPointDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class SantaCatarinaCollectPointDtoToCollectPointWithCollectsMapper @Inject constructor(
    private val santaCatarinaCollectPointDtoToCollectPointMapper: SantaCatarinaCollectPointDtoToCollectPointMapper,
    private val santaCatarinaCollectDtoToCollectMapper: SantaCatarinaCollectDtoToCollectMapper
) : Mapper<CollectPointDto, CollectPointWithCollects> {
    override fun map(input: CollectPointDto): CollectPointWithCollects {
        return CollectPointWithCollects(
            collectPoint = santaCatarinaCollectPointDtoToCollectPointMapper.map(input = input),
            collects = input.collects.orEmpty().mapNotNull { santaCatarinaCollectDtoToCollectMapper.map(input = it) }
        )
    }
}
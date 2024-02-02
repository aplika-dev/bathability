package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.CollectPoint
import dev.aplika.core.domain.model.LocalityGroup
import dev.aplika.network.santa_catarina.model.CollectDto
import dev.aplika.network.santa_catarina.model.CollectPointDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class SantaCatarinaCollectDtoToCollectMapper @Inject constructor(
    private val santaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper: SantaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper,
    private val santaCatarinaRainStatusStringDtoToRainStatusMapper: SantaCatarinaRainStatusStringDtoToRainStatusMapper
) : Mapper<CollectDto, Collect?> {
    override fun map(input: CollectDto): Collect? {
        return Collect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilityStatus = santaCatarinaBathabilityStatusStringDtoToBathabilityStatusMapper.map(input = input.bathabilityStatus),
            rainStatus = santaCatarinaRainStatusStringDtoToRainStatusMapper.map(input = input.rainStatus),
            escherichiaColiFactor = input.escherichiaColiFactor.toFloatOrNull()?.roundToInt()
        )
    }
}
package dev.aplika.data.collect_point.santa_catarina.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaBathabilityStatus
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaCollect
import dev.aplika.core.domain.model.santa_catarina.SantaCatarinaRainStatus
import dev.aplika.data.collect_point.santa_catarina.model.SantaCatarinaCollectDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class CollectDtoToCollectMapper @Inject constructor() : Mapper<SantaCatarinaCollectDto, SantaCatarinaCollect?> {

    override fun map(input: SantaCatarinaCollectDto): SantaCatarinaCollect? {
        val escherichiaColiFactor = input.escherichiaColiFactor.toFloatOrNull()?.roundToInt() ?: return null
        return SantaCatarinaCollect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilityStatus = input.bathabilityStatus.toBathabilityStatus(),
            rainStatus = input.rainStatus.toRainStatus(),
            escherichiaColiFactor = escherichiaColiFactor
        )
    }

    private fun String.toBathabilityStatus(): SantaCatarinaBathabilityStatus =
        when (this) {
            "PRÓPRIO" -> SantaCatarinaBathabilityStatus.APPROPRIATE
            "IMPRÓPRIO" -> SantaCatarinaBathabilityStatus.INAPPROPRIATE
            else -> SantaCatarinaBathabilityStatus.UNDETERMINED
        }

    private fun String.toRainStatus(): SantaCatarinaRainStatus =
        when (this) {
            "Ausente" -> SantaCatarinaRainStatus.ABSENT
            "Fraca" -> SantaCatarinaRainStatus.WEAK
            "Moderada" -> SantaCatarinaRainStatus.MODERATE
            else -> SantaCatarinaRainStatus.UNKNOWN
        }
}
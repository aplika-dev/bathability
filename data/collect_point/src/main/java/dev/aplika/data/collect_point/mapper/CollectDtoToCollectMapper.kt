package dev.aplika.data.collect_point.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilityStatus
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.RainStatus
import dev.aplika.network.santa_catarina.model.CollectDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class CollectDtoToCollectMapper @Inject constructor() : Mapper<CollectDto, Collect?> {

    override fun map(input: CollectDto): Collect? {
        val escherichiaColiFactor = input.escherichiaColiFactor.toFloatOrNull()?.roundToInt() ?: return null
        return Collect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilityStatus = input.bathabilityStatus.toBathabilityStatus(),
            rainStatus = input.rainStatus.toRainStatus(),
            escherichiaColiFactor = escherichiaColiFactor
        )
    }

    private fun String.toBathabilityStatus(): BathabilityStatus =
        when (this) {
            "PRÓPRIO" -> BathabilityStatus.APPROPRIATE
            "IMPRÓPRIO" -> BathabilityStatus.INAPPROPRIATE
            else -> BathabilityStatus.UNDETERMINED
        }

    private fun String.toRainStatus(): RainStatus =
        when (this) {
            "Ausente" -> RainStatus.ABSENT
            "Fraca" -> RainStatus.WEAK
            "Moderada" -> RainStatus.MODERATE
            else -> RainStatus.UNKNOWN
        }
}
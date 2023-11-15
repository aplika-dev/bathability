package dev.aplika.core.data.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.domain.model.BathabilitySituation
import dev.aplika.core.domain.model.Collect
import dev.aplika.core.domain.model.RainSituation
import dev.aplika.core.network.model.CollectDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class CollectDtoToCollectMapper @Inject constructor() : Mapper<CollectDto, Collect?> {
    override fun map(input: CollectDto): Collect? {
        val escherichiaColiFactor = input.escherichiaColiFactor.toFloatOrNull()?.roundToInt() ?: return null
        return Collect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilitySituation = BathabilitySituation.getById(id = input.bathabilitySituation),
            rainSituation = RainSituation.getById(id = input.rainSituation),
            escherichiaColiFactor = escherichiaColiFactor
        )
    }
}
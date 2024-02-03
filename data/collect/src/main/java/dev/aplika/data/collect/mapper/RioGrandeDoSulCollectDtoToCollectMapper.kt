package dev.aplika.data.collect.mapper

import dev.aplika.core.android.mapper.Mapper
import dev.aplika.core.data.mapper.RioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper
import dev.aplika.core.domain.model.Collect
import dev.aplika.network.rio_grande_do_sul.model.CollectDto
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class RioGrandeDoSulCollectDtoToCollectMapper @Inject constructor(
    private val rioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper: RioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper
) : Mapper<CollectDto, Collect?> {
    override fun map(input: CollectDto): Collect? {
        return Collect(
            date = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(input.date) ?: return null,
            bathabilityStatus = rioGrandeDoSulBathabilityStatusCodeDtoToBathabilityStatusMapper.map(input = input.status),
            rainStatus = null,
            escherichiaColiFactor = null
        )
    }
}
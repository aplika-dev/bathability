package com.aplika.feature.collect_point_details.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BathabilitySituation
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.domain.model.RainSituation
import com.aplika.core.kotlin.extensions.formatToString
import com.aplika.core.resources.R
import com.aplika.feature.collect_point_details.CollectPointDetailsState
import com.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class BeachCollectToStateMapper @Inject constructor() : Mapper<BeachCollect, CollectPointDetailsState> {
    override fun map(input: BeachCollect): CollectPointDetailsState {
        return CollectPointDetailsState(
            isLoading = false,
            title = input.beach,
            description = "${input.city} - ${input.location} - ${input.collectPoint}",
            collects = input.collects.map {
                CollectState(
                    leadingIcon = when (it.bathabilitySituation) {
                        BathabilitySituation.APPROPRIATE -> R.drawable.ic_check
                        BathabilitySituation.INAPPROPRIATE -> R.drawable.ic_close
                        BathabilitySituation.UNDETERMINED -> R.drawable.ic_question_mark
                    },
                    headline = it.date.formatToString(format = "dd/MM/yyyy"),
                    supporting = when (it.rainSituation) {
                        RainSituation.ABSENT -> "Chuva ausente"
                        RainSituation.WEAK -> "Chuva fraca"
                        RainSituation.MODERATE -> "Chuva moderada"
                        RainSituation.UNKNOWN -> "Chuva desconhecida"
                    },
                    trailing = it.escherichiaColiFactor.toString()
                )
            }
        )
    }
}
package com.aplika.feature.collect_point_details.mapper

import com.aplika.core.android.mapper.Mapper
import com.aplika.core.domain.model.BeachCollect
import com.aplika.core.kotlin.extensions.formatToString
import com.aplika.feature.collect_point_details.CollectPointDetailsState
import com.aplika.feature.collect_point_details.state.CollectState
import javax.inject.Inject

class BeachCollectToStateMapper @Inject constructor() : Mapper<BeachCollect, CollectPointDetailsState> {
    override fun map(input: BeachCollect): CollectPointDetailsState {
        return CollectPointDetailsState(
            title = input.beach,
            description = "${input.city} - ${input.location} - ${input.collectPoint}",
            collects = input.collects.map {
                CollectState(
                    date = it.date.formatToString(format = "dd/MM/yyyy"),
                    bathabilitySituation = it.bathabilitySituation.id.orEmpty(),
                    rainSituation = it.rainSituation.id.orEmpty(),
                    escherichiaColiFactor = it.escherichiaColiFactor.toString()
                )
            }
        )
    }
}
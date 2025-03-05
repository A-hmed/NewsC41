package com.route.newsc41.data.mappers

import com.route.newsc41.data.model.SourceDM
import com.route.newsc41.domain.model.Source
import javax.inject.Inject

class SourcesMapper @Inject constructor() {
    fun fromDataModel(sourceDM: SourceDM): Source =
        Source(name = sourceDM.name ?: "", id = sourceDM.id)

    fun fromDataModels(sources: List<SourceDM>): List<Source> =
        sources.map {
            return@map fromDataModel(it)
        }

}
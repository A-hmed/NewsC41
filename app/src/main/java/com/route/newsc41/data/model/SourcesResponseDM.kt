package com.route.newsc41.data.model

import com.google.gson.annotations.SerializedName

data class SourcesResponseDM(
    @field:SerializedName("sources")
    val sources: List<SourceDM>? = null,

    ) : BaseResponse()
package com.route.newsc41.api.model

import com.google.gson.annotations.SerializedName

data class SourcesResponseDM(
    @field:SerializedName("sources")
    val sources: List<SourceDM?>? = null,

    ) : BaseResponse()
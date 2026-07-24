package com.ivy.releases

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

class ReleasesDataSource @Inject constructor() {

    @Keep
    @Serializable
    data class ReleaseDto(
        @SerialName("tag_name")
        val releaseName: String,
        @SerialName("html_url")
        val releaseUrl: String,
        @SerialName("published_at")
        val releaseDate: String,
        @SerialName("body")
        val commits: String?
    )

    suspend fun fetchReleaseInfo(): List<ReleaseDto>? = null
}

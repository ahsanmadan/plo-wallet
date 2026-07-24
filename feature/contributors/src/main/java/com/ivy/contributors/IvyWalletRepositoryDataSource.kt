package com.ivy.contributors

import androidx.annotation.Keep
import arrow.core.Either
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

class IvyWalletRepositoryDataSource @Inject constructor() {
    @Keep
    @Serializable
    @Suppress("DataClassDefaultValues")
    data class ContributorDto(
        val login: String? = null,
        @SerialName("avatar_url")
        val avatarUrl: String? = null,
        val contributions: Int,
        @SerialName("html_url")
        val link: String? = null,
    )

    @Keep
    @Serializable
    data class IvyWalletRepositoryInfo(
        @SerialName("forks")
        val forks: Int,
        @SerialName("stargazers_count")
        val stars: Int,
        @SerialName("html_url")
        val url: String,
    )

    suspend fun fetchContributors(): Either<String, List<ContributorDto>> = Either.Right(
        listOf(
            ContributorDto(
                login = "Ahsan Ramadan",
                avatarUrl = null,
                contributions = 1,
                link = null,
            )
        )
    )

    suspend fun fetchRepositoryInfo(): IvyWalletRepositoryInfo? = null
}

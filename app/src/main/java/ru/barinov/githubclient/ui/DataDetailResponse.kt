package ru.barinov.githubclient.ui

import ru.barinov.githubclient.data.LoadedProfileEntity
import ru.barinov.githubclient.domain.GitHubRepoEntity

data class DataDetailResponse(
    val repositories: List<GitHubRepoEntity>,
    val profile :LoadedProfileEntity?,
    )


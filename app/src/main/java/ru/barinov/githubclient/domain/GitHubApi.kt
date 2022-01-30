package ru.barinov.githubclient.domain

import io.reactivex.rxjava3.core.*
import retrofit2.Call
import retrofit2.http.*
import ru.barinov.githubclient.data.LoadedProfileEntity

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Single<LoadedProfileEntity>

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Single<List<GitHubRepoEntity>>
}
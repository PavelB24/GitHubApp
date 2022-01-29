package ru.barinov.githubclient.domain

import retrofit2.Call
import retrofit2.http.*
import ru.barinov.githubclient.data.LoadedProfileEntity

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Call<LoadedProfileEntity>
}
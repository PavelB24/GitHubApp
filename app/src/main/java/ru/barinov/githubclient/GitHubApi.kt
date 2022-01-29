package ru.barinov.githubclient

import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Call<GitUserEntity>
}
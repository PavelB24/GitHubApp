package ru.barinov.githubclient.domain

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import ru.barinov.githubclient.data.LoadedProfileEntity
import ru.barinov.githubclient.domain.GitHubApi

private const val BASE_URL = "https://api.github.com/"

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)


    fun loadUserEntityAsync(
        userName: String,
        callback: (gitUserEntity: LoadedProfileEntity?) -> Unit
    ){
        api.loadUserByName(userName).enqueue(object: Callback<LoadedProfileEntity>{
            override fun onResponse(
                call: Call<LoadedProfileEntity>,
                response: Response<LoadedProfileEntity>) {
                callback.invoke(response.body()!!)
            }

            override fun onFailure(
                call: Call<LoadedProfileEntity>,
                t: Throwable) {
                callback.invoke(null)
            }

        })
    }
}
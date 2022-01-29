package ru.barinov.githubclient

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)


    fun loadUserEntityAsync(
        userName: String,
        callback: (gitUserEntity: GitUserEntity?) -> Unit
    ){
        api.loadUserByName(userName).enqueue(object: Callback<GitUserEntity>{
            override fun onResponse(
                call: Call<GitUserEntity>,
                response: Response<GitUserEntity>) {
                callback.invoke(response.body()!!)
            }

            override fun onFailure(
                call: Call<GitUserEntity>,
                t: Throwable) {
                callback.invoke(null)
            }

        })
    }
}
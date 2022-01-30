package ru.barinov.githubclient.domain

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import ru.barinov.githubclient.data.LoadedProfileEntity


private const val BASE_URL = "https://api.github.com/"

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)


    fun loadUserEntityAsync(
        userName: String
    ): Observable<LoadedProfileEntity> {
        return api.loadUserByName(userName)
        
    }




    fun loadUserRepositoriesAsync(
        userName: String
    ): Observable<List<GitHubRepoEntity>>
    {
        return api.loadUsersRepositories(userName)
    }
}
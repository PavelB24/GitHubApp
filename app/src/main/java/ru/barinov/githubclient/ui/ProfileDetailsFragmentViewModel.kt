package ru.barinov.githubclient.ui

import android.util.Log
import androidx.lifecycle.*
import ru.barinov.githubclient.data.*
import ru.barinov.githubclient.domain.*
import java.io.IOException
import java.lang.RuntimeException

class ProfileDetailsFragmentViewModel(
    private val cacheRepository: LoadedProfileRepository,
    private val loader: GitHubLoader
): ViewModel() {

    private val _dataLoadedLiveData = MutableLiveData<DataDetailResponse>()
    val dataLoadedLiveDataSearch: LiveData<DataDetailResponse> = _dataLoadedLiveData

    fun getData(login: String) {
        var repositoriesList = emptyList<GitHubRepoEntity>()
        try {
        loader.loadUserRepositoriesAsync(login) { list ->
            if (list != null) {
                repositoriesList = list
                _dataLoadedLiveData.postValue(
                    DataDetailResponse(
                        repositoriesList, cacheRepository.getProfileByLogin(login)!!, true
                    )
                )
            } else {
                _dataLoadedLiveData.postValue(
                    DataDetailResponse(
                        repositoriesList, cacheRepository.getProfileByLogin(login)!!, false
                    )
                )
            }
        }
    } catch (e: IOException){

    }
    }
}
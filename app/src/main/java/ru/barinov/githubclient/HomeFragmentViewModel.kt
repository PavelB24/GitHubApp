package ru.barinov.githubclient

import androidx.lifecycle.*

class HomeFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: GitNamesRepo
) : ViewModel() {

    private val _userNamesLiveData = MutableLiveData<List<GitHubUsersSerializedName>>()
    val userNamesLiveData: LiveData<List<GitHubUsersSerializedName>> = _userNamesLiveData

    fun getUserNames() {
        _userNamesLiveData.value = repository.getList()

    }
}
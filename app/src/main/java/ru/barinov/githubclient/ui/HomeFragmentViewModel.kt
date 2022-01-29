package ru.barinov.githubclient.ui

import androidx.lifecycle.*
import ru.barinov.githubclient.data.*
import ru.barinov.githubclient.domain.*

class HomeFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: GitNamesRepo,
    private val cacheRepository: LoadedProfileRepository
) : ViewModel() {

    private val _adapterData = MutableLiveData<RecyclerViewAdapter>()
    val adapterData: LiveData<RecyclerViewAdapter> = _adapterData

    private val _dataLoadedLiveData = MutableLiveData<Event<DataResponse>>()
    val dataLoadedLiveData: LiveData<Event<DataResponse>> = _dataLoadedLiveData

    fun getUserNames(adapter: RecyclerViewAdapter) {
        adapter.setItems(repository.getList())
        adapter.setListener(implementListener())
        _adapterData.value = adapter

    }

    private fun implementListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: GitHubUser) {
                loader.loadUserEntityAsync(user.name) { profile ->
                    if (profile != null) {
                        cacheRepository.loadedEntityCache.add(profile)
                        _dataLoadedLiveData.postValue(Event(DataResponse(user.name, true)))
                    } else {
                        _dataLoadedLiveData.postValue(Event(DataResponse(user.name, false)))
                    }
                }
            }
        }
    }
}
package ru.barinov.githubclient.ui

import androidx.lifecycle.*
import ru.barinov.githubclient.data.*
import ru.barinov.githubclient.domain.*
import java.io.IOException

class HomeFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: GitNamesRepo,
    private val cacheRepository: LoadedProfileRepository
) : ViewModel() {

    private val _createdAdapterLiveData = MutableLiveData<ProfilesRecyclerViewAdapter>()
    val createdAdapterLiveData: LiveData<ProfilesRecyclerViewAdapter> = _createdAdapterLiveData

    private val _dataLoadedLiveData = MutableLiveData<Event<DataSearchResponse>>()
    val dataLoadedLiveDataSearch: LiveData<Event<DataSearchResponse>> = _dataLoadedLiveData

    fun getUserNames(adapterProfiles: ProfilesRecyclerViewAdapter) {
        adapterProfiles.setItems(repository.getList())
        adapterProfiles.setListener(implementListener())
        _createdAdapterLiveData.value = adapterProfiles

    }

    private fun implementListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: GitHubUser) {
                try {

                    loader.loadUserEntityAsync(user.name) { profile ->
                        if (profile != null) {
                            cacheRepository.loadedEntityCache.add(profile)
                            _dataLoadedLiveData.postValue(Event(DataSearchResponse(user.name, true)))
                        } else {
                            _dataLoadedLiveData.postValue(Event(DataSearchResponse(user.name, false)))
                        }
                    }
                } catch (e: IOException){

                }
            }
        }
    }
}
package ru.barinov.githubclient.ui

import androidx.lifecycle.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.barinov.githubclient.data.*
import ru.barinov.githubclient.domain.*

class HomeFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: GitNamesRepo,
    private val cacheRepository: LoadedProfileRepository
) : ViewModel() {

    private val _createdAdapterLiveData = MutableLiveData<ProfilesRecyclerViewAdapter>()
    val createdAdapterLiveData: LiveData<ProfilesRecyclerViewAdapter> = _createdAdapterLiveData

    private val _dataLoadedLiveData = MutableLiveData<Event<String>>()
    val dataLoadedLiveDataSearch: LiveData<Event<String>> = _dataLoadedLiveData

    private val _onErrorLiveData = MutableLiveData<Event<Unit>>()
    val onErrorLiveData: LiveData<Event<Unit>> = _onErrorLiveData

    fun getUserNames(adapterProfiles: ProfilesRecyclerViewAdapter) {
        adapterProfiles.setItems(repository.getList())
        adapterProfiles.setListener(sendListener())
        _createdAdapterLiveData.value = adapterProfiles

    }

    private fun sendListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: GitHubUser) {
                loader.loadUserEntityAsync(user.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onError = {
                        _onErrorLiveData.postValue(Event(Unit))
                    },
                        onNext = { profile ->
                        cacheRepository.loadedEntityCache.add(profile)
                        _dataLoadedLiveData.postValue(Event(profile.login))
                    })

            }
        }
    }
}
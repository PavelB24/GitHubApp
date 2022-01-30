package ru.barinov.githubclient.ui

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
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

    private val _onErrorLiveData = MutableLiveData<Event<Unit>>()
    val onErrorLiveData: LiveData<Event<Unit>> = _onErrorLiveData

    fun getData(login: String) {
        loader.loadUserRepositoriesAsync(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    _onErrorLiveData.postValue(Event(Unit))
                },
                onNext = { list ->
                    _dataLoadedLiveData.postValue(DataDetailResponse(list, cacheRepository.getProfileByLogin(login)))
                }
            )

    }
}
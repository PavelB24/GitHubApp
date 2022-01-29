package ru.barinov.githubclient

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.barinov.githubclient.data.*
import ru.barinov.githubclient.domain.GitHubLoader
import ru.barinov.githubclient.ui.HomeFragmentViewModel

val appModule = module {

    single<GitNamesRepo> {
        GitNamesRepo()
    }

    single<GitHubLoader>{
        GitHubLoader()
    }

    single<LoadedProfileRepository>{
        LoadedProfileRepository()
    }



    viewModel<HomeFragmentViewModel>{
        HomeFragmentViewModel(get(), get(), get())
    }
}

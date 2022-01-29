package ru.barinov.githubclient

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {

    single<GitNamesRepo> {
        GitNamesRepo()
    }

    single<GitHubLoader>{
        GitHubLoader()
    }

    viewModel<HomeFragmentViewModel>{
        HomeFragmentViewModel(get(), get())
    }
}

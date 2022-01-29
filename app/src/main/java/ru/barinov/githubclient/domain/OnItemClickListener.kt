package ru.barinov.githubclient.domain

import ru.barinov.githubclient.data.GitHubUser

interface OnItemClickListener {

   fun onItemClick(user: GitHubUser)

}

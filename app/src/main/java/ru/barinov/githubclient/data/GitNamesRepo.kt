package ru.barinov.githubclient.data

import ru.barinov.githubclient.data.GitHubUser

class GitNamesRepo {

    private val list = mutableListOf<GitHubUser>(
        GitHubUser("PavelB24"),
        GitHubUser("svetlanakuro"),
        GitHubUser("DumDumbIchGB")
    )


   fun addUserName(user: GitHubUser){
       list.add(user)
   }

    fun addListOfNames(names: List<GitHubUser>){
        list.addAll(names)
    }

    fun getList(): List<GitHubUser>{
        val copy = mutableListOf<GitHubUser>()
        copy.addAll(list)
        return copy
    }

}

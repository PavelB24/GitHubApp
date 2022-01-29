package ru.barinov.githubclient

import java.util.*

class GitNamesRepo {

    private val list = mutableListOf<GitHubUsersSerializedName>(
        GitHubUsersSerializedName("PavelB24"),
        GitHubUsersSerializedName("svetlanakuro"),
        GitHubUsersSerializedName("DumDumbIchGB")
    )


   fun addUserName(user: GitHubUsersSerializedName){
       list.add(user)
   }

    fun addListOfNames(names: List<GitHubUsersSerializedName>){
        list.addAll(names)
    }

    fun getList(): List<GitHubUsersSerializedName>{
        val copy = mutableListOf<GitHubUsersSerializedName>()
        copy.addAll(list)
        return copy
    }

}

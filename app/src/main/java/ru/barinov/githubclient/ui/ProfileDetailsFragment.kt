package ru.barinov.githubclient.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import java.lang.NullPointerException

private const val argsKey= "LOGIN"

class ProfileDetailsLifeData: Fragment() {

    private var login: String? = ""

    override fun onAttach(context: Context) {
        login = requireArguments().getString(argsKey)
        if (login == null) {
            throw NullPointerException("Login should be provided")
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


















    companion object{
        fun getNewInstance(login: String): Fragment{
            val bundle= Bundle()
            bundle.putString(argsKey, login)
            return Fragment().apply { arguments = bundle }
        }
    }
}
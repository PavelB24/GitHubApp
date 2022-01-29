package ru.barinov.githubclient

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.barinov.githubclient.databinding.*

class HomeFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val viewModel by viewModel<HomeFragmentViewModel>()
    private val adapter= RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserNames()
        binding.recyclerView.adapter = adapter
        viewModel.userNamesLiveData.observe(viewLifecycleOwner){namesList->
            adapter.setItems(namesList)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}

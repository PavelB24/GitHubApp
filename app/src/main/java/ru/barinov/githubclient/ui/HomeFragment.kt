package ru.barinov.githubclient.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.barinov.githubclient.databinding.*
import ru.barinov.githubclient.domain.*

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val viewModel by viewModel<HomeFragmentViewModel>()
    private val adapter = ProfilesRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserNames(adapter)

        viewModel.createdAdapterLiveData.observe(viewLifecycleOwner) { adapter ->
            binding.recyclerView.adapter = adapter
        }

        viewModel.dataLoadedLiveDataSearch.observe(viewLifecycleOwner) { responseEvent ->
            var searchResponse: DataSearchResponse? = null
            responseEvent.getContentIfNotHandled()?.let { responseEvent ->
                searchResponse = responseEvent
            }
            if (searchResponse != null) {
                if (searchResponse!!.isSuccess) {
                    Log.d("@@@@", "onViewCreated: suc")
                    parentFragmentManager.beginTransaction().replace(
                        (requireActivity() as MainActivity).binding.mainContainerForFragments.id,
                        ProfileDetailsFragment.getNewInstance(searchResponse!!.login)
                    ).addToBackStack(null).commit()
                } else if (!searchResponse!!.isSuccess) {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}

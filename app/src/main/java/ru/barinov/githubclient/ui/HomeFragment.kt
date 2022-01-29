package ru.barinov.githubclient.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.barinov.githubclient.data.ProfileDetailsLifeData
import ru.barinov.githubclient.databinding.*
import ru.barinov.githubclient.domain.RecyclerViewAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val viewModel by viewModel<HomeFragmentViewModel>()
    private val adapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserNames(adapter)

        viewModel.adapterData.observe(viewLifecycleOwner) { adapter ->
            binding.recyclerView.adapter = adapter
        }

        viewModel.dataLoadedLiveData.observe(viewLifecycleOwner) { responseEvent ->
            var isSuccess = false
            var login = ""
            responseEvent?.let {
                    responseEvent ->
                isSuccess = responseEvent.peekContent().isSuccess
                login= responseEvent.peekContent().login
            }
            if(isSuccess){
                parentFragmentManager.beginTransaction().add(
                    (requireActivity() as MainActivity).binding.mainContainerForFragments.id,
                    ProfileDetailsLifeData.getNewInstance(login))
                    .addToBackStack(null)
                    .commit()
            }
            else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}

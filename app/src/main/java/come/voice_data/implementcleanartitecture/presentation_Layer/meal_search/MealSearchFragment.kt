package come.voice_data.implementcleanartitecture.presentation_Layer.meal_search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import come.voice_data.implementcleanartitecture.R
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.common.meal_data
import come.voice_data.implementcleanartitecture.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private  val TAG = "MealSearchFragment"
    @Inject
    @Named("fragmentMealSearchAdapter")
    lateinit var searchAdapter: MealSearchAdapter


    private val viewModel: MealSearchViewModel by viewModels()


    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mealSearchRecycler.apply {
            adapter = searchAdapter
        }


        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    viewModel.getSearchMeals(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }

            }
        }


        searchAdapter.itemClickListener {

            meal_data=it
            findNavController().navigate(R.id.action_mealSearchFragment_to_mealDetailsFragment)


            CoroutineScope(Dispatchers.IO).launch {
                Log.e(TAG, "onViewCreated: data  ${it.id}", )
                Log.e(TAG, "onViewCreated: data  ${it.name}", )
                delay(3000)
                Log.e(TAG, "onViewCreated: data  ${it.id}", )
                Log.e(TAG, "onViewCreated: data  ${it.name}", )
                viewModel.my_Data?.invoke(it)
            }

        }

    }

}
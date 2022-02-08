package come.voice_data.implementcleanartitecture.presentation_Layer.meal_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import come.voice_data.implementcleanartitecture.R
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.common.meal_data
import come.voice_data.implementcleanartitecture.databinding.FragmentMealDetailsBinding
import come.voice_data.implementcleanartitecture.presentation_Layer.meal_search.MealSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


/**
 * A simple [Fragment] subclass.
 * Use the [MealDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {
    private  val TAG = "MealDetailsFragment"


    private var _binding: FragmentMealDetailsBinding? = null
    val binding: FragmentMealDetailsBinding
        get() = _binding!!
    private val my_viewModel: MealSearchViewModel by activityViewModels()

    private val viewModel: MealDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.e(TAG, "onViewCreated:  ${meal_data?.name}", )
        Log.e(TAG, "onViewCreated:  ${meal_data?.id}", )
        Log.e(TAG, "onViewCreated:  ${meal_data?.image}", )
        my_viewModel.my_Data={
            Log.e(TAG, "onViewCreated:  ${it.name}", )
            Log.e(TAG, "onViewCreated:  ${it.id}", )
            Log.e(TAG, "onViewCreated:  ${it.image}", )
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        meal_data?.id?.let {
            Log.e(TAG, "onViewCreated: id  ${it}", )
            viewModel.getMealDetails(it) 
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetails.collect {
                if (it.isLoading) {
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(),it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.mealDetails = it
                }
            }
        }
    }

}
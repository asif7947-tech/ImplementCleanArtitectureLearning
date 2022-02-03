package come.voice_data.implementcleanartitecture.presentation_Layer.meal_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import come.voice_data.implementcleanartitecture.R
import come.voice_data.implementcleanartitecture.databinding.FragmentMealDetailsBinding
import come.voice_data.implementcleanartitecture.presentation_Layer.meal_search.MealSearchViewModel
import dagger.hilt.android.AndroidEntryPoint


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
        return inflater.inflate(R.layout.fragment_meal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        my_viewModel.my_Data={
            Log.e(TAG, "onViewCreated:  ${it.name}", )
            Log.e(TAG, "onViewCreated:  ${it.id}", )
            Log.e(TAG, "onViewCreated:  ${it.image}", )
        }
    }

}
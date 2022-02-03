package come.voice_data.implementcleanartitecture.presentation_Layer.meal_detail

import androidx.lifecycle.ViewModel
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.domain_Layer.use_cases.GetMealDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: GetMealDetailsUseCase):ViewModel() {

    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails

    suspend fun getMealDetails(id: String) {
        mealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data?.get(0))
                }
            }
        }
    }
}
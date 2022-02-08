package come.voice_data.implementcleanartitecture.presentation_Layer.meal_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.domain_Layer.use_cases.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val mealDetailsUseCase: GetMealDetailsUseCase):ViewModel() {

    private val _mealDetails = MutableStateFlow(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails

     fun getMealDetails(id: String) {
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
        }.launchIn(viewModelScope)
    }
}
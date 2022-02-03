package come.voice_data.implementcleanartitecture.presentation_Layer.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.domain_Layer.model.Meal
import come.voice_data.implementcleanartitecture.domain_Layer.use_cases.GetMealsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val mealsSearchUseCase: GetMealsSearchUseCase
) : ViewModel() {

     var my_Data:((Meal)->Unit)?=null

    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList: StateFlow<MealSearchState> = _mealSearchList

    fun getSearchMeals(s: String) {
        mealsSearchUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _mealSearchList.value = MealSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}
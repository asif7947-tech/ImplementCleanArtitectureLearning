package come.voice_data.implementcleanartitecture.presentation_Layer.meal_search

import come.voice_data.implementcleanartitecture.domain_Layer.model.Meal

data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)

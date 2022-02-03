package come.voice_data.implementcleanartitecture.presentation_Layer.meal_detail
import come.voice_data.implementcleanartitecture.domain_Layer.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
) {
}
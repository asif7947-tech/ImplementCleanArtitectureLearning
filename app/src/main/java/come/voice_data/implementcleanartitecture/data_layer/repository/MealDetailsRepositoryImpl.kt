package come.voice_data.implementcleanartitecture.data_layer.repository

import com.gaur.mealsearch.domain.repository.MealDetailsRepository
import come.voice_data.implementcleanartitecture.data_layer.model.MealsDTO
import come.voice_data.implementcleanartitecture.data_layer.remote.MealSearchAPI

class MealDetailsRepositoryImpl(private val mealSearchAPI: MealSearchAPI):MealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}
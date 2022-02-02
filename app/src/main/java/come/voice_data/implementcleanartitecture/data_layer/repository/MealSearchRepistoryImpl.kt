package come.voice_data.implementcleanartitecture.data_layer.repository

import com.gaur.mealsearch.domain.repository.MealSearchRepository
import come.voice_data.implementcleanartitecture.data_layer.model.MealsDTO
import come.voice_data.implementcleanartitecture.data_layer.remote.MealSearchAPI

class MealSearchRepistoryImpl(private val mealSearchAPI: MealSearchAPI):MealSearchRepository {
    override suspend fun getMealSearch(s: String): MealsDTO {
       return mealSearchAPI.getSearchMealList(s)
    }


}
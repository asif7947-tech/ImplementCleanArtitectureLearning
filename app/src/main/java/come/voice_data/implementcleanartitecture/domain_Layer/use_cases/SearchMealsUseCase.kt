package come.voice_data.implementcleanartitecture.domain_Layer.use_cases

import com.gaur.mealsearch.domain.repository.MealSearchRepository
import javax.inject.Inject

class SearchMealsUseCase @Inject constructor(private val repository: MealSearchRepository) {

}
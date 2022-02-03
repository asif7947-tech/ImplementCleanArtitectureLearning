package come.voice_data.implementcleanartitecture.domain_Layer.use_cases

import com.gaur.mealsearch.domain.repository.MealDetailsRepository
import come.voice_data.implementcleanartitecture.common.Resource
import come.voice_data.implementcleanartitecture.data_layer.model.toDomainMealDetails
import come.voice_data.implementcleanartitecture.domain_Layer.model.MealDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(val repository: MealDetailsRepository) {

    operator fun invoke(id: String): Flow<Resource<List<MealDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(id)
            val domainData =
                if (!data.meals.isNullOrEmpty()) data.meals.map { it -> it.toDomainMealDetails() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.localizedMessage ?: " Exception Bug"))
        }
    }
}
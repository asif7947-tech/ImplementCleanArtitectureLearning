package come.voice_data.implementcleanartitecture.domain_Layer.use_cases

import come.voice_data.implementcleanartitecture.common.Resource
import com.gaur.mealsearch.domain.repository.MealSearchRepository
import come.voice_data.implementcleanartitecture.data_layer.model.toDomainMeal
import come.voice_data.implementcleanartitecture.data_layer.model.toDomainMealDetails
import come.voice_data.implementcleanartitecture.domain_Layer.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMealsSearchUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s:String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val response =repository.getMealSearch(s)

            val domainData =
                if (!response.meals.isNullOrEmpty()) response.meals.map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))

        }catch(e:HttpException ){
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))

        }catch (ex:IOException){
            emit(Resource.Error(message = ex.localizedMessage ?: "Check Connectivity"))

        }catch (ex:Exception){
            emit(Resource.Error(message = ex.localizedMessage ?: " Exception Bug"))
        }
    }

}
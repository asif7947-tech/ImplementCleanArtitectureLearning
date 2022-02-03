package come.voice_data.implementcleanartitecture.hilt

import com.gaur.mealsearch.common.Constants
import com.gaur.mealsearch.domain.repository.MealDetailsRepository
import com.gaur.mealsearch.domain.repository.MealSearchRepository
import come.voice_data.implementcleanartitecture.data_layer.remote.MealSearchAPI
import come.voice_data.implementcleanartitecture.data_layer.repository.MealDetailsRepositoryImpl
import come.voice_data.implementcleanartitecture.data_layer.repository.MealSearchRepistoryImpl
import come.voice_data.implementcleanartitecture.presentation_Layer.meal_search.MealSearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HIltModules {


    @Provides
    @Singleton
    fun provideMealSearchApi():MealSearchAPI{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI):MealSearchRepository
    {
        return MealSearchRepistoryImpl( mealSearchAPI =mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(searchMealSearchAPI: MealSearchAPI): MealDetailsRepository {
        return MealDetailsRepositoryImpl(searchMealSearchAPI)
    }



}
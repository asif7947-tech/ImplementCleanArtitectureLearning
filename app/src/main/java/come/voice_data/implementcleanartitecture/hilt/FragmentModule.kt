package come.voice_data.implementcleanartitecture.hilt

import come.voice_data.implementcleanartitecture.presentation_Layer.meal_search.MealSearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {
    @FragmentScoped
    @Provides
    @Named("fragmentMealSearchAdapter")
    fun provideMealSearchAdapter(): MealSearchAdapter {
        return MealSearchAdapter()
    }

}
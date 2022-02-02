package com.gaur.mealsearch.domain.repository

import come.voice_data.implementcleanartitecture.data_layer.model.MealsDTO

interface MealDetailsRepository {

    suspend fun getMealDetails(id:String): MealsDTO

}
package com.gaur.mealsearch.domain.repository


import come.voice_data.implementcleanartitecture.data_layer.model.MealsDTO

interface MealSearchRepository {

    suspend fun getMealSearch(s:String): MealsDTO



}
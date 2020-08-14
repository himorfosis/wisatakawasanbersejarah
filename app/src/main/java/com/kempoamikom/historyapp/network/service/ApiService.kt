package com.kempoamikom.historyapp.network.service

import com.kempoamikom.historyapp.entity.FacilitiesEntity
import com.kempoamikom.historyapp.entity.QuizEntity
import com.kempoamikom.historyapp.entity.SiteEntity
import com.kempoamikom.historyapp.model.FacilitiesModel
import com.kempoamikom.historyapp.model.QuizModel
import com.kempoamikom.historyapp.model.SiteModel
import com.kempoamikom.historyapp.model.SumberModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Accept: application/json")
    @GET("api.php?get=situs&output=json")
    fun fetchSitus(
    ): Observable<List<SiteModel>>

    @Headers("Accept: application/json")
    @GET("api.php?get=facilities&output=json")
    fun fetchFacilities(
    ): Observable<List<FacilitiesModel>>

    @GET("api.php?get=sumber&output=json")
    fun fetchSumber(
    ): Observable<List<SumberModel>>

    @GET("api.php?get=quiz&output=json")
    fun fetchQuiz(
    ): Observable<List<QuizModel>>


}
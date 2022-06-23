package org.d3if2024.assesment2.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfacePenemuSuhu {
    @GET("v1/d949d965-5dae-45f1-8d4f-a0edbe4b83ad")
    fun getData(): Call<List<DataPenemuSuhuItem>>
}
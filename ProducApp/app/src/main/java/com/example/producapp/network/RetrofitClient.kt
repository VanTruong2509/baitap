package com.example.producapp.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String
)

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

object ApiClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}

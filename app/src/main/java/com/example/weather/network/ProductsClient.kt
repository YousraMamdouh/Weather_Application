//package com.example.weather.network
//
//
//
//class ProductsClient private constructor(): RemoteSource {
//    val productsService: ApiService by lazy {
//        RetrofitHelper.retrofitInstance.create(ApiService::class.java)
//    }
//
//    override suspend fun getProductsOverNetwork():List<Products>{
//        val response=productsService.getProducts()
//        return response.products
//    }
//
//    companion object{
//        private var instance: ProductsClient?=null
//        fun getInstance(): ProductsClient {
//            return instance?: synchronized(this)
//            {
//                val temp= ProductsClient()
//                instance=temp
//                temp
//            }
//        }
//    }
//}
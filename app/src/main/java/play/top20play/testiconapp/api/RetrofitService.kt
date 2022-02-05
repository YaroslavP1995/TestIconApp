package play.top20play.testiconapp.api

import okhttp3.OkHttpClient
import play.top20play.testiconapp.data.BaseData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v2/tagged?api_key=CcEqqSrYdQ5qTHFWssSMof4tPZ89sfx6AXYNQ4eoXHMgPJE03U")
    suspend fun updateCampaign(@Query("tag") tag: String): Response<BaseData>

    companion object {

        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.tumblr.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

        fun getClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.interceptors().add(LoggingInterceptor())
            val client = builder.build()
            return client
        }

    }
}
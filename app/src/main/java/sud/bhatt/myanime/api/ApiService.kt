package sud.bhatt.myanime.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {


    private const val BASE_URL = "https://api.jikan.moe/v3/search/"
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory()
        .client(client)
        .build()


    fun <T> buildAPIService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
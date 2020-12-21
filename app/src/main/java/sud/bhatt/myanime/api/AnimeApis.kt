package sud.bhatt.myanime.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sud.bhatt.myanime.data.MyAnimeData
import sud.bhatt.myanime.data.MyAnimeDataList

interface AnimeApis {


    @GET("anime?q=naruto")
    fun getAnimeList(): Call<MyAnimeDataList>

    @GET("anime")
    fun searchAnimeList(@Query("q") search:String): Call<MyAnimeDataList>

}
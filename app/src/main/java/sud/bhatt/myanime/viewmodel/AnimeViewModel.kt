package sud.bhatt.myanime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sud.bhatt.myanime.api.AnimeApis
import sud.bhatt.myanime.api.ApiService
import sud.bhatt.myanime.data.MyAnimeData
import sud.bhatt.myanime.data.MyAnimeDataList

class AnimeViewModel : ViewModel() {


    private val animeList: MutableLiveData<MyAnimeDataList> = MutableLiveData()
    private val failure: MutableLiveData<Boolean> = MutableLiveData()
    private val apiService: ApiService = ApiService


    init {
        fetchAnimeList()
    }

    private fun fetchAnimeList() {
        val service = apiService.buildAPIService(AnimeApis::class.java)
        val call = service.getAnimeList()
        call.enqueue(object : Callback<MyAnimeDataList> {
            override fun onResponse(
                call: Call<MyAnimeDataList>,
                response: Response<MyAnimeDataList>
            ) {
                animeList.value = response.body()
            }

            override fun onFailure(call: Call<MyAnimeDataList>, t: Throwable) {
                failure.value = true;
            }
        })

    }

    fun getAnimes(): LiveData<MyAnimeDataList> {

        return animeList
    }

    fun getFailure(): LiveData<Boolean> {
        return failure
    }

    fun setSearchQuery(search: String) {
        val service = apiService.buildAPIService(AnimeApis::class.java)
        val call = service.searchAnimeList(search)
        call.enqueue(object : Callback<MyAnimeDataList> {
            override fun onResponse(
                call: Call<MyAnimeDataList>,
                response: Response<MyAnimeDataList>
            ) {
                if(response.body()!=null)
                animeList.value = response.body()
            }

            override fun onFailure(call: Call<MyAnimeDataList>, t: Throwable) {
                failure.value = true;
            }
        })
    }

}
package sud.bhatt.myanime.data

import com.google.gson.annotations.SerializedName

class MyAnimeDataList(@SerializedName("results") val animeData: List<MyAnimeData>) {


}
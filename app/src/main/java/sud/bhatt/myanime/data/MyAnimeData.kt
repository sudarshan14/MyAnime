package sud.bhatt.myanime.data

import com.google.gson.annotations.SerializedName

data class MyAnimeData(
    @SerializedName("title") val animeTitle: String,
    @SerializedName("image_url") val animeImageUrl: String,
    @SerializedName("score") val animeRating: Double,

    ) {
}


package sud.bhatt.myanime.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sud.bhatt.myanime.data.MyAnimeData

class AnimeDataAdapter(val anmieData: List<MyAnimeData>) :
    RecyclerView.Adapter<AnimeDataAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeDataAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimeDataAdapterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AnimeDataAdapterViewHolder, position: Int) {
        val animeName: String? = anmieData[position].animeTitle
        val animeImageUrl: String? = anmieData[position].animeImageUrl
        holder.bind(animeName, animeImageUrl)
    }

    override fun getItemCount(): Int {
        return anmieData.size
    }
}
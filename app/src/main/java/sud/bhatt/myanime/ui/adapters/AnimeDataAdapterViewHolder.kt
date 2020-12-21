package sud.bhatt.myanime.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import sud.bhatt.myanime.R

class AnimeDataAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.anime_view_item, parent, false
        )
    ) {
    private var animeName: TextView? = null
    private var animeImage: ImageView? = null

    init {
        animeName = itemView.findViewById(R.id.name)
        animeImage = itemView.findViewById(R.id.image)

    }

    fun bind(name: String?, imageUrl: String?) {
        animeName?.text = name
//        Picasso.with().load(imageUrl).into(animeImage)

        Picasso.get()
            .load(imageUrl)
//            .placeholder(R.drawable.ic_launcher_background)
//            .error(R.drawable.ic_launcher_background)
//            .fit()
//            .priority(Picasso.Priority.HIGH)
            .into(animeImage)
    }
}
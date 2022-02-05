package play.top20play.testiconapp.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import play.top20play.testiconapp.R
import play.top20play.testiconapp.data.NeededData
import play.top20play.testiconapp.util.extensions.initGlide

class ListItemAdapter(
    private val list: ArrayList<NeededData>,
    private val listener: CardItemListener,
) :
    RecyclerView.Adapter<ListItemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icPreview: ImageView = itemView.findViewById(R.id.ic_preview)
        val nameBlog: TextView = itemView.findViewById(R.id.nameBlog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_icon, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameBlog.text = list[position].blog_name

        initGlide(
            holder.icPreview.context,
            list[position].post_url,
            300.0f,
            R.drawable.ic_launcher_background
        ) { drawable -> holder.icPreview.setImageDrawable(drawable) }

        holder.icPreview.setOnClickListener {
            listener.onCardItemClickListener(list[position].post_url)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CardItemListener {
        fun onCardItemClickListener(url: String)
    }
}
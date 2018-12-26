package sample.gopi.com.fastapplication.imagegameguess.adapter

import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import sample.gopi.com.fastapplication.R
import sample.gopi.com.fastapplication.imagegameguess.pojos.Game

class GameGuessAdapter(var itemList: MutableList<Game>, val handler: Handler) : RecyclerView.Adapter<GameGuessAdapter.Companion.GameViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): GameViewHolder {
        return GameViewHolder((LayoutInflater.from(viewGroup.context).inflate(R.layout.game_item, viewGroup, false)))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(viewHolder: GameViewHolder, position: Int) {
        val context = viewHolder.gameIv.context
        val item = itemList[position]
        val imgUrl: String? = item.media?.m ?: ""
        if (item.showState) {
            Glide.with(context).load(imgUrl).into(viewHolder.gameIv)
        } else {
            Glide.with(context).load(ContextCompat.getDrawable(context, R.drawable.grey_drawable)).into(viewHolder.gameIv)
        }
        viewHolder.itemView.setOnClickListener { flipItem(position) }
    }


    fun updateItemList(updatedList: List<Game>) {
        itemList.clear()
        itemList.addAll(updatedList)
        notifyDataSetChanged()
    }

    fun flipAllItemsToGrey() = itemList.forEach { it.showState = false }

    fun flipItem(position: Int) {
        itemList[position].showState = true
        notifyItemChanged(position)
        handler.postDelayed({ itemList[position].showState = false ; notifyItemChanged(position) }, 2000)
    }

    companion object {
        class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var gameIv: ImageView = view.findViewById(R.id.gameIv)
        }
    }
}



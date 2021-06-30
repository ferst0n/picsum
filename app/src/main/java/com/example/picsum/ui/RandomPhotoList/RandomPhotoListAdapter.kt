package com.example.picsum.ui.RandomPhotoList

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.picsum.R
import com.example.picsum.data.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class RandomPhotoListAdapter(diffCallback: DiffUtil.ItemCallback<Image>) :
        PagingDataAdapter<Image, RandomPhotoListAdapter.ImageViewHolder>(diffCallback) {



    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)
        return ImageViewHolder(itemView)
    }

    fun likeImage(position:Int): Image? {
        notifyDataSetChanged()

        val image = getItem(position)
        return image
    }

    fun deleteImageFromUI(position: Int):Image?{

        val image = getItem(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()

        return image
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ImageViewHolder(private val view: View): RecyclerView.ViewHolder(view) {


        fun bind(img: Image){
            Picasso.get()
                    .load(img.download_url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(view.imv_item)
        }
    }


}
object ItemComparator: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image)
            = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image)
            = oldItem == newItem
}
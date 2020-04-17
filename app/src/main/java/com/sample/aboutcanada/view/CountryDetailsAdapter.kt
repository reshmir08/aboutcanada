package com.sample.aboutcanada.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sample.aboutcanada.R
import com.sample.aboutcanada.model.entity.Rows
import kotlinx.android.synthetic.main.item_country_details.view.*

/**
 * This adapter is used to bind the country details to the recycler view
 */
class CountryDetailsAdapter(private val items: List<Rows>, private val context: Context) :
    RecyclerView.Adapter<CountryDetailsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewTitle = view.tv_item_country_details_title
        private val textViewDesc = view.tv_item_country_details_desc
        private val imageViewThumb = view.iv_item_country_details_thumb

        fun bind(rows: Rows, context: Context) {
            textViewTitle.text = rows.title
            textViewDesc.text = rows.description

            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(100, 100)

            val glide = Glide.with(context).load(rows.imageHref).apply(requestOptions)
            glide.into(imageViewThumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_country_details,
                parent,
                false
            )
        )
    }
}
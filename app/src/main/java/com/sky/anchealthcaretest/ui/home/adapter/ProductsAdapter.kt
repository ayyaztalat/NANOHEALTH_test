package com.sky.anchealthcaretest.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sky.anchealthcaretest.databinding.AllProducstAdapterBinding
import com.sky.anchealthcaretest.interfaces.ItemClicked
import com.sky.anchealthcaretest.model.GetAllProducts

class ProductsAdapter(val callback: ItemClicked) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var arrayList = ArrayList<GetAllProducts>()
    private var context: Context? = null

    class ViewHolder(viewbinding: AllProducstAdapterBinding) :
        RecyclerView.ViewHolder(viewbinding.root) {
        val imageMain = viewbinding.imageMain
        val cardImage =viewbinding.cardImage
        val price = viewbinding.price
        val rating = viewbinding.rating
        val productTitle = viewbinding.productTitle
        val description = viewbinding.description
    }

    fun updateAdapter(arrayList: ArrayList<GetAllProducts>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    fun getContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AllProducstAdapterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {

            holder.price.text = "${arrayList[position].getPrice()} AED"
            holder.productTitle.text = arrayList[position].getTitle()
            holder.description.text = arrayList[position].getDescription()
            holder.itemView.setOnClickListener {
                arrayList[position].getId()?.let { it1 -> callback.itemClicked(it1) }
            }

            arrayList[position].getRating()?.let {
                it.rate?.let {rate->
                    holder.rating.rating = rate
                }

            }

            Glide.with(context!!).load(arrayList[position].getImage()).into(holder.imageMain)
        } catch (
            e: Exception
        ) {
            e.printStackTrace()
        }
    }

}
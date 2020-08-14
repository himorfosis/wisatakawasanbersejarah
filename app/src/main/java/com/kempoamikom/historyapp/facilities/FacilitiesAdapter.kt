package com.kempoamikom.historyapp.facilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.entity.FacilitiesEntity
import com.kempoamikom.historyapp.model.FacilitiesModel
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.main.item_facilities.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class FacilitiesAdapter : RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>() {

    private var listData: MutableList<FacilitiesModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_facilities, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listData!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = listData[position]
        val context = holder.itemView.context

        data.let {

            holder.titleTv.text = it.name
            holder.locationTv.text = "${it.address}, ${it.address}"

            print("foto site : ${data.image}")

            Glide.with(context)
                .load(AppNetwork.URL_IMAGE + "fasilitas/${data.image}")
                .thumbnail(0.1f)
                .error(R.drawable.ic_broken_image)
                .into(holder.restaurantImg)

            holder.itemView.onClick {
                context.startActivity(
                    context.intentFor<FacilitiesDetail>(
                        "id" to data.id,
                        "address" to data.address,
                        "name" to data.name,
                        "description" to data.description,
                        "latitude" to data.latitude,
                        "longtitude" to data.longitude,
                        "foto" to data.image
                    )
                )
            }

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTv = itemView.restaurantTitleTv
        val locationTv = itemView.locationTv
        val restaurantImg = itemView.restaurantImg

    }

    private fun add(data: FacilitiesModel) {
        listData!!.add(data)
        notifyItemInserted(listData!!.size - 1)
    }

    fun addAll(data: List<FacilitiesModel>) {
        data.forEach {
            add(it)
        }
    }

}
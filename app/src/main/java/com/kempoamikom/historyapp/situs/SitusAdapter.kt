package com.kempoamikom.historyapp.situs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.entity.SiteEntity
import com.kempoamikom.historyapp.model.SiteModel
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.main.item_facilities.view.*
import kotlinx.android.synthetic.main.item_situs.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class SitusAdapter : RecyclerView.Adapter<SitusAdapter.ViewHolder>() {

    private var listData: MutableList<SiteModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_situs, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listData!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = listData[position]
        val context = holder.itemView.context

        data.let {

            holder.placeTitleTv.text = it.name
            holder.locationTv.text = "${it.address}"

            print("foto site : ${data.image_site}")

            Glide.with(context)
                .load(AppNetwork.URL_IMAGE + "situs/${data.image_site}")
                .thumbnail(0.1f)
                .error(R.drawable.ic_broken_image)
                .into(holder.itemPlaceImg)

            holder.itemView.onClick {

                context.startActivity(
                    context.intentFor<SitusDetail>(
                        "id" to data.id,
                        "address" to data.address,
                        "name" to data.name,
                        "description" to data.description,
                        "latitude" to data.latitude,
                        "longtitude" to data.longitude,
                        "video" to data.link_youtube,
                        "panorama" to data.image_panorama,
                        "foto" to data.image_site
                    )
                )
            }

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val placeTitleTv = itemView.placeTitleTv
        val locationTv = itemView.placeLocationTv
        val itemPlaceImg = itemView.itemPlaceImg

    }

    private fun add(data: SiteModel) {
        listData!!.add(data)
        notifyItemInserted(listData!!.size - 1)
    }

    fun addAll(data: List<SiteModel>) {
        data.forEach {
            add(it)
        }
    }


}
package com.kempoamikom.historyapp.sumber

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kempoamikom.historyapp.R
import com.kempoamikom.historyapp.entity.SiteEntity
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.kempoamikom.historyapp.model.FacilitiesModel
import com.kempoamikom.historyapp.model.SumberModel
import com.kempoamikom.historyapp.network.AppNetwork
import kotlinx.android.synthetic.main.item_sumber.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class SumberAdapter : RecyclerView.Adapter<SumberAdapter.ViewHolder>() {

    private var listData: MutableList<SumberModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_sumber, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = listData[position]
        val context = holder.itemView.context

        data.let {

            holder.titleTv.text = it.title
            holder.uploadTv.text = "Upload tanggal ${it.upload_date}"

            Glide.with(context)
                .load(AppNetwork.URL_IMAGE + "sumber/${it.gambar}")
                .thumbnail(0.1f)
                .error(R.drawable.ic_broken_image)
                .into(holder.sumberImg)

            holder.itemView.onClick {
                context.startActivity(
                    context.intentFor<SumberDetail>(
                        "url" to data.file_url,
                        "title" to data.title,
                        "upload" to data.upload_date
                    )
                )
            }

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTv = itemView.sumberTitleTv
        val uploadTv = itemView.sumberUploadTv
        val sumberImg = itemView.sumberImg
    }

    private fun add(data: SumberModel) {
        listData!!.add(data)
        notifyItemInserted(listData!!.size - 1)
    }

    fun addAll(data: List<SumberModel>) {
        data.forEach {
            add(it)
        }
    }

}
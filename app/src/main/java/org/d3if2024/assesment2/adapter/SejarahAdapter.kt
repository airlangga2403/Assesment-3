package org.d3if2024.assesment2.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_items.view.*
import org.d3if2024.assesment2.R
import org.d3if2024.assesment2.network.DataPenemuSuhuItem

class SejarahAdapter(val context: Context, val userList: List<DataPenemuSuhuItem>) :
    RecyclerView.Adapter<SejarahAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var extract: TextView

        init {
            title = itemView.title
            extract = itemView.extract
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = userList[position].title
        holder.extract.text = userList[position].extract
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
package net.azarquiel.examenandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_lugar.view.*
import net.azarquiel.examenandroid.model.Lugar

class CustomAdapterLugares(val context: Context,
                              val layout: Int
) : RecyclerView.Adapter<CustomAdapterLugares.ViewHolder>() {

    private var dataList: List<Lugar> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setLugares(lugares: List<Lugar>) {
        this.dataList = lugares
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Lugar){
            itemView.tvRowLugarNombre.text = dataItem.titre
            itemView.tvRowLugarDescripcion.text = dataItem.description_es
            itemView.tag = dataItem
        }

    }
}
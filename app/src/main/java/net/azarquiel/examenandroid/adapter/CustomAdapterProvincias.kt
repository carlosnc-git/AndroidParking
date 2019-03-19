package net.azarquiel.examenandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_comunidad.view.*
import kotlinx.android.synthetic.main.row_provincia.view.*
import net.azarquiel.examenandroid.model.Comunidad
import net.azarquiel.examenandroid.model.Provincia
import org.jetbrains.anko.imageResource

class CustomAdapterProvincias(val context: Context,
                               val layout: Int
) : RecyclerView.Adapter<CustomAdapterProvincias.ViewHolder>() {

    private var dataList: List<Provincia> = emptyList()
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

    internal fun setProvincias(provincias: List<Provincia>) {
        this.dataList = provincias
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Provincia){
            itemView.tvProvincia.text = dataItem.nombre
            itemView.tag = dataItem
        }

    }
}
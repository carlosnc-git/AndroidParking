package net.azarquiel.examenandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_comunidad.view.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.model.Comunidad
import org.jetbrains.anko.imageResource

class CustomAdapterComunidades(val context: Context,
                   val layout: Int
) : RecyclerView.Adapter<CustomAdapterComunidades.ViewHolder>() {

    private var dataList: List<Comunidad> = emptyList()
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

    internal fun setComunidades(comunidades: List<Comunidad>) {
        this.dataList = comunidades
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Comunidad){
            itemView.ivComunidad.imageResource = context.resources.getIdentifier("ccaa${dataItem.id}", "drawable", context.packageName)
            itemView.tvComunidad.text = dataItem.nombre
            itemView.tag = dataItem
        }

    }
}
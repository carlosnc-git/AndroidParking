package net.azarquiel.examenandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_comunidad.view.*
import kotlinx.android.synthetic.main.row_municipio.view.*
import net.azarquiel.examenandroid.model.Comunidad
import net.azarquiel.examenandroid.model.Municipio
import org.jetbrains.anko.imageResource

class CustomAdapterMunicipios(val context: Context,
                               val layout: Int
) : RecyclerView.Adapter<CustomAdapterMunicipios.ViewHolder>() {

    private var dataList: List<Municipio> = emptyList()
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

    internal fun setMunicipios(municipios: List<Municipio>) {
        this.dataList = municipios
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Municipio){
            itemView.tvMunicipio.text = dataItem.nombre
            itemView.tag = dataItem
        }

    }
}
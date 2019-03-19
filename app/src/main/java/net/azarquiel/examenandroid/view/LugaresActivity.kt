package net.azarquiel.examenandroid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterMunicipios
import net.azarquiel.examenandroid.viewmodel.MainViewModel
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_lugares.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.examenandroid.adapter.CustomAdapterComunidades
import net.azarquiel.examenandroid.adapter.CustomAdapterLugares
import net.azarquiel.examenandroid.model.*
import org.jetbrains.anko.toast

class LugaresActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapterLugares
    private lateinit var viewModel: MainViewModel
    private lateinit var municipio: Municipio
    private var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)
        municipio = intent.getSerializableExtra("municipio") as Municipio
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        title = "Lugares (${municipio.nombre})"
        loadAdapter()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getLugares(latitud = municipio.latitud, longitud = municipio.longitud).observe(this, Observer {
            it?.let(adapter::setLugares)
        })
    }

    private fun loadAdapter() {
        adapter = CustomAdapterLugares(this, R.layout.row_lugar)
        rvLugares.layoutManager = LinearLayoutManager(this)
        rvLugares.adapter = adapter
    }

    fun clickLugar(view: View){
        val intent = Intent(this, LugarActivity::class.java)
        usuario?.let {
            intent.putExtra("lugar", (view.tag as Lugar))
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }?:let { toast("Debes estar logeado para poder acceder") }


    }
}

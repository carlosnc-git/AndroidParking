package net.azarquiel.examenandroid.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_provincias.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterComunidades
import net.azarquiel.examenandroid.adapter.CustomAdapterProvincias
import net.azarquiel.examenandroid.viewmodel.MainViewModel
import android.arch.lifecycle.Observer
import android.content.Intent
import android.view.View
import net.azarquiel.examenandroid.model.Comunidad
import net.azarquiel.examenandroid.model.Provincia
import net.azarquiel.examenandroid.model.Usuario

class ProvinciasActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapterProvincias
    private lateinit var viewModel: MainViewModel
    private lateinit var comunidad: Comunidad
    private var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincias)
        comunidad = intent.getSerializableExtra("comunidad") as Comunidad
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        title = "Provincias (${comunidad.nombre})"
        loadAdapter()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //gets the list of Provincia of a Comunidad, gives it to the adapter
        viewModel.getProvincias(comunidad.id).observe(this, Observer {
            it?.let(adapter::setProvincias)
        })
    }
    //initialize the adapter
    private fun loadAdapter(){
        adapter = CustomAdapterProvincias(this, R.layout.row_provincia)
        rvProvincias.layoutManager = LinearLayoutManager(this)
        rvProvincias.adapter = adapter
    }
    //onclick method for each row/viewholder
    fun clickProvincia(view: View){
        val intent = Intent(this, MunicipiosActivity::class.java)
        intent.putExtra("provincia", (view.tag as Provincia))
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }
}

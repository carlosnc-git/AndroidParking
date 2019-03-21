package net.azarquiel.examenandroid.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_municipios.*
import kotlinx.android.synthetic.main.content_municipios.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterMunicipios
import net.azarquiel.examenandroid.model.Municipio
import net.azarquiel.examenandroid.model.Provincia
import net.azarquiel.examenandroid.model.Usuario
import net.azarquiel.examenandroid.viewmodel.MainViewModel

class MunicipiosActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchView: SearchView
    private lateinit var adapter: CustomAdapterMunicipios
    private lateinit var municipios: List<Municipio>
    private lateinit var viewModel: MainViewModel
    private lateinit var provincia: Provincia
    private var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_municipios)
        setSupportActionBar(toolbar)
        provincia = intent.getSerializableExtra("provincia") as Provincia
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        title = "Municipios (${provincia.nombre})"
        loadAdapter()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //gets the list of Municipios for the given Provincia, saves it for filtering and gives it to the adapter
        viewModel.getMunicipios(provincia.id).observe(this, Observer {
            it?.let{
                municipios=it
                adapter.setMunicipios(municipios)
            }
        })
    }
    //initialize the adapter
    private fun loadAdapter() {
        adapter = CustomAdapterMunicipios(this, R.layout.row_municipio)
        rvMunicipios.layoutManager = LinearLayoutManager(this)
        rvMunicipios.adapter = adapter
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflates the menu with the searchview
        menuInflater.inflate(R.menu.menu_municipios, menu)
        val searchItem = menu.findItem(R.id.sbTapas)
        searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar..."
        searchView.setOnQueryTextListener(this)
        return true
    }
    //filters the list municipios to only show those which match the given string
    private fun filtrar(filtro:String){
        adapter.setMunicipios(municipios.filter { m -> m.nombre.toUpperCase().contains(filtro.toUpperCase())})
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filtrar(newText!!)
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
    //onclick action for each row/viewholder
    fun clickMunicipio(view: View){
        val intent = Intent(this, LugaresActivity::class.java)
        intent.putExtra("municipio", (view.tag as Municipio))
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

}

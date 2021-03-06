package net.azarquiel.examenandroid.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_lugares.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterLugares
import net.azarquiel.examenandroid.model.Lugar
import net.azarquiel.examenandroid.model.Municipio
import net.azarquiel.examenandroid.model.Usuario
import net.azarquiel.examenandroid.viewmodel.MainViewModel
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
        //gets the Lugares list for the given Municipio and passes them to the adapter
        viewModel.getLugares(latitud = municipio.latitud, longitud = municipio.longitud).observe(this, Observer {
            it?.let(adapter::setLugares)
        })
    }
    //initialize the adapter
    private fun loadAdapter() {
        adapter = CustomAdapterLugares(this, R.layout.row_lugar)
        rvLugares.layoutManager = LinearLayoutManager(this)
        rvLugares.adapter = adapter
    }
    //onclick action for each row/viewholder
    fun clickLugar(view: View){
        //only if user is logged (so usuario!=null) will be able to navigate to the new activity
        usuario?.let {
            val intent = Intent(this, LugarActivity::class.java)
            intent.putExtra("lugar", (view.tag as Lugar))
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }?:let { toast("Debes estar logeado para poder acceder") }


    }
}

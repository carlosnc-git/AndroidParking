package net.azarquiel.examenandroid.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lugar.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterLugares
import net.azarquiel.examenandroid.model.Lugar
import net.azarquiel.examenandroid.model.Municipio
import net.azarquiel.examenandroid.model.Usuario
import net.azarquiel.examenandroid.viewmodel.MainViewModel
import android.arch.lifecycle.Observer
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lugar.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast

class LugarActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var lugar: Lugar
    private lateinit var usuario: Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugar)
        lugar = intent.getSerializableExtra("lugar") as Lugar
        usuario = intent.getSerializableExtra("usuario") as Usuario
        title = lugar.titre
        tvLugarNombre.text = lugar.titre
        tvLugarDescripcion.text = lugar.description_es
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getFotos(lugar.id).observe(this, Observer {
            it?.let{
                for (foto in it) {
                    var iv = ImageView(this)
                    var lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    iv.layoutParams = lp
                    Picasso.with(this).load(foto.link_large).placeholder(R.drawable.placeholder).fit().centerInside().into(iv)
                    sv.llLugar.addView(iv)
                }
            }
        })
        viewModel.getAvg(lugar.id).observe(this, Observer {
            it?.let{
                var num = it.toInt()
                if (num==0) num = 1
                ivAvg.imageResource = this.resources.getIdentifier("ic_${num}", "drawable", this.packageName)
                }
            }
        )
    }
    fun puntuar(view: View){
        viewModel.getPuntos(lugar.id).observe(this, Observer {
            it?.let {
                if (it.any { p -> p.usuario==usuario.id }){
                    toast("Ya has puntuado este lugar")
                }else{
                    viewModel.savePuntos(lugar.id,usuario.id,Integer.parseInt(view.tag as String)).observe(this, Observer {
                        it?.let{
                            toast("Puntos añadidos correctamente")
                            this.finish()
                        }?:let { toast("Error añadiendo puntos") }
                    }
                    )
                }
            }
        })

    }
}

package net.azarquiel.examenandroid.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lugar.*
import kotlinx.android.synthetic.main.activity_lugar.view.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.model.Lugar
import net.azarquiel.examenandroid.model.Usuario
import net.azarquiel.examenandroid.viewmodel.MainViewModel
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
        tvLugarNombre.text = lugar.titre+"  "+lugar.id
        tvLugarDescripcion.text = lugar.description_es
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //get images
        viewModel.getFotos(lugar.id).observe(this, Observer {
            it?.let{
                //control variable for current image
                var i=1
                for (foto in it) {
                    var iv = ImageView(this)
                    var lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    //adds margin at each side of the generated view,in my experience it does not work with dp
                    //the view for the first image won't have left margin
                    if (i!=1) lp.marginStart=20
                    //the view for the last image won't have right margin
                    if (i!=it.size) lp.marginEnd=20
                    iv.layoutParams = lp
                    //for picasso to use WRAP_CONTENT or MATCH_PARENT, the view has to first contain an image
                    //as a workaround, a placeholder its used, it has to be larger in width/height than the loaded image AFTER resizing, as I understand
                    Picasso.with(this).load(foto.link_large).placeholder(R.drawable.bigplaceholder).fit().centerInside().into(iv)
                    sv.llLugar.addView(iv)
                    i++
                }
            }
        })
        //get average rating
        viewModel.getAvg(lugar.id).observe(this, Observer {
            it?.let{
                var num = it.toInt()
                if (num==0) num = 1
                ivAvg.imageResource = this.resources.getIdentifier("ic_${num}", "drawable", this.packageName)
                }
            }
        )
    }
    //rate current Lugar
    fun puntuar(view: View){
        viewModel.getPuntos(lugar.id).observe(this, Observer {
            it?.let {
                //checks if the Lugar has already been rated
                if (it.any { p -> p.usuario==usuario.id }){
                    toast("Ya has puntuado este lugar")
                }else{
                    //if not it submits the rate
                    viewModel.savePuntos(lugar.id,usuario.id,Integer.parseInt(view.tag as String)).observe(this, Observer {
                        it?.let{
                            toast("Puntos añadidos correctamente")
                            this.finish()
                        }?:let { toast("Error añadiendo puntos") }
                    })
                }
            }
        })

    }
}

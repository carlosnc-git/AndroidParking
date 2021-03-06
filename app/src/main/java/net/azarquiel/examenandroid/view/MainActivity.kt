package net.azarquiel.examenandroid.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import net.azarquiel.examenandroid.R
import net.azarquiel.examenandroid.adapter.CustomAdapterComunidades
import net.azarquiel.examenandroid.model.Comunidad
import net.azarquiel.examenandroid.model.Usuario
import net.azarquiel.examenandroid.viewmodel.MainViewModel
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: CustomAdapterComunidades
    private lateinit var viewModel: MainViewModel
    private var usuario: Usuario? = null
    private lateinit var preferencias: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loadAdapter()
        preferencias = getSharedPreferences("user", Context.MODE_PRIVATE)
        title = "Comunidades"
        loadUser()
        loginWithLastUser()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //get the Comunidad list, then it's given to the adapter
        viewModel.getComunidades().observe(this, Observer {
            it?.let(adapter::setComunidades)
        })
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }
    //loads the Usuario saved in the sharedpreference, if not null
    private fun loginWithLastUser() {
        val user = preferencias.getString("usuario", null)
        usuario = null
        user?.let {
                usuario = Gson().fromJson(user,Usuario::class.java)
                loadUser()
            }
    }
    //loads the nick parameter from usuario into the drawer
    private fun loadUser(){
        val nickAvatar = nav_view.getHeaderView(0).tvUser
        usuario?.let{ it -> nickAvatar.text = it.nick
        }?: let {
            nickAvatar.text ="NoUser"
        }

    }
    //call for register dialog, if possitive then tries to register
    private fun register(){
        alert {
            customView {
                verticalLayout {
                    val tvNick = editText {
                        hint = "Nick"
                    }
                    val tvPass = editText {
                        hint ="Pass"
                        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
                    }
                    negativeButton("Cancelar"){}
                    positiveButton("Register") { okRegister(tvPass.text.toString(),tvNick.text.toString()) }
                }
            }
        }.show()
    }
    //register
    private fun okRegister(pass: String, nick: String){
        //if any paramer is empty, the alert with show up again
        if (pass.isEmpty() || nick.isEmpty()){
            alert ("Rellena todos los parámetros"){
                okButton { register() }
                cancelButton {  }
            }.show()
        } else {
            //if parameters are ok it tries to register the user
            var newUsuario = Usuario(nick = nick,pass = pass)
            viewModel.saveUsuario(newUsuario).observe( this, Observer {
                it?.let { toast("Usuario registrado exitosamente") }?:let { toast("Error registrando usuario")  }
            })
        }
    }
    //call for login dialog, if possitive then tries to login
    private fun login(){
        alert {
            customView {
                verticalLayout {
                    val tvNick = editText {
                        hint = "Nick"
                    }
                    val tvPass = editText {
                        hint ="Pass"
                        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
                    }
                    negativeButton("Cancelar"){}
                    positiveButton("Login") { okLogin(tvPass.text.toString(),tvNick.text.toString()) }
                }
            }
        }.show()
    }
    //login
    private fun okLogin(pass : String, nick : String){
        //tries to get an Usuario that matches pass and nick
        viewModel.getUsuario(nick, pass).observe( this, Observer {
            var mensaje = ""
            //if successfull it saves the Usuario into the sharedpreferences
            it?.let {
                usuario = it
                loadUser()
                val editor = preferencias.edit()
                editor.putString("usuario", Gson().toJson(usuario))
                editor.commit()
                mensaje = "Iniciada sesión como ${it.nick}" }?: let { mensaje = "Usuario no encontrado o contraseña incorrecta" }
            //depending of the result the message will vary
            toast(mensaje)
        })
    }
    //initialize the adapter
    private fun loadAdapter(){
        adapter = CustomAdapterComunidades(this, R.layout.row_comunidad)
        rvComunidades.layoutManager = LinearLayoutManager(this)
        rvComunidades.adapter = adapter
    }
    //onclick method for each row/viewholder
    fun clickComunidad(view: View){
        val intent = Intent(this, ProvinciasActivity::class.java)
        intent.putExtra("comunidad", (view.tag as Comunidad))
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_login -> {
                login()
            }
            R.id.nav_registrar -> {
                register()
            }
            R.id.nav_logout -> {
                logout()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    //logout, sets usuario to null and removes it from the sharedpreferences
    private fun logout() {
        usuario = null
        loadUser()
        val editor = preferencias.edit()
        editor.remove("usuario")
        editor.commit()
    }
}

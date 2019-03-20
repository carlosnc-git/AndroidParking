package net.azarquiel.examenandroid.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import net.azarquiel.examenandroid.model.*

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: MainRepository = MainRepository(application)

    fun getComunidades(): LiveData<List<Comunidad>> {
        return repository.getComunidades()
    }
    fun getProvincias(id: Int): LiveData<List<Provincia>> {
        return repository.getProvincias(id)
    }
    fun getMunicipios(id: Int): LiveData<List<Municipio>> {
        return repository.getMunicipios(id)
    }

    fun saveUsuario(usuario: Usuario): LiveData<Usuario>{
        return repository.saveUsuario(usuario)
    }

    fun getUsuario(nick: String, pass: String): LiveData<Usuario>{
        return repository.getUsuario(nick,pass)
    }

    fun getLugares(latitud: Double, longitud: Double):LiveData<List<Lugar>>{
        return repository.getLugares(latitud=latitud, longitud = longitud)
    }

    fun getFotos(id: Int): LiveData<List<Foto>>{
        return repository.getFotos(id)
    }

    fun getAvg(id: Int): LiveData<Double>{
        return repository.getAvg(id)
    }

    fun savePuntos(idlugar: Int, idusuario: Int, puntos: Int): LiveData<Punto>{
        return repository.savePunto(idlugar, idusuario, puntos)
    }

    fun getPuntos(id: Int): LiveData<List<Punto>>{
        return repository.getPuntos(id)
    }

}

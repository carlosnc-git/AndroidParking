package net.azarquiel.examenandroid.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenandroid.model.*

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: MainRepository = MainRepository(application)

    fun getComunidades(): LiveData<List<Comunidad>> {
        val result= MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getComunidades()
        }
        return result
    }
    fun getProvincias(id: Int): LiveData<List<Provincia>> {
        val result= MutableLiveData<List<Provincia>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getProvincias(id)
        }
        return result
    }
    fun getMunicipios(id: Int): LiveData<List<Municipio>> {
        val result= MutableLiveData<List<Municipio>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getMunicipios(id)
        }
        return result
    }

    fun saveUsuario(usuario: Usuario): LiveData<Usuario>{
        val result= MutableLiveData<Usuario>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.saveUsuario(usuario)
        }
        return result
    }

    fun getUsuario(nick: String, pass: String): LiveData<Usuario>{
        val result= MutableLiveData<Usuario>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getUsuario(nick,pass)
        }
        return result
    }

    fun getLugares(latitud: Double, longitud: Double):LiveData<List<Lugar>>{
        val result= MutableLiveData<List<Lugar>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getLugares(latitud = latitud, longitud = longitud)
        }
        return result
    }

    fun getFotos(id: Int): LiveData<List<Foto>>{
        val result= MutableLiveData<List<Foto>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getFotos(id)
        }
        return result
    }

    fun getAvg(id: Int): LiveData<Double>{
        val result= MutableLiveData<Double>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getAvg(id)
        }
        return result
    }

    fun savePuntos(idlugar: Int, idusuario: Int, puntos: Int): LiveData<Punto>{
        val result= MutableLiveData<Punto>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.savePunto(idlugar,idusuario,puntos)
        }
        return result
    }

    fun getPuntos(id: Int): LiveData<List<Punto>>{
        val result= MutableLiveData<List<Punto>>()
        GlobalScope.launch(Dispatchers.Main) {
            result.value = repository.getPuntos(id)
        }
        return result
    }

}

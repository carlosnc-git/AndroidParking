package net.azarquiel.examenandroid.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenandroid.api.WebAccess

class MainRepositoryOld(application: Application) {
    val service = WebAccess.caravanService

    fun getComunidades(): LiveData<List<Comunidad>> {
        var result = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getComunidades().await()
            if (webResponse.isSuccessful) {
                result.value = webResponse.body()!!.comunidades as ArrayList<Comunidad>
            }
        }
        return result
    }
    fun getProvincias(id: Int): LiveData<List<Provincia>> {
        var result = MutableLiveData<List<Provincia>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getProvincias(id).await()
            if (webResponse.isSuccessful) {
                result.value = webResponse.body()!!.provincias as ArrayList<Provincia>
            }
        }
        return result
    }
    fun getMunicipios(id: Int): LiveData<List<Municipio>> {
        var result = MutableLiveData<List<Municipio>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getMunicipios(id).await()
            if (webResponse.isSuccessful) {
                result.value = webResponse.body()!!.municipios as ArrayList<Municipio>
            }
        }
        return result
    }
    fun saveUsuario(usuario: Usuario): LiveData<Usuario> {
        var userResponse = MutableLiveData<Usuario>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.saveUsuario(usuario).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.usuario
            }
        }
        return userResponse
    }
    fun getUsuario(nick: String, pass: String): LiveData<Usuario> {
        var userResponse = MutableLiveData<Usuario>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getUsuario(nick,pass).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.usuario
            }
        }
        return userResponse
    }
    fun getLugares(latitud: Double, longitud: Double): LiveData<List<Lugar>> {
        var userResponse = MutableLiveData<List<Lugar>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getLugares(latitud=latitud,longitud = longitud).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.lieux
            }
        }
        return userResponse
    }
    fun getFotos(id: Int): LiveData<List<Foto>> {
        var userResponse = MutableLiveData<List<Foto>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getFotos(id).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.p4n_photos
            }
        }
        return userResponse
    }
    fun getAvg(id: Int): LiveData<Double> {
        var userResponse = MutableLiveData<Double>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getAvg(id).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.avg
            }
        }
        return userResponse
    }

    fun savePunto(idlugar: Int, idusuario: Int, puntos:Int): LiveData<Punto> {
        var userResponse = MutableLiveData<Punto>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.savePunto(idlugar, idusuario, puntos).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.punto
            }
        }
        return userResponse
    }

    fun getPuntos(id: Int): LiveData<List<Punto>> {
        var userResponse = MutableLiveData<List<Punto>>()
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = service.getPuntos(id).await()
            if (webResponse.isSuccessful) {
                userResponse.value = webResponse.body()!!.puntos
            }
        }
        return userResponse
    }




}
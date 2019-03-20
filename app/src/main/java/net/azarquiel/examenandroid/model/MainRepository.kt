package net.azarquiel.examenandroid.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenandroid.api.WebAccess

class MainRepository(application: Application) {
    val service = WebAccess.caravanService

    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }
    suspend fun getProvincias(id: Int): List<Provincia> {
        val webResponse = service.getProvincias(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.provincias
        }
        return emptyList()
    }
    suspend fun getMunicipios(id: Int): List<Municipio> {
        val webResponse = service.getMunicipios(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.municipios
        }
        return emptyList()
    }
    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }
    suspend fun getUsuario(nick: String, pass: String): Usuario? {
        val webResponse = service.getUsuario(nick,pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }
    suspend fun getLugares(latitud: Double, longitud: Double): List<Lugar> {
        val webResponse = service.getLugares(latitud=latitud,longitud = longitud).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.lieux
        }
        return emptyList()
    }
    suspend fun getFotos(id: Int): List<Foto> {
        val webResponse = service.getFotos(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.p4n_photos
        }
        return emptyList()
    }
    suspend fun getAvg(id: Int): Double? {
        val webResponse = service.getAvg(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return null
    }

    suspend fun savePunto(idlugar: Int, idusuario: Int, puntos:Int): Punto? {
        val webResponse = service.savePunto(idlugar, idusuario, puntos).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.punto
        }
        return null
    }

    suspend fun getPuntos(id: Int): List<Punto> {
        val webResponse = service.getPuntos(id).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.puntos
        }
        return emptyList()
    }




}
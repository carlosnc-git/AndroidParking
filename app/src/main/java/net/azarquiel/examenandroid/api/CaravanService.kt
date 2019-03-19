package net.azarquiel.examenandroid.api

import kotlinx.coroutines.Deferred
import net.azarquiel.examenandroid.model.Respuesta
import net.azarquiel.examenandroid.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface CaravanService {

    @GET("comunidad")
    fun getComunidades(): Deferred<Response<Respuesta>>

    @GET("comunidad/{id}/provincia")
    fun getProvincias(@Path("id") id: Int): Deferred<Response<Respuesta>>

    @GET("provincia/{id}/municipio")
    fun getMunicipios(@Path("id") id: Int): Deferred<Response<Respuesta>>

    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Respuesta>>

    @GET("usuario")
    fun getUsuario(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Respuesta>>

    @GET("lugar")
    fun getLugares(
        @Query("latitud") latitud: Double,
        @Query("longitud") longitud: Double): Deferred<Response<Respuesta>>

    @GET("lugar/{id}/fotos")
    fun getFotos(@Path("id") id: Int): Deferred<Response<Respuesta>>

    @GET("lugar/{id}/avgpuntos")
    fun getAvg(@Path("id") id: Int): Deferred<Response<Respuesta>>

    @GET("lugar/{id}/puntos")
    fun getPuntos(@Path("id") id: Int): Deferred<Response<Respuesta>>

    @FormUrlEncoded
    @POST("lugar/{idlugar}/puntos")
    fun savePunto(@Path ("idlugar") idlugar: Int, @Field("usuario") idusuario: Int,
                  @Field("puntos") puntos: Int): Deferred<Response<Respuesta>>

}
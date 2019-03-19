package net.azarquiel.examenandroid.model

import java.io.Serializable

data class Punto(
    var usuario: Int,
    var lugar: Int,
    var puntos: Int,
    var id: Int
)
data class Foto(
    var link_thumb: String,
    var link_large: String
)
data class Lugar(
    var id: Int,
    var titre: String,
    var description_es: String
): Serializable
data class Usuario(
    var id: Int = 0,
    var nick: String,
    var pass: String
): Serializable
data class Municipio(
    var id: Int,
    var nombre: String,
    var provincia: Int,
    var latitud: Double,
    var longitud: Double
): Serializable
data class Provincia(
    var id: Int,
    var comunidad: Int,
    var nombre: String
):Serializable
data class Comunidad(
    var id: Int,
    var nombre: String
): Serializable
data class Respuesta (
    var comunidades: List<Comunidad>,
    var provincias: List<Provincia>,
    var municipios: List<Municipio>,
    var msg: String,
    var usuario: Usuario? = null,
    var lieux: List<Lugar>,
    var p4n_photos: List<Foto>,
    var avg: Double,
    var punto: Punto
): Serializable
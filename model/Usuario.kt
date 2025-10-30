package model

import model.enums.Genero

data class Usuario(
    val nome: String,
    val idade: Int,
    val genero: Genero,
    val cpf: String
)
package model

import model.enums.Nivel

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, var nivel: Nivel)
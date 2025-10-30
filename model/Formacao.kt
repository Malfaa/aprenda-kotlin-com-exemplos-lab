package model

import model.enums.Nivel

data class Formacao(
    val nome: String,
    val nivel: Nivel
) {
    val conteudos: MutableList<ConteudoEducacional> = mutableListOf()
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            println("Erro: Usuário ${usuario.nome} já está matriculado nesta formação.")
        } else {
            inscritos.add(usuario)
            println("Sucesso: ${usuario.nome} matriculado(a) na formação '${this.nome}'.")
        }
    }

    fun adicionarConteudo(conteudo: ConteudoEducacional) {
        conteudos.add(conteudo)
        println("Sucesso: Conteúdo '${conteudo.nome}' adicionado à formação '${this.nome}'.")
    }
}
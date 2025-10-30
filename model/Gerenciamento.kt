package model

import model.enums.Genero
import model.enums.Nivel

class Gerenciamento {

    private fun lerInteiro(prompt: String = "Digite o índice: "): Int {
        print(prompt)
        return readlnOrNull()?.toIntOrNull() ?: -1
    }

    private fun visualizarUsuarios(usuarios: List<Usuario>) {
        println("\n=== Lista de Usuários ===")
        if (usuarios.isEmpty()) {
            println("Nenhum usuário cadastrado.")
            return
        }
        for((index, usuario) in usuarios.withIndex()){
            println("[$index] | ${usuario.nome} | (CPF: ${usuario.cpf})")
        }
    }

    private fun visualizarFormacoes(formacoes: List<Formacao>) {
        println("\n--- Lista de Formações ---")
        if (formacoes.isEmpty()) {
            println("Nenhuma formação cadastrada.")
            return
        }
        for((index, formacao) in formacoes.withIndex()){
            println("[$index] ${formacao.nome} | (${formacao.nivel}) | ${formacao.inscritos.size} inscritos")
        }
    }

    fun adicionarUsuario(usuarios: MutableList<Usuario>) {
        println("\n=== Adicionar Usuário ===")
        print("Digite o nome: ")
        val nome = readlnOrNull() ?: "Nome Inválido"

        val idade = lerInteiro("Digite a idade: ")

        println("Digite o gênero:\n[1]. MASCULINO\n[2]. FEMININO\n[3]. OUTROS")
        val genero = when (lerInteiro("Opção de Gênero: ")) {
            1 -> Genero.MASCULINO
            2 -> Genero.FEMININO
            else -> Genero.OUTROS
        }

        print("Digite o cpf: ")
        val cpf = readlnOrNull() ?: "CPF Inválido"

        val novoUsuario = Usuario(nome, idade, genero, cpf)
        usuarios.add(novoUsuario)
        println("Sucesso: Usuário ${novoUsuario.nome} adicionado!")
    }

    fun deletarUsuario(usuarios: MutableList<Usuario>) {
        println("\n=== Deletar Usuário ===")
        visualizarUsuarios(usuarios)
        if (usuarios.isEmpty()) return

        val indice = lerInteiro("Digite o índice do usuário que deseja deletar: ")

        if (indice in 0 until usuarios.size) {
            val usuarioRemovido = usuarios.removeAt(indice)
            println("Sucesso: Usuário ${usuarioRemovido.nome} foi removido.")
        } else {
            println("Erro: Índice inválido.")
        }
    }

    fun adicionarFormacao(formacoes: MutableList<Formacao>) {
        println("\n=== Adicionar Formação ===")
        print("Nome da Formação: ")
        val nome = readlnOrNull() ?: "Formação Inválida"

        println("Nível da Formação:\n[1]. BASICO\n[2]. INTERMEDIARIO\n[3]. AVANCADO")
        val nivel = when (lerInteiro("Opção de Nível: ")) {
            1 -> Nivel.BASICO
            2 -> Nivel.INTERMEDIARIO
            3 -> Nivel.AVANCADO
            else -> Nivel.BASICO
        }

        val novaFormacao = Formacao(nome, nivel)
        formacoes.add(novaFormacao)
        println("Sucesso: Formação ${novaFormacao.nome} adicionada!")
    }

    fun deletarFormacao(formacoes: MutableList<Formacao>) {
        println("\n=== Deletar Formação ===")
        visualizarFormacoes(formacoes)
        if (formacoes.isEmpty()) return

        val indice = lerInteiro("Digite o índice da formação que deseja deletar: ")

        if (indice in 0 until formacoes.size) {
            val formacaoRemovida = formacoes.removeAt(indice)
            println("Sucesso: Formação ${formacaoRemovida.nome} foi removida.")
        } else {
            println("Erro: Índice inválido.")
        }
    }

    fun adicionarConteudoEdu(formacoes: MutableList<Formacao>) {
        println("\n=== Adicionar Conteúdo Educacional ===")
        visualizarFormacoes(formacoes)
        if (formacoes.isEmpty()) return

        val indiceFormacao = lerInteiro("Digite o índice da formação que receberá o conteúdo: ")
        if (indiceFormacao !in 0 until formacoes.size) {
            println("Erro: Índice de formação inválido.")
            return
        }
        val formacaoAlvo = formacoes[indiceFormacao]

        println("\nAdicionando conteúdo para: ${formacaoAlvo.nome}")
        print("Nome do Conteúdo: ")
        val nome = readlnOrNull() ?: "Conteúdo Inválido"
        val duracao = lerInteiro("Duração (em minutos): ")

        println("Nível do Conteúdo:\n[1]. BASICO\n[2]. INTERMEDIARIO\n[3]. AVANCADO")
        val nivel = when (lerInteiro("Opção de Nível: ")) {
            1 -> Nivel.BASICO
            2 -> Nivel.INTERMEDIARIO
            3 -> Nivel.AVANCADO
            else -> formacaoAlvo.nivel
        }

        val novoConteudo = ConteudoEducacional(nome, duracao, nivel)
        formacaoAlvo.adicionarConteudo(novoConteudo)
    }

    fun deletarConteudoEdu(formacoes: MutableList<Formacao>) {
        println("\n=== Deletar Conteúdo Educacional ===")
        visualizarFormacoes(formacoes)
        if (formacoes.isEmpty()) return

        val indiceFormacao = lerInteiro("Digite o índice da formação para ver os conteúdos: ")
        if (indiceFormacao !in 0 until formacoes.size) {
            println("Erro: Índice de formação inválido.")
            return
        }
        val formacaoAlvo = formacoes[indiceFormacao]

        println("\n--- Conteúdos em ${formacaoAlvo.nome} ---")
        if (formacaoAlvo.conteudos.isEmpty()) {
            println("Esta formação não possui conteúdos.")
            return
        }
        for ((index, conteudo) in formacaoAlvo.conteudos.withIndex()){
            println("[$index] | ${conteudo.nome} | (${conteudo.nivel})")
        }

        val indiceConteudo = lerInteiro("Digite o índice do conteúdo que deseja deletar: ")
        if (indiceConteudo in 0 until formacaoAlvo.conteudos.size) {
            val conteudoRemovido = formacaoAlvo.conteudos.removeAt(indiceConteudo)
            println("Sucesso: Conteúdo ${conteudoRemovido.nome} removido.")
        } else {
            println("Erro: Índice de conteúdo inválido.")
        }
    }
}
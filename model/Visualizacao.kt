package model

class Visualizacao {

    fun lerOpcao(prompt: String = "\nDigite sua opção: "): Int {
        print(prompt)
        return readlnOrNull()?.toIntOrNull() ?: -1
    }

    fun visualizarUsuarios(usuarios: List<Usuario>) {
        println("\n=== Lista de Usuários ===")
        if (usuarios.isEmpty()) {
            println("Nenhum usuário cadastrado.")
            return
        }
        usuarios.forEach { usuario ->
            println("Nome: ${usuario.nome} | Idade: ${usuario.idade} | Gênero: ${usuario.genero} | CPF: ${usuario.cpf}")
        }
    }

    fun visualizarFormacoes(formacoes: List<Formacao>) {
        println("\n=== Lista de Formações ===")
        if (formacoes.isEmpty()) {
            println("Nenhuma formação cadastrada.")
            return
        }
        formacoes.forEach { formacao ->
            println("Formação: ${formacao.nome} | Nível: ${formacao.nivel} | Inscritos: ${formacao.inscritos.size} | Conteúdos: ${formacao.conteudos.size}")
        }
    }

    fun visualizarConteudosDeUmaFormacao(formacoes: List<Formacao>) {
        println("\n=== Visualizar Conteúdos ===")
        for((index, formacao) in formacoes.withIndex()){
            println("[$index] ${formacao.nome}")
        }
        val indice = lerOpcao("Digite o índice da formação que deseja ver os conteúdos: ")

        if (indice !in 0 until formacoes.size) {
            println("Erro: Índice inválido.")
            return
        }
        val formacao = formacoes[indice]

        println("\n--- Conteúdos da Formação: ${formacao.nome} ---")
        if (formacao.conteudos.isEmpty()) {
            println("Nenhum conteúdo cadastrado nesta formação.")
            return
        }
        formacao.conteudos.forEach { conteudo ->
            println("Conteúdo: ${conteudo.nome} | Nível: ${conteudo.nivel} | Duração: ${conteudo.duracao} min")
        }
    }
}
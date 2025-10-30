import model.*
import model.enums.Genero
import model.enums.Nivel

val usuariosDB = mutableListOf<Usuario>()
val formacoesDB = mutableListOf<Formacao>()
val visualizacao = Visualizacao()
val gerenciamento = Gerenciamento()


fun lerOpcao(): Int {
    return readlnOrNull()?.toIntOrNull() ?: -1
}

fun escolheuVisualizacao(){
    println("\n=== Visualização ===")
    print("""
    Para escolher uma opção, basta digitar seu código de referência:
    [1]. Visualizar usuários
    [2]. Visualizar formações
    [3]. Visualizar conteúdos de uma formação
    [4]. Voltar
    
    """.trimIndent())

    when(lerOpcao()){
        1 -> visualizacao.visualizarUsuarios(usuariosDB)
        2 -> visualizacao.visualizarFormacoes(formacoesDB)
        3 -> visualizacao.visualizarConteudosDeUmaFormacao(formacoesDB)
        4 -> return
        else -> print("Valor inválido. Tente novamente.")
    }
}
fun escolheuGerenciamento(){
    println("\n=== Gerenciamento ===")
    print("""
    Para escolher uma opção, basta digitar seu código de referência:
    [1]. Gerenciar usuários
    [2]. Gerenciar formações
    [3]. Voltar
    
    """.trimIndent())

    when(lerOpcao()){
        1 -> gerenciarUsuarios()
        2 -> gerenciarFormacao()
        3 -> return
        else -> print("Valor inválido. Tente novamente.")
    }
}

fun gerenciarUsuarios(){
    println("\n=== Gerenciamento Usuários ===")
    print("""
    Para escolher uma opção, basta digitar seu código de referência:
    [1]. Adicionar usuários
    [2]. Deletar usuários
    [3]. Voltar
    
    """.trimIndent())

    when(lerOpcao()){
        1 -> gerenciamento.adicionarUsuario(usuariosDB)
        2 -> gerenciamento.deletarUsuario(usuariosDB)
        3 -> return
        else -> print("Valor inválido. Tente novamente.")
    }
}

fun gerenciarFormacao(){
    println("\n=== Gerenciamento Formação ===")
    print("""
    Para escolher uma opção, basta digitar seu código de referência:
    [1]. Adicionar formação
    [2]. Deletar formação
    [3]. Adicionar conteúdo educacional
    [4]. Deletar conteúdo educacional
    [5]. Voltar
    
    """.trimIndent())

    when(lerOpcao()){
        1 -> gerenciamento.adicionarFormacao(formacoesDB)
        2 -> gerenciamento.deletarFormacao(formacoesDB)
        3 -> gerenciamento.adicionarConteudoEdu(formacoesDB)
        4 -> gerenciamento.deletarConteudoEdu(formacoesDB)
        5 -> return
        else -> print("Valor inválido. Tente novamente.")
    }
}


fun main() {

    val user1 = Usuario("Ana Silva", 25, Genero.FEMININO, "111.111.111-11")
    val user2 = Usuario("Bruno Costa", 30, Genero.MASCULINO, "222.222.222-22")
    usuariosDB.addAll(listOf(user1, user2))

    val formacaoKotlin = Formacao("Desenvolvimento Kotlin", Nivel.BASICO)
    formacaoKotlin.adicionarConteudo(ConteudoEducacional("Variáveis", 60, Nivel.BASICO))
    formacaoKotlin.adicionarConteudo(ConteudoEducacional("Funções", 90, Nivel.BASICO))
    formacaoKotlin.matricular(user1)

    val formacaoGestao = Formacao("Gestão Ágil de Projetos", Nivel.INTERMEDIARIO)
    formacaoGestao.adicionarConteudo(ConteudoEducacional("Scrum", 120, Nivel.INTERMEDIARIO))
    formacaoGestao.matricular(user1)
    formacaoGestao.matricular(user2)

    formacoesDB.addAll(listOf(formacaoKotlin, formacaoGestao))

    println("Dados de teste carregados: ${usuariosDB.size} usuários e ${formacoesDB.size} formações.")

    while (true) {
        println("\n=== Instituto Educacional ===")
        print(
            """
        Para escolher uma opção, basta digitar seu código de referência:
        [1]. Visualização
        [2]. Gerenciamento
        [3]. Sair
        
        """.trimIndent()
        )

        when (lerOpcao()) {
            1 -> escolheuVisualizacao()
            2 -> escolheuGerenciamento()
            3 -> {
                println("\nEncerrando o sistema...")
                break
            }
        }
    }
}
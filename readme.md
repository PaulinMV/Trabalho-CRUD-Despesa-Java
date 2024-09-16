**Sistema de Controle de Despesas**

**Descrição Geral:**
    Este projeto permite que os usuários gerenciem suas despesas e pagamentos, armazenando os dados em arquivos de texto. Será utilizando métodos de programação orientada a objetos, como herança, interfaces, polimorfismo, sobrecarga e sobrescrita de métodos construtores.

**FUNCIONALIDADE**
MENU:

    1. Gerenciar Despesas:
        Editar Despesa: Permite alterar informações de uma despesa existente.
        Excluir Despesa: Remove uma despesa do sistema.
        Relatório de Despesas: Lista todas as despesas, possivelmente filtradas por categorias ou status.

    2. Anotar Pagamento:
        Permite marcar uma despesa como paga, atualizando o status de pagamento.
    
    3. Gerenciar Usuários:
        Cadastrar Usuário: Adiciona novos usuários ao sistema.
        Editar Usuário: Modifica as informações de um usuário existente.
        Listar Usuários: Exibe todos os usuários cadastrados.
        Excluir Usuário: Remove um usuário do sistema.

    0. Sair:
    Encerra o programa.


**Para o projeto, foram apontados as seguintes funcionalidades:**

    - **Banco de Dados em Arquivo de Texto:**
        Todas as despesas, tipos de despesa e usuários devem ser armazenados em arquivos de texto separados.

    - **Criptografia de Senhas:**
        Implemente um método para criptografar as senhas dos usuários antes de armazená-las no arquivo de texto.

**Metodologias Indicadas:**

    - **Classes e Herança:**
        Crie classes abstratas e concretas para representar diferentes categorias de despesas (por exemplo, Transporte, Alimentação, Moradia, Lazer e Saúde.). Utilize herança para compartilhar comportamentos comuns.

    - **Interfaces e Polimorfismo:**
        Implemente interfaces para definir contratos comuns entre classes (por exemplo, Pagável) e use polimorfismo para permitir que um objeto se comporte como seu pai.

    - **Sobrecarga e Sobrescrita de Método Construtor:**
        Implemente construtores sobrecarregados para permitir a criação de despesas com diferentes detalhes. Utilize a sobrescrita para personalizar o comportamento em subclasses.

------------------------------------------------- /// Parte Tecnica /// ------------------------------------------------
**PRIMERIA PARTE DO DESENVOLVIMENTO:**
    - **Essa Primeira parte consiste em aplicar os métodos basicos como a estruturação do desenvolvimento do código, em seguida será aplicado métodos de criptografia simples de senha e métodos para salvar os conteúdos em arquivo texto.**


Começando pelo Inicio do codigo, temos a importação de bibliotecas utilizadas, biblioteca essas que são responsaveis por métodos presentes e essenciais para o desenvolver do sistema.

*Interface Pagavel:*

    - **Contém ações publicas do tipo Boleano que realizará o pagamento e irá retornar o status da despesa Paga/Pendente:**
        interface Pagavel {
        public boolean pagar();
        public boolean statusPagamento();
        }


*Classe Despesa:*

    - **Classe que implementa a interface 'Pagavel', as variaveis presentes são variavies indispensaveis para a descrição completa de uma despesa seguido do Método construtor da mesma classe que faz com que toda 'Despesa' instanciada tenha todos os parametros/variaveis:**
        String descricao
        Float valor
        String dataVencimento
        String categoria
        boolean statusPagamento


*@Override:*

    - **Método de reescrita dos métodos da interface 'Pagavel'. Método 'pagar' que verifica se a despesa esta com o status 'Pendente', caso Verdadeiro a despesa passará a ter o status 'Pago', caso seja uma despesa que ja está com o status 'Pago' ele retorna uma mensagem que já está pago. O método 'statusPagamento' apenas retorna qual é o status do pagamento.**


Declaração de classes que herdam de Despesa, classes essas que são variações de despesas como por exemplo Alimentação, Transporte, etc.


*Classe User:*
    - **Classe que "cria" um novo usuario, com um método construtor padrão e métodos get padrão.**


*Classe CrudDespesa:*

    - **Classe essa responsavel pelo processo de CRUD (Gerenciamento) de despesas, a classe consiste em administrar através de métodos as despesas. O Arraylist criado no começo da classe tem o objetivo de armazenar toda classe que extendem do tipo de variavel Despesa, podendo assim gerenciar também todas as despesas. Alguns Métodos presentes na classe são: pagarDespesa 'O usuário irá pagar uma despesa, iniciasse o método verificando se a despesa informada está presente no ArrayList, estando ele pagará a despesa', criarDespesa 'Consiste em recolher informações do usuario através do scanner, após todos os dados coletados é instanciado uma nova despesa', editarDespesa 'Recolhe o número index da despesa que foi informado pelo usuario, recolhe atráves do scanner os dados a serem alterados e por fim utiliza o método .set que altera os dados', listarDespesas 'O usúario escolhe de que forma quer listar as despesas informando a data de inicio de fim de período, listando por pagas ou pendentes, o método consiste em um if que compara qual o tipo de filtro o usuario quer utilizar e também utiliza um .get para retornar o valor' e por ultimo o método excluirDespesa 'Atráves do scanner recolhe qual despesa o usúario quer deletar, verifica atraves do if se o index está dentro do ArrayList e utiliza o .remove para deletar do ArrayList'.**

*Classe CrudUsuario:*

    - **Contém a mesma base de configuraçoes e métodos do CrudDespesa voltado para o gerenciamento de usuarios, más com métodos voltados exclusivamente para os usuarios com métodos que cadastram, editam, listam e excluem.**


*Classe Menu:*

    - **Classe que contém a exibição do menu através do método 'exibirMenuPrincipal' que utiliza de um do while que exibirá o menu de opçoes até que a opção do usuario seja '0', dentro desse menu de escolhas temos os métodos que irão gerenciar as despesas, pagamentos e usuarios. O usuario escolhendo qual ação deseja fazer através do scanner que lhe levará a outro submenu de acordo com opçao que é gerenciada com um switch case que verifica o valor e retornará um campo especifico, método esse que se repete em todo o decorrer de exibiçao do menu. Dentro de cada Switch Case é criado métodos que facilitarão a futura execução chamando os métodos criados anteriormente para o gerenciamento e o pagamento de uma despesa.**



**SEGUNDA PARTE DO DESENVOLVIMENTO:**
    - **Consiste em aplicar os métodos de criptografia de senha de modo simples como sugerido pelo professor, usando o método ASCII para implantar caracteres diferentes a senha do usuario.**

*Classe Criptografia:*

    - **Contém os métodos:
        criptografar:
            Método que tem como parametro uma string senha, utiliza o StringBuilder que é uma classe padrão Java que permite a melhor manipulação de uma String, diferente de uma String comum. Método FOR-EACH que converte o String senha em um array de caracteres avançando três posições na tabela ASCII.
            
            Tabela ASCII: Uma tabela que mapeia caracteres para valores numéricos.
            Exemplo: Se c for 'A' (valor ASCII 65), c + 3 será 68, que corresponde ao caractere 'D'.

        descriptografar:
            Método que tem as mesmas caracteristicas e ações do método anterior, com a diferença que ao invés de avançar três posições na tabela ASCII, ele retrocede três posições.**


**CrudUsuario:**
    - **Alterações foram feitas na classe CrudUsuario:
        cadastrarUser:
            Antes da senha ser armazenada com o users.add, foi implementado o método criptografar(senha):
                String senhaCriptografada = Criptografia.criptografar(senha);
            Logo Após foi alterado a senha que irá ser salva no arrayList.

        editarUser:
            As alterações feitas aqui foram semelhantes as anteriores, aplicando o método criptografar(senha).

        listarUser:
            Foi adicionado o método que faz a descriptografia antes de listar a senha com o usuario**


**Classe Arquivo:**
    - **Classe que tem os métodos de salvar arqvuivos Despesa e de Usuarios:
        salvarArquivoDespesa:
            BufferedWriter writer cria um novo arquivo com o nome "Despesas" em formato .txt, um for-each lista tudo da listaDespesa do tipo Despesa e adiciona ao arquivo os dados detalhados.

        salvarArquivoUser:
            Tem o mesmo comportamento do método anterior, mas com ações voltadas aos usuarios.

        carregarArquivoDespesa:
            BufferedReader reader faz a leitura do arquivo com o nome "Despesa.txt" e após fazer a leitura de tudo, faz a instanciação.

        carregarArquivoUser:
            Tem o mesmo comportamento do método anterior, mas com ações voltadas aos usuarios.**

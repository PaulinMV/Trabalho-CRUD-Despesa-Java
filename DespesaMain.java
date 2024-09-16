// Importação de bibliotecas utilizadas.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface que define a característica Pagavel que será utilizada na classe 'Despesa'.
interface Pagavel {
    public boolean pagar();
    public boolean statusPagamento();
}

// Classe responsável por definir o que uma despesa DEVE conter.
abstract class Despesa implements Pagavel {
    private String descricao;
    private Float valor;
    private String dataVencimento;
    private String categoria;
    private boolean statusPagamento;

    // Método construtor 
    Despesa(String descricao, Float valor, String dataVencimento, String categoria, boolean statusPagamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.statusPagamento = false;
    }

    // Métodos de sobrescrita da interface
    @Override
    public boolean pagar() {
        if (!this.statusPagamento) {
            this.statusPagamento = true;
            return true; // Pagamento realizado.
        }
        return false; // Quando a despesa já está paga.
    }

    @Override
    public boolean statusPagamento() {
        return statusPagamento;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public String toString() {
        return "Despesa{"
                + "descricao='" + descricao + '\''
                + ", valor=" + valor
                + ", dataVencimento='" + dataVencimento + '\''
                + ", categoria='" + categoria + '\''
                + ", statusPagamento=" + statusPagamento
                + '}';
    }
}

// Subclasses que herdam de Despesa.
class Transporte extends Despesa {
    Transporte(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Transporte", statusPagamento);
    }
}

class Alimentacao extends Despesa {
    Alimentacao(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Alimentação", statusPagamento);
    }
}

class Moradia extends Despesa {
    Moradia(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Moradia", statusPagamento);
    }
}

class Lazer extends Despesa {
    Lazer(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Lazer", statusPagamento);
    }
}

class Saude extends Despesa {
    Saude(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Saúde", statusPagamento);
    }
}

class Outros extends Despesa {
    Outros(String descricao, Float valor, String dataVencimento, boolean statusPagamento) {
        super(descricao, valor, dataVencimento, "Outros", statusPagamento);
    }
}

// Classe User
class User {
    private String login;
    private String senha;

    // Construtor
    User(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // Métodos get
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}

// Classe CRUD responsável pelo gerenciamento de despesas.
class CrudDespesa {
    // Array que armazena qualquer variável que seja do tipo Despesa.
    List<Despesa> listaDespesas = new ArrayList<>();
    Despesa despesa = null;

    public CrudDespesa() {
        this.listaDespesas = new ArrayList<>();
    }

    // Método de pagamento de despesa.
    public void pagarDespesa(int index) {
        if (index >= 0 && index < listaDespesas.size()) {
            boolean sucesso = listaDespesas.get(index).pagar();
            if (sucesso) {
                System.out.println("Despesa paga com sucesso!");
            } else {
                System.out.println("Despesa já estava paga.");
            }
        } else {
            System.out.println("Número de Despesa Inválido!");
        }
    }

    // Método CRUD de criação de Despesa de acordo com a categoria selecionada.
    public void criarDespesa() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- CRIAR DESPESA -----");

        // Descrição
        System.out.println("Digite a descrição da despesa:");
        String descricao = scanner.nextLine();

        // Valor
        System.out.println("Digite o valor da despesa:");
        Float valor = scanner.nextFloat();

        // Data de Vencimento
        System.out.println("Digite a data de vencimento da despesa (formato: AAAA-MM-DD):");
        String dataVencimento = scanner.next();

        // Categoria
        System.out.println("Escolha a categoria da despesa:");
        System.out.println("1. Alimentação");
        System.out.println("2. Transporte");
        System.out.println("3. Moradia");
        System.out.println("4. Lazer");
        System.out.println("5. Saúde");
        System.out.println("6. Outros");

        boolean statusPagamento = false;

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1: // Alimentação
                System.out.println("Criando despesa do tipo Alimentação...");
                despesa = new Alimentacao(descricao, valor, dataVencimento, statusPagamento);
                break;
            case 2: // Transporte
                System.out.println("Criando despesa do tipo Transporte...");
                despesa = new Transporte(descricao, valor, dataVencimento, statusPagamento);
                break;
            case 3: // Moradia
                System.out.println("Criando despesa do tipo Moradia...");
                despesa = new Moradia(descricao, valor, dataVencimento, statusPagamento);
                break;
            case 4: // Lazer
                System.out.println("Criando despesa do tipo Lazer...");
                despesa = new Lazer(descricao, valor, dataVencimento, statusPagamento);
                break;
            case 5: // Saúde
                System.out.println("Criando despesa do tipo Saúde...");
                despesa = new Saude(descricao, valor, dataVencimento, statusPagamento);
                break;
            case 6: // Outros
                System.out.println("Criando despesa do tipo Outros...");
                despesa = new Outros(descricao, valor, dataVencimento, statusPagamento);
                break;
            default:
                System.out.println("Opção inválida. Nenhuma despesa criada.");
                return;
        }

        listaDespesas.add(despesa);
        System.out.println("Despesa criada com sucesso!");
    }

    // Método CRUD de edição de Despesa de acordo com a despesa selecionada.
    public void editarDespesa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- EDITAR DESPESA -----");

        System.out.println("Insira o número da Despesa a editar:\n");
        for (int i = 0; i < listaDespesas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDespesas.get(i));
        int indiceDespesa = scanner.nextInt() - 1;
        scanner.nextLine();

        // Verifica se o número a editar é inválido.
        if (indiceDespesa < 0 || indiceDespesa >= listaDespesas.size()) {
            System.out.println("Número de Despesa Inválido!");
            return;
        }

        Despesa despesa = listaDespesas.get(indiceDespesa);

        System.out.println("Editar Despesa: " + despesa);

        // Descrição:
        System.out.println("Insira a nova descrição:\n(Deixe em branco para não alterar)");
        String novaDescricao = scanner.nextLine();

        // Verifica se a descrição está em branco.
        if (!novaDescricao.isEmpty()) {
            despesa.setDescricao(novaDescricao);
        }

        // Valor:
        System.out.println("Insira o novo valor:\n(Digite 0 para não alterar)");
        Float novoValor = scanner.nextFloat();
        scanner.nextLine();

        // Verifica se o valor é 0.
        if (novoValor != 0) {
            despesa.setValor(novoValor);
        }

        // Vencimento:
        System.out.println("Insira a nova data de vencimento:\n(Deixe em branco para não alterar)");
        String novoVencimento = scanner.nextLine();

        // Verifica se o vencimento está em branco.
        if (!novoVencimento.isEmpty()) {
            despesa.setDataVencimento(novoVencimento);
        }

        System.out.println("Despesa atualizada: " + despesa);
        }
    }

    // Método CRUD para listar despesas por status de pagamento e dentro de um período específico.
    public void listarDespesas() { 
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- LISTAR DESPESAS -----");

        System.out.println("Escolha o filtro para listagem:");
        System.out.println("1. Listar apenas despesas pagas");
        System.out.println("2. Listar apenas despesas pendentes");
        int statusEscolhido = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Escolha a data de início do período\n(AAAA-MM-DD):");
        String inicioPeriodo = scanner.nextLine();
    
        System.out.println("Escolha a data de fim do período\n(AAAA-MM-DD):");
        String fimPeriodo = scanner.nextLine();
    
        System.out.println("Listando despesas...:");
        for (Despesa despesa : listaDespesas) {
            boolean statusCorreto = false;
    
            // Verifica se o status de pagamento corresponde ao escolhido.
            if (statusEscolhido == 1 && despesa.getStatusPagamento() == true) {  // Status pagamento == Pagas
                statusCorreto = true;
            } else if (statusEscolhido == 2 && despesa.getStatusPagamento() == false) { // Status pagamento == Pendentes
                statusCorreto = true;
            }
    
            // Verifica se a despesa está dentro do período escolhido.
            if (statusCorreto) {
                String dataVencimento = despesa.getDataVencimento();
    
                // Verificador do início do período.
                boolean dataDentroDoPeriodo = true;
                if (dataVencimento.length() >= inicioPeriodo.length()) {
                    for (int i = 0; i < inicioPeriodo.length(); i++) {
                        if (dataVencimento.charAt(i) < inicioPeriodo.charAt(i)) {
                            dataDentroDoPeriodo = false;
                            break;
                        } else if (dataVencimento.charAt(i) > inicioPeriodo.charAt(i)) {
                            break;
                        }
                    }
                } else {
                    dataDentroDoPeriodo = false;
                }
    
                // Verificador do fim do período.
                if (dataDentroDoPeriodo && dataVencimento.length() >= fimPeriodo.length()) {
                    for (int i = 0; i < fimPeriodo.length(); i++) {
                        if (dataVencimento.charAt(i) > fimPeriodo.charAt(i)) {
                            dataDentroDoPeriodo = false;
                            break;
                        } else if (dataVencimento.charAt(i) < fimPeriodo.charAt(i)) {
                            break;
                        }
                    }
                } else {
                    dataDentroDoPeriodo = false;
                }
    
               
                if (dataDentroDoPeriodo) {
                    System.out.println(despesa);
                }
            }
        }
    }
    
    // Método CRUD de exclusão de Despesa de acordo com a despesa selecionada
    public void excluirDespesa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- EXCLUIR DESPESA -----");

        if (listaDespesas.isEmpty()) {
            System.out.println("Não há despesas para excluir.");
            return;
        }
        System.out.println("Insira o número da despesa a excluir:\n:");
        for (int i = 0; i < listaDespesas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDespesas.get(i));
        int despesaExcluir = scanner.nextInt();

        if (despesaExcluir > 0 && despesaExcluir <= listaDespesas.size()) {
            listaDespesas.remove(despesaExcluir - 1);
            System.out.println("Despesa excluída com sucesso!");
        } else {
            System.out.println("Número de Despesa Inválido!");
        }
    }
}
}

// Classe CRUD responsável pelo gerenciamento de usuarios.
class CrudUsuario {
    List<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public CrudUsuario() {
        this.users = new ArrayList<>();
    }

    // Método CRUD de criação de Usuarios.
    public void cadastrarUser() {
        System.out.println("----- CADASTRAR USUARIOS -----");

        System.out.println("Insira um nome de Login:");
        String login = scanner.nextLine();

        System.out.println("Insira uma senha para o login " + login + ":");
        String senha = scanner.nextLine();

        String senhaCriptografada = Criptografia.criptografar(senha);

        users.add(new User(login, senhaCriptografada));

        System.out.println("Usuario " + login + " Cadastrado com Sucesso!");
    }

    // Método CRUD para edição de Usuarios.  
    public void editarUser() {
        System.out.println("----- EDITAR USUARIOS -----");

        System.out.println("Insira o número do usuario a editar (1-" + users.size() + "):");
        int userEditar = scanner.nextInt();
        scanner.nextLine();
                    
        System.out.println("Insira o novo Login do user: ");
        String novoLogin = scanner.nextLine();

        System.out.println("Insira a nova senha do user: ");
        String novaSenha = scanner.nextLine();

        String novaSenhaCriptografada = Criptografia.criptografar(novaSenha);
                    
        users.set(userEditar - 1, new User(novoLogin, novaSenhaCriptografada));
        
        System.out.println("Usuario atualizado!");
    }

    // Método CRUD para listagem de Usuarioss
    public void listarUser() {
        System.out.println("----- LISTAR USUARIOS -----");

        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (User user : users) {
            System.out.println("Login: " + user.getLogin() + ", Senha: " + Criptografia.descriptografar(user.getSenha()));
        }
    }

    // Método CRUD de exclusão de Usuarios.
    public void excluirUser() {
        System.out.println("----- EXCLUIR USUARIOS -----");

        if (users.isEmpty()) {
            System.out.println("Não há usuarios para excluir.");
            return;
        }
        System.out.println("Insira o número do usuario a excluir:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
        int userExcluir = scanner.nextInt();
    
        if (userExcluir > 0 && userExcluir <= users.size()) {
            users.remove(userExcluir - 1);
            System.out.println("Usuario excluído com sucesso!");
        } else {
            System.out.println("Número de Usuario Inválido!");
        }
    }
}

// Classe Menu que contém toda a rotina que o sistema deve fazer.
class Menu {
    private CrudDespesa crudDespesa;
    private CrudUsuario crudUsuario;
    private Arquivo arquivo;
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.crudDespesa = new CrudDespesa();
        this.crudUsuario = new CrudUsuario();
        this.arquivo = new Arquivo();
    }

    public void carregarArquivoDespesa() {
        Arquivo.carregarArquivoDespesa(crudDespesa.listaDespesas);
    }

    public void carregarArquivoUser() {
        Arquivo.carregarArquivoUser(crudUsuario.users);
    }

    public void exibirMenuPrincipal() {
        int opcao;
            do {
                System.out.println("\n----- MENU -----");
                System.out.println("1. Gerenciar Despesas");
                System.out.println("2. Anotar Pagamento");
                System.out.println("3. Gerenciar Usuários");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção:\n");
                opcao = scanner.nextInt();
                scanner.nextLine();
    
                switch (opcao) {
                    case 1: // Gerenciar Despesas.
                        gerenciarDespesas();
                        break;
    
                    case 2: // Anotar Pagamento.
                        pagarDespesa();
                        break;
    
                    case 3: // Gerenciar Usuários.
                        gerenciarUsuarios();
                        break;
    
                    case 0: // Sair
                        Arquivo.salvarArquivoDespesa(crudDespesa.listaDespesas);
                        Arquivo.salvarArquivoUser(crudUsuario.users);
                        System.out.println("\nSalvando...\nSaindo, obrigado!");
                        break;
    
                    default:
                        System.out.println("\nOpção inválida. Tente novamente!");
                        break;
                }
    
            } while (opcao != 0);
    
            scanner.close();
        }
        
        private void pagarDespesa() {
            System.out.println("\n----- ANOTAR PAGAMENTO -----");
            System.out.println("Insira o número da despesa a pagar:\n");
            for (int i = 0; i < crudDespesa.listaDespesas.size(); i++) {
                Despesa despesa = crudDespesa.listaDespesas.get(i);
                System.out.println((i + 1) + ". " + despesa);
                }
            int despesaPagar = scanner.nextInt();
            scanner.nextLine();
            crudDespesa.pagarDespesa(despesaPagar - 1);
        }

        private void gerenciarDespesas() {
            int submenu;
    
            do {
                System.out.println("\n----- GERENCIAR DESPESAS -----");
                System.out.println("1. Criar Despesa");
                System.out.println("2. Editar Despesa");
                System.out.println("3. Excluir Despesa");
                System.out.println("4. Relatório de Despesas");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção:\n");
                submenu = scanner.nextInt();
                scanner.nextLine();
    
                switch (submenu) {
                    case 1: // Criar Despesa.
                        crudDespesa.criarDespesa();
                        break;

                    case 2: // Editar Despesa.
                        crudDespesa.editarDespesa();
                        break;
    
                    case 3: // Excluir Despesa.
                        crudDespesa.excluirDespesa();
                        break;
    
                    case 4: // Relatório de Despesas.
                        crudDespesa.listarDespesas();
                        break;
    
                    case 0: // Sair
                        System.out.println("\nVoltando ao Menu Principal!");
                        break;
    
                    default:
                        System.out.println("\nOpção inválida. Tente novamente!\n");
                        break;
                }
    
            } while (submenu != 0);
        }

        private void gerenciarUsuarios() {
            int submenuUsuario;
    
            do {
                System.out.println("\n----- GERENCIAR USUÁRIOS -----");
                System.out.println("1. Cadastrar Usuário");
                System.out.println("2. Editar Usuário");
                System.out.println("3. Listar Usuários");
                System.out.println("4. Excluir Usuário");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção:\n");
                submenuUsuario = scanner.nextInt();
                scanner.nextLine();
    
                switch (submenuUsuario) {
                    case 1: // Cadastrar Usuarios.
                        crudUsuario.cadastrarUser();
                        break;
    
                    case 2: // Editar Usuarios.
                        crudUsuario.editarUser();
                        break;
    
                    case 3: // Listar Usuarios.
                        crudUsuario.listarUser();
                        break;
    
                    case 4: // Excluir Usuarios.
                        crudUsuario.excluirUser();
                        break;
    
                    case 0: // Sair.
                        System.out.println("\nVoltando ao Menu Principal!");
                        break;
    
                    default:
                        System.out.println("\nOpção inválida. Tente novamente!\n");
                        break;
                }
    
            } while (submenuUsuario != 0);
        }
}

// Classe Criptografia responsável pelos métodos de criptografia e descriptografia de senha de usuarios.
class Criptografia {  
    public static String criptografar(String senha) {
        StringBuilder senhaCriptografada = new StringBuilder();
            for (char c : senha.toCharArray()) {
                senhaCriptografada.append((char) (c + 3));
            }
            return senhaCriptografada.toString();
    }

    public static String descriptografar(String senha) {
        StringBuilder senhaDescriptografada = new StringBuilder();
        for (char c : senha.toCharArray()) {
            senhaDescriptografada.append((char) (c - 3));
        }
        return senhaDescriptografada.toString();
    }
}

// Classe Arqvuivo responsavel pelos métodos de carregar e salvar um arquivo.
class Arquivo {
    public static void salvarArquivoDespesa(List<Despesa> listaDespesas) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("despesas.txt"));
            for (Despesa d : listaDespesas) {
                writer.write("Despesa{" +
                "descricao='" + d.getDescricao() + '\'' +
                ", valor=" + d.getValor() +
                ", dataVencimento='" + d.getDataVencimento() + '\'' +
                ", categoria='" + d.getCategoria() + '\'' +
                ", statusPagamento=" + d.getStatusPagamento() +
                '}' + System.lineSeparator());
            }
            System.out.println("Despesas salvas com sucesso!");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de despesas.");
            e.printStackTrace();
        }
    }

    public static void salvarArquivoUser(List<User> users) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt")); 
            for (User user : users) {
                writer.write(user.getLogin() + ";" + user.getSenha());
                writer.newLine();
            }
            System.out.println("Usuários salvos com sucesso!");
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de usuario.");
            e.printStackTrace();
        }
    }

    public static void carregarArquivoDespesa(List<Despesa> listaDespesas) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../despesas.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("***DESPESA***")) {
                    String descricao = reader.readLine();
                    Float valor = Float.parseFloat(reader.readLine());
                    String dataVencimento = reader.readLine();
                    String categoria = reader.readLine();
                    boolean statusPagamento = Boolean.parseBoolean(reader.readLine());
    
                    Despesa despesa;

                    switch (categoria) {
                        case "Alimentacao":
                            despesa = new Alimentacao(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        case "Transporte":
                            despesa = new Transporte(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        case "Moradia":
                            despesa = new Moradia(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        case "Lazer":
                            despesa = new Lazer(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        case "Saude":
                            despesa = new Saude(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        case "Outros":
                            despesa = new Outros(descricao, valor, dataVencimento, statusPagamento);
                            break;
                        default:
                            System.err.println("Categoria desconhecida: " + categoria);
                            continue;
                    }
                    if (despesa != null) {
                        despesa.setStatusPagamento(statusPagamento);
                        listaDespesas.add(despesa);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("\"Arquivo não encontrado.\"");   
        } catch (IOException e) {
            System.err.println("\"Erro ao ler o arquivo de despesas.\"");
            e.printStackTrace();
        }
    }

    public static void carregarArquivoUser(List<User> users) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../usuarios.txt"));
            String line;
        
            while ((line = reader.readLine()) != null) {
                if (line.equals("***USUARIO***")) {
                    String login = reader.readLine();
                    String senha = reader.readLine();
                    
                    if (login != null && senha != null) {
                        users.add(new User(login, senha));
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("\"Arquivo não encontrado.\""); 
        } catch (IOException e) {
            System.err.println("\"Erro ao ler o arquivo de usuario.\"");
            e.printStackTrace();
        }
    }
}

// Classe que possui o método MAIN, onde todo o fluxo acontece
class DespesaMain {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.carregarArquivoDespesa();
        menu.carregarArquivoUser();
        menu.exibirMenuPrincipal();
    }
}

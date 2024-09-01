//Importação de bibliotecas utilizadas.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface que define a característica Pagavel que será utilizada na classe 'Despesa'.
interface Pagavel {
    public boolean pagar();
    public boolean statusPagamento();
}

// Classe responsável por definir o que uma despesa DEVE conter.
public abstract class Despesa implements Pagavel {

    private String descricao;
    private Float valor;
    private String dataVencimento;
    private String categoria;
    private boolean statusPagamento;

    // Método construtor 
    Despesa(String descricao, Float valor, String dataVencimento, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.statusPagamento = false;
    }

    // Métodos de sobrescrita da interfaces
    @Override
    public boolean pagar() {
        if (!this.statusPagamento) {
            this.statusPagamento = true;
            return true; // Pagamento realizado.
        }
        return false; // Quando a despesa já estaviver paga.
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

    Transporte(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Transporte");
    }
}

class Alimentacao extends Despesa {

    Alimentacao(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Alimentação");
    }
}

class Moradia extends Despesa {

    Moradia(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Moradia");
    }
}

class Lazer extends Despesa {

    Lazer(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Lazer");
    }
}

class Saude extends Despesa {

    Saude(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Saúde");
    }
}

class Outros extends Despesa {

    Outros(String descricao, Float valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Outros");
    }
}

// Classe CRUD responsável pelo gerenciamento de despesas.
class CrudDespesa {

    // Array que armazena qualquer variável que seja do tipo Despesa.
    List<Despesa> listaDespesas = new ArrayList<>();
    Despesa despesa = null;

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
        String categoria;
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

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1: // Alimentação
                System.out.println("Criando despesa do tipo Alimentação...");
                despesa = new Alimentacao(descricao, valor, dataVencimento);
                break;
            case 2: // Transporte
                System.out.println("Criando despesa do tipo Transporte...");
                despesa = new Transporte(descricao, valor, dataVencimento);
                break;
            case 3: // Moradia
                System.out.println("Criando despesa do tipo Moradia...");
                despesa = new Moradia(descricao, valor, dataVencimento);
                break;
            case 4: // Lazer
                System.out.println("Criando despesa do tipo Lazer...");
                despesa = new Lazer(descricao, valor, dataVencimento);
                break;
            case 5: // Saúde
                System.out.println("Criando despesa do tipo Saúde...");
                despesa = new Saude(descricao, valor, dataVencimento);
                break;
            case 6: // Outros
                System.out.println("Criando despesa do tipo Outros...");
                despesa = new Outros(descricao, valor, dataVencimento);
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

        System.out.println("Insira o número da Despesa a editar (1-" + listaDespesas.size() + "):");
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

        //Verifica se a descrição está em branco.
        if (!novaDescricao.isEmpty()) {
            despesa.setDescricao(novaDescricao);
        }

        // Valor:
        System.out.println("Insira o novo valor:\n(Digite 0 para não alterar)");
        Float novoValor = scanner.nextFloat();
        scanner.nextLine();

        //Verifica se o valor é 0.
        if (novoValor != 0) {
            despesa.setValor(novoValor);
        }

        // Vencimento:
        System.out.println("Insira a nova data de vencimento:\n(Deixe em branco para não alterar)");
        String novoVencimento = scanner.nextLine();

        //Verifica se o vencimento está em branco.
        if (!novoVencimento.isEmpty()) {
            despesa.setDataVencimento(novoVencimento);
        }

        System.out.println("Despesa atualizada: " + despesa);
    }

    // Método CRUD para listar despesas por status de pagamento e dentro de um período específico.
    public void listarDespesas() { 
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- LISTAR DESPESA -----");

        System.out.println("Listar despesas por:\n1. Pagas\n2. Pendentes");
        int statusEscolhido = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Escolha a data de início do período\n(AAAA-MM-DD):");
        String inicioPeriodo = scanner.nextLine();
    
        System.out.println("Escolha a data de fim do período\n(AAAA-MM-DD):");
        String fimPeriodo = scanner.nextLine();
    
        System.out.println("Despesas filtradas:");
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

        System.out.println("Insira o número da despesa a excluir (1-" + listaDespesas.size() + "):");
        int despesaExcluir = scanner.nextInt();

        if (despesaExcluir > 0 && despesaExcluir <= listaDespesas.size()) {
            listaDespesas.remove(despesaExcluir - 1);
            System.out.println("Despesa excluída com sucesso!");
        } else {
            System.out.println("Número de Despesa Inválido!");
        }
    }
}

// Classe CRUD responsável pelo gerenciamento de usuarios.
class CrudUsuario {
    private List<String> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // Método CRUD de criação de Usuarios.
    public void cadastrarUser() {
        System.out.println("----- CADASTRAR USUARIOS -----");

        System.out.println("Insira um nome de Login:");
        String login = scanner.nextLine();

        System.out.println("Insira uma senha para o login" + login + ":");
        String senha = scanner.nextLine();

        users.add(login + ":" + senha);

        System.out.println("Usuario " + login + " Cadastrado com Sucesso!");
    }

    // Método CRUD para edição de Usuarios.  
    public void editarUser() {
        System.out.println("----- EDITAR USUARIOS -----");

        System.out.println("Insira o número do usuario a editar (1-" + users.size() + "):");
        int userEditar = scanner.nextInt();
        scanner.nextLine();
                    
        System.out.println("Insira o novo Login do user: ");
        String novologin = scanner.nextLine();

        System.out.println("Insira a nova senha do user: ");
        String novaSenha = scanner.nextLine();
                    
        users.set(userEditar - 1, novologin + " " + novaSenha);

        System.out.println("Usuario atualizado!");
    }

    // Método CRUD para listagem de Usuarioss
    public void listarUser() {
        System.out.println("----- LISTAR USUARIOS -----");

        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (String usuario : users) {
            System.out.println(usuario);
        }
    }

    // Método CRUD de exclusão de Usuarios.
    public void excluirUser() {
        System.out.println("----- EXCLUIR USUARIOS -----");

        System.out.println("Insira o número do usuario a excluir (1-" + users.size() + "):");
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
    Scanner scanner = new Scanner(System.in);

    public void exibirMenuPrincipal() {
        int opcao;
            do {
                System.out.println("----- MENU -----");
                System.out.println("1. Gerenciar Despesas");
                System.out.println("2. Anotar Pagamento");
                System.out.println("3. Gerenciar Usuários");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
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
                        System.out.println("Saindo!");
                        break;
    
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        break;
                }
    
            } while (opcao != 0);
    
            scanner.close();
        }
        
        private void pagarDespesa() {
            System.out.println("----- ANOTAR PAGAMENTO -----");
            System.out.println("Insira o número da despesa a pagar (1-" + crudDespesa.listaDespesas.size() + "):");
            int despesaPagar = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
            crudDespesa.pagarDespesa(despesaPagar - 1); // Ajusta o índice para base zero
        }

        private void gerenciarDespesas() {
            int submenu;
    
            do {
                System.out.println("----- GERENCIAR DESPESAS -----");
                System.out.println("1. Editar Despesa");
                System.out.println("2. Excluir Despesa");
                System.out.println("3. Relatório de Despesas");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                submenu = scanner.nextInt();
                scanner.nextLine();
    
                switch (submenu) {
                    case 1: // Editar Despesa.
                        crudDespesa.editarDespesa();
                        break;
    
                    case 2: // Excluir Despesa.
                        crudDespesa.excluirDespesa();
                        break;
    
                    case 3: // Relatório de Despesas.
                        crudDespesa.listarDespesas();
                        break;
    
                    case 0: // Sair
                        System.out.println("Voltando ao Menu Principal!");
                        break;
    
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        break;
                }
    
            } while (submenu != 0);
        }

        private void gerenciarUsuarios() {
            int submenuUsuario;
    
            do {
                System.out.println("----- GERENCIAR USUÁRIOS -----");
                System.out.println("1. Cadastrar Usuário");
                System.out.println("2. Editar Usuário");
                System.out.println("3. Listar Usuários");
                System.out.println("4. Excluir Usuário");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
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
                        System.out.println("Voltando ao Menu Principal!");
                        break;
    
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        break;
                }
    
            } while (submenuUsuario != 0);
        }
}

//


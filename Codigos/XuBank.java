package xuBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XuBank {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();
    private Cliente clienteLogado;

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente criarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();

        Cliente novoCliente = new Cliente(nome, cpf, cpf);
        adicionarCliente(novoCliente);
        System.out.println("Cliente registrado com sucesso.");

        return novoCliente;
    }

    public void criarConta(Cliente cliente, String tipoConta, double rendimentoContratado) {
        String numeroConta = gerarNumeroConta();
        Conta novaConta = null;
        switch (tipoConta) {
            case "corrente":
                novaConta = new ContaCorrente(numeroConta, cliente);
                break;
            case "poupanca":
                novaConta = new ContaPoupanca(numeroConta, cliente);
                break;
            case "renda_fixa":
                novaConta = new ContaRendaFixa(numeroConta, cliente, rendimentoContratado);
                break;
            case "investimento":
                novaConta = new ContaInvestimento(numeroConta, cliente);
                break;
            default:
                System.out.println("Tipo de conta inválido.");
                return;
        }
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso. Número da conta: " + numeroConta);
    }

    private int numeroContaAtual = 1;

    private String gerarNumeroConta() {
        String numeroConta = String.format("%04d", numeroContaAtual);
        numeroContaAtual++;
        return numeroConta;
    }


    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("XuBank - Menu Principal");
            System.out.println("1. Criar Conta");
            System.out.println("2. Acessar Conta");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    acessarConta();
                    break;
                case 3:
                    System.out.println("Obrigado por usar o XuBank. Adeus!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void criarConta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();
        Cliente cliente = encontrarClientePorCPF(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado. Deseja registrar-se como um novo cliente? (S/N)");
            String resposta = scanner.next().toUpperCase();
            if (resposta.equals("S")) {
                cliente = criarCliente();
            } else {
                System.out.println("Operação cancelada.");
                return;
            }
        }

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Conta de Renda Fixa");
        System.out.println("4. Conta de Investimento");
        System.out.print("Escolha uma opção: ");
        int tipoConta = scanner.nextInt();

        switch (tipoConta) {
            case 1:
                criarConta(cliente, "corrente", 0);
                break;
            case 2:
                criarConta(cliente, "poupanca", 0);
                break;
            case 3:
                System.out.print("Digite a taxa de rendimento contratada: ");
                double rendimentoContratado = scanner.nextDouble();
                criarConta(cliente, "renda_fixa", rendimentoContratado);
                break;
            case 4:
                criarConta(cliente, "investimento", 0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void acessarConta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();
        Cliente cliente = encontrarClientePorCPF(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado. Deseja registrar-se como um novo cliente? (S/N)");
            String resposta = scanner.next().toUpperCase();
            if (resposta.equals("S")) {
                cliente = criarCliente();
            } else {
                System.out.println("Operação cancelada.");
                return;
            }
        }

        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.next();
        Conta conta = encontrarContaPorNumero(numeroConta);

        if (conta == null || !conta.getCliente().equals(cliente)) {
            System.out.println("Conta não encontrada ou não pertence a este cliente.");
            return;
        }

        clienteLogado = cliente;

        while (true) {
            System.out.println("Conta logada: " + conta.getNumeroConta());
            System.out.println("Cliente: " + clienteLogado.getNome());
            System.out.println("Saldo: R$" + conta.consultarSaldo());
            System.out.println("XuBank - Menu da Conta");
            System.out.println("1. Realizar Depósito");
            System.out.println("2. Realizar Saque");
            System.out.println("3. Realizar Transferência");
            System.out.println("4. Consultar Extrato");
            System.out.println("5. Consultar Saldo");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado com sucesso.");
                    break;
                case 2:
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    System.out.println("Saque realizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o número da conta de destino: ");
                    String numeroContaDestino = scanner.next();
                    Conta contaDestino = encontrarContaPorNumero(numeroContaDestino);
                    if (contaDestino != null) {
                        System.out.print("Digite o valor da transferência: ");
                        double valorTransferencia = scanner.nextDouble();
                        conta.transferir(contaDestino, valorTransferencia);
                        System.out.println("Transferência realizada com sucesso.");
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                    break;
                case 4:
                    conta.consultarExtrato();
                    break;
                case 5:
                    System.out.println("Saldo: R$" + conta.consultarSaldo());
                    break;
                case 6:
                    clienteLogado = null;
                    System.out.println("Deslogado com sucesso.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private Cliente encontrarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private Conta encontrarContaPorNumero(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        XuBank xubank = new XuBank();
        xubank.menuPrincipal();
    }
}

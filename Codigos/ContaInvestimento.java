package xuBank;

import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento extends Conta implements OperacoesConta {
    private static final double IMPOSTO = 0.15; // 15% de imposto sobre o rendimento
    private List<String> extrato;

    public ContaInvestimento(String numeroConta, Cliente cliente) {
        super(numeroConta, cliente);
        extrato = new ArrayList<>();
    }

    @Override
    public void sacar(double valor) {
        if (valor <= getSaldo()) {
            double impostoSobreRendimento = valor * IMPOSTO;
            saldo -= valor + impostoSobreRendimento;
            extrato.add("Saque: - R$" + valor);
            extrato.add("Imposto sobre rendimento: - R$" + impostoSobreRendimento);
        } else {
            System.out.println("Saldo insuficiente para o saque.");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        extrato.add("Depósito: + R$" + valor);
    }

    @Override
    public void transferir(Conta destino, double valor) {
        if (valor <= getSaldo()) {
            sacar(valor);
            destino.depositar(valor);
            extrato.add("Transferência para conta " + destino.getNumeroConta() + ": - R$" + valor);
        } else {
            System.out.println("Saldo insuficiente para a transferência.");
        }
    }

    @Override
    public void consultarExtrato() {
        System.out.println("Extrato da Conta de Investimento:");
        for (String transacao : extrato) {
            System.out.println(transacao);
        }
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }
}

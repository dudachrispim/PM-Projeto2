package xuBank;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente extends Conta implements OperacoesConta {
    private static final double TAXA_MENSAL = 20;
    private static final double LIMITE_SAQUE_ESPECIAL = 200;
    private List<String> extrato;

    public ContaCorrente(String numeroConta, Cliente cliente) {
        super(numeroConta, cliente);
        extrato = new ArrayList<>();
    }

    @Override
    public void sacar(double valor) {
        if (valor <= getSaldo() + LIMITE_SAQUE_ESPECIAL) {
            saldo -= valor;
            extrato.add("Saque: - R$" + valor);
        } else {
            System.out.println("Saldo insuficiente.");
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
        System.out.println("Extrato da Conta Corrente:");
        for (String transacao : extrato) {
            System.out.println(transacao);
        }
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }
}

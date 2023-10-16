package xuBank;

public interface OperacoesConta {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(Conta destino, double valor);
    void consultarExtrato();
    double consultarSaldo();
}

abstract class Conta implements OperacoesConta {
    private String numeroConta;
    public double saldo;
    private Cliente cliente;

    public Conta(String numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
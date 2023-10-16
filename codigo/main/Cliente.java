package xuBank;

class Cliente {
    private String nome;
    private String CPF;
    private String senha;

    public Cliente(String nome, String CPF, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getSenha() {
        return senha;
    }
}

class ClienteRegular extends Cliente {
    public ClienteRegular(String nome, String CPF, String senha) {
        super(nome, CPF, senha);
    }
}

class ClienteGold extends Cliente {
    public ClienteGold(String nome, String CPF, String senha) {
        super(nome, CPF, senha);
    }
}

class ClienteVip extends Cliente {
    public ClienteVip(String nome, String CPF, String senha) {
        super(nome, CPF, senha);
    }
}

import java.util.ArrayList;

public class Banco {
    private ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public ContaBancaria autenticar(String cpf, String senha) {
        for (ContaBancaria conta : contas) {
            if (conta.getTitular().getCpf().equals(cpf) &&
                conta.getTitular().autenticar(senha)) {
                return conta;
            }
        }
        return null;
    }

    public ContaBancaria buscarContaPorCPF(String cpf) {
        for (ContaBancaria conta : contas) {
            if (conta.getTitular().getCpf().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }
}
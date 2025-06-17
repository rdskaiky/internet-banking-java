import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        // Criando contas de exemplo
        Usuario u1 = new Usuario("Kaiky", "123", "senha123");
        ContaBancaria c1 = new ContaBancaria(u1);
        c1.depositar(1000);
        banco.adicionarConta(c1);

        Usuario u2 = new Usuario("Giovana", "456", "amor456");
        ContaBancaria c2 = new ContaBancaria(u2);
        c2.depositar(2500);
        banco.adicionarConta(c2);

        System.out.println("=== Bem-vindo ao Internet Banking ===");
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        ContaBancaria logado = banco.autenticar(cpf, senha);
        if (logado == null) {
            System.out.println("Acesso negado. CPF ou senha inválido.");
            return;
        }

        System.out.println("Olá, " + logado.getTitular().getNome() + "!");

        int opcao;
        do {
            System.out.println("\n1 - Ver saldo");
            System.out.println("2 - Transferir");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual: R$" + logado.getSaldo());
                    break;

                case 2:
                    sc.nextLine(); // limpar buffer
                    System.out.print("CPF do destinatário: ");
                    String destinoCpf = sc.nextLine();
                    ContaBancaria destino = banco.buscarContaPorCPF(destinoCpf);
                    if (destino == null) {
                        System.out.println("Conta não encontrada.");
                        break;
                    }
                    System.out.print("Valor da transferência: ");
                    double valor = sc.nextDouble();
                    if (logado.transferir(destino, valor)) {
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Saldo insuficiente.");
                    }
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);
    }
}
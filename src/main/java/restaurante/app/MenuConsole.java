package restaurante.app;

import restaurante.cardapio.ItemCardapio;
import restaurante.pedido.Pedido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);

    public int exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Alterar ou cancelar pedido");
        System.out.println("0 - Sair");
        return lerInteiro("Escolha uma opção: ", 0, 4);
    }

    public String lerNomeCliente() {
        while (true) {
            System.out.print("Nome do cliente: ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) return nome;
            System.out.println("O nome não pode ficar vazio.");
        }
    }

    public int lerOpcaoLanche() {
        System.out.println("\nCardápio:");
        System.out.println("1 - X-Burger (R$ 16,00)");
        System.out.println("2 - X-Salada (R$ 18,00)");
        System.out.println("3 - X-Bacon (R$ 22,00)");
        return lerInteiro("Escolha o lanche: ", 1, 3);
    }

    public boolean[] lerIngredientesRetirados() {
        boolean[] retirar = new boolean[4];
        retirar[0] = lerSimNao("Retirar queijo? (s/n): ");
        retirar[1] = lerSimNao("Retirar alface? (s/n): ");
        retirar[2] = lerSimNao("Retirar tomate? (s/n): ");
        retirar[3] = lerSimNao("Retirar cebola? (s/n): ");
        return retirar;
    }

    public boolean[] lerAdicionais() {
        boolean[] adicionais = new boolean[2];
        adicionais[0] = lerSimNao("Adicionar cheddar por R$ 4,00? (s/n): ");
        adicionais[1] = lerSimNao("Adicionar bacon extra por R$ 6,00? (s/n): ");
        return adicionais;
    }

    public boolean desejaAdicionarItem() {
        return lerSimNao("Adicionar mais um item ao pedido? (s/n): ");
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("Pedido " + pedido.getNumero() + " - " + pedido.getNomeCliente());
        int numero = 1;
        for (ItemCardapio item : pedido.getItens()) {
            System.out.printf("%d. %s - R$ %.2f%n", numero++, item.getDescricao(), item.getPreco());
        }
        System.out.printf("Total: R$ %.2f%n", pedido.calcularTotal());
    }

    public void exibirPedidos(ArrayList<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Ainda não há pedidos.");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
            System.out.println();
        }
    }

    public int lerNumeroPedido() {
        return lerInteiro("Número do pedido: ", 1, Integer.MAX_VALUE);
    }

    public int exibirOpcoesAlteracao() {
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido inteiro");
        System.out.println("0 - Voltar");
        return lerInteiro("Escolha uma opção: ", 0, 3);
    }

    public int lerNumeroItem(int quantidade) {
        return lerInteiro("Número do item que deseja remover: ", 1, quantidade);
    }

    public void exibirFechamentoDoDia(double total, HashSet<String> descricoes) {
        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.printf("Total vendido: R$ %.2f%n", total);
        System.out.println("Combinações diferentes: " + descricoes.size());
        for (String descricao : descricoes) System.out.println("- " + descricao);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    private boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = scanner.nextLine().trim();
            if (resposta.equalsIgnoreCase("s")) return true;
            if (resposta.equalsIgnoreCase("n")) return false;
            System.out.println("Digite s para sim ou n para não.");
        }
    }

    private int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                if (valor >= minimo && valor <= maximo) return valor;
            } catch (InputMismatchException erro) {
                scanner.nextLine();
            }
            System.out.println("Opção inválida. Digite um número entre " + minimo + " e " + maximo + ".");
        }
    }
}

package restaurante.app;

import restaurante.cardapio.ItemCardapio;
import restaurante.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade sistema = new RestauranteFacade();
        MenuConsole menu = new MenuConsole();
        boolean funcionando = true;

        while (funcionando) {
            int opcao = menu.exibirMenu();
            switch (opcao) {
                case 1:
                    registrarPedido(menu, sistema);
                    break;
                case 2:
                    menu.exibirPedidos(sistema.getPedidos());
                    break;
                case 3:
                    menu.exibirFechamentoDoDia(sistema.calcularTotalDoDia(), sistema.getDescricoesDoDia());
                    funcionando = false;
                    break;
                case 4:
                    alterarPedido(menu, sistema);
                    break;
                case 0:
                    menu.exibirMensagem("Sistema encerrado.");
                    funcionando = false;
                    break;
                default:
                    menu.exibirMensagem("Opção inválida.");
                    break;
            }
        }
    }

    private static void registrarPedido(MenuConsole menu, RestauranteFacade sistema) {
        Pedido pedido = sistema.criarPedido(menu.lerNomeCliente());
        do {
            ItemCardapio item = lerNovoItem(menu, sistema);
            sistema.adicionarItem(pedido.getNumero(), item);
        } while (menu.desejaAdicionarItem());

        menu.exibirPedido(pedido);
    }

    private static ItemCardapio lerNovoItem(MenuConsole menu, RestauranteFacade sistema) {
        int lanche = menu.lerOpcaoLanche();
        boolean[] retirar = menu.lerIngredientesRetirados();
        boolean[] adicionais = menu.lerAdicionais();
        return sistema.criarItem(lanche, retirar, adicionais);
    }

    private static void alterarPedido(MenuConsole menu, RestauranteFacade sistema) {
        if (sistema.getPedidos().isEmpty()) {
            menu.exibirMensagem("Ainda não há pedidos para alterar.");
            return;
        }

        menu.exibirPedidos(sistema.getPedidos());
        int numero = menu.lerNumeroPedido();
        Pedido pedido = sistema.buscarPedido(numero);
        if (pedido == null) {
            menu.exibirMensagem("Pedido não encontrado.");
            return;
        }

        int opcao = menu.exibirOpcoesAlteracao();
        switch (opcao) {
            case 1:
                sistema.adicionarItem(numero, lerNovoItem(menu, sistema));
                menu.exibirMensagem("Item acrescentado ao pedido.");
                break;
            case 2:
                if (pedido.getItens().isEmpty()) {
                    menu.exibirMensagem("Esse pedido não tem itens para remover.");
                    return;
                }
                menu.exibirPedido(pedido);
                int numeroItem = menu.lerNumeroItem(pedido.getItens().size());
                if (sistema.removerItem(numero, numeroItem)) {
                    menu.exibirMensagem("Item removido.");
                }
                break;
            case 3:
                sistema.cancelarPedido(numero);
                menu.exibirMensagem("Pedido cancelado.");
                break;
            case 0:
                menu.exibirMensagem("Nenhuma alteração feita.");
                break;
            default:
                menu.exibirMensagem("Opção inválida.");
                break;
        }
    }
}
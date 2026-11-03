package restaurante.app;

import restaurante.cardapio.ComBaconExtra;
import restaurante.cardapio.ComCheddar;
import restaurante.cardapio.ItemCardapio;
import restaurante.cardapio.Lanche;
import restaurante.cardapio.LancheFactory;
import restaurante.pedido.GerenciadorPedidos;
import restaurante.pedido.Pedido;

import java.util.ArrayList;
import java.util.HashSet;

public class RestauranteFacade {
    private GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) { return gerenciador.criarPedido(nomeCliente); }
    public Pedido buscarPedido(int numero) { return gerenciador.buscarPedido(numero); }
    public ArrayList<Pedido> getPedidos() { return gerenciador.getPedidos(); }
    public boolean cancelarPedido(int numero) { return gerenciador.cancelarPedido(numero); }
    public boolean removerItem(int numeroPedido, int numeroItem) {
        return gerenciador.removerItem(numeroPedido, numeroItem);
    }
    public void adicionarItem(int numeroPedido, ItemCardapio item) {
        gerenciador.adicionarItem(numeroPedido, item);
    }
    public double calcularTotalDoDia() { return gerenciador.calcularTotalDoDia(); }

    public HashSet<String> getDescricoesDoDia() {
        HashSet<String> descricoes = new HashSet<>();
        for (Pedido pedido : gerenciador.getPedidos()) {
            for (ItemCardapio item : pedido.getItens()) descricoes.add(item.getDescricao());
        }
        return descricoes;
    }

    public ItemCardapio criarItem(int opcaoLanche, boolean[] retirar, boolean[] adicionais) {
        Lanche lanche;
        switch (opcaoLanche) {
            case 1:
                lanche = LancheFactory.xBurger();
                break;
            case 2:
                lanche = LancheFactory.xSalada();
                break;
            case 3:
                lanche = LancheFactory.xBacon();
                break;
            default:
                throw new IllegalArgumentException("Opção de lanche inválida.");
        }
        if (retirar[0]) lanche.setQueijo(false);
        if (retirar[1]) lanche.setAlface(false);
        if (retirar[2]) lanche.setTomate(false);
        if (retirar[3]) lanche.setCebola(false);

        ItemCardapio item = lanche;
        if (adicionais[0]) item = new ComCheddar(item);
        if (adicionais[1]) item = new ComBaconExtra(item);
        return item;
    }
}

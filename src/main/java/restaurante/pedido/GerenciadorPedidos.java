package restaurante.pedido;

import restaurante.cardapio.ItemCardapio;
import java.util.ArrayList;
import java.util.HashMap;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public Pedido buscarPedido(int numero){
        return pedidos.get(numero);
    }

    public ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        for (Pedido pedido : pedidos.values()) {
            listaPedidos.add(pedido);
        }
        return listaPedidos;
    }

    public boolean cancelarPedido(int numero) {
        return pedidos.remove(numero) != null;
    }

    public boolean removerItem(int numeroPedido, int numeroItem) {
        Pedido pedido = buscarPedido(numeroPedido);
        return pedido != null && pedido.removerItem(numeroItem);
    }

    public void adicionarItem(int numeroPedido, ItemCardapio item) {
        Pedido pedido = buscarPedido(numeroPedido);
        if (pedido == null)
            throw new IllegalArgumentException("Pedido não encontrado.");
        pedido.adicionarItem(item);
    }

    public double calcularTotalDoDia() {
        double total = 0;
        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
            return total;
    }
}
package restaurante.pedido;

import restaurante.cardapio.ItemCardapio;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens;

    public Pedido(int numero, String nomeCliente) {
        if (numero <= 0){
            throw new IllegalArgumentException("O número do pedido deve ser positivo.");
        }
        this.numero = numero;
        setNomeCliente(nomeCliente);
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        if (item == null) throw new IllegalArgumentException("O item não pode ser nulo.");
        itens.add(item);
    }

    public boolean removerItem(int numeroItem) {
        int indice = numeroItem - 1;
        if (indice < 0 || indice >= itens.size()) return false;
        itens.remove(indice);
        return true;
    }

    public ArrayList<ItemCardapio> getItens() {
        return new ArrayList<>(itens);
    }

    public double calcularTotal() {
        double soma = 0;
        for (ItemCardapio item : itens) soma += item.getPreco();
        return soma;

    }

    public int getNumero() { return numero; }
    public String getNomeCliente() { return nomeCliente; }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser nulo nem vazio.");
        }
        this.nomeCliente = nomeCliente;
    }
}

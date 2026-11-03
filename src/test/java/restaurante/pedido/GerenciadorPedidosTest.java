package restaurante.pedido;

import org.junit.jupiter.api.Test;
import restaurante.cardapio.LancheFactory;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorPedidosTest {
    @Test
    void criaPedidosComNumerosCrescentesESomaOTotalDoDia() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();
        Pedido primeiro = gerenciador.criarPedido("Ana");
        Pedido segundo = gerenciador.criarPedido("Bia");
        primeiro.adicionarItem(LancheFactory.xBurger());
        segundo.adicionarItem(LancheFactory.xSalada());

        assertEquals(1, primeiro.getNumero());
        assertEquals(2, segundo.getNumero());
        assertEquals(34.00, gerenciador.calcularTotalDoDia(), 0.001);
    }

    @Test
    void pedidoCanceladoDeixaDeEntrarNoTotal() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();
        Pedido pedido = gerenciador.criarPedido("Caio");
        pedido.adicionarItem(LancheFactory.xBacon());

        assertTrue(gerenciador.cancelarPedido(pedido.getNumero()));
        assertEquals(0.00, gerenciador.calcularTotalDoDia(), 0.001);
    }
}
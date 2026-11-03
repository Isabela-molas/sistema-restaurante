package restaurante.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurante.cardapio.Lanche;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    private Pedido pedido;
    private Lanche lanche1;
    private Lanche lanche2;

    @BeforeEach
    void criarObjeto() {
        pedido = new Pedido(1, "Isabela");
        lanche1 = new Lanche("X-Burguer", 20.0);
        lanche2 = new Lanche("Refrigerante", 5.50);
    }

    @Test
    void adicionarItem() {
        pedido.adicionarItem(lanche1);
        assertEquals(1, pedido.getItens().size());
        assertEquals(lanche1, pedido.getItens().get(0));
    }

    @Test
    void calcularTotal() {
        pedido.adicionarItem(lanche1);
        pedido.adicionarItem(lanche2);
        assertEquals(25.50, pedido.calcularTotal(), 0.001);
    }

    @Test
    void setNomeCliente() {
        pedido.setNomeCliente("Maria");
        assertEquals("Maria", pedido.getNomeCliente());
        assertThrows(IllegalArgumentException.class, () -> pedido.setNomeCliente(null));
        assertThrows(IllegalArgumentException.class, () -> pedido.setNomeCliente(""));
        assertThrows(IllegalArgumentException.class, () -> pedido.setNomeCliente("   "));
    }

    @Test
    void removerItemAtualizaOTotal() {
        pedido.adicionarItem(lanche1);
        pedido.adicionarItem(lanche2);

        assertTrue(pedido.removerItem(1));
        assertEquals(5.50, pedido.calcularTotal(), 0.001);
        assertFalse(pedido.removerItem(5));
    }
}
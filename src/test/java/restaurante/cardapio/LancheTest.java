package restaurante.cardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LancheTest {
    private Lanche lanche;

    @BeforeEach
    void criarObjeto() {
        lanche = new Lanche("X-Burguer", 20.50);
    }

    @Test
    void deveAlterarNomeComSucesso() {
        lanche.setNome("X-Salada");
        assertEquals("X-Salada", lanche.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNuloOuVazio() {
        assertThrows(IllegalArgumentException.class, () -> lanche.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> lanche.setNome(""));
        assertThrows(IllegalArgumentException.class, () -> lanche.setNome("   "));
    }

    @Test
    void deveAlterarPrecoComSucesso() {
        lanche.setPreco(25.50);
        assertEquals(25.50, lanche.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> lanche.setPreco(-0.90));
    }
}
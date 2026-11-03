# 🍔 Sistema de Restaurante

Aplicação em Java para montar pedidos, personalizar lanches e acompanhar o fechamento do dia.

O projeto foi organizado por responsabilidades: a interface do terminal recebe as escolhas da pessoa usuária, as classes de pedido controlam as comandas e as classes do cardápio representam os lanches e seus adicionais.

## ✨ Funcionalidades

- Criar pedidos com numeração automática e nome do cliente.
- Escolher X-Burger, X-Salada ou X-Bacon.
- Retirar ingredientes sem alterar o preço do lanche.
- Adicionar cheddar ou bacon extra ao lanche.
- Consultar pedidos, adicionar ou remover itens e cancelar uma comanda.
- Fechar o dia com o total vendido e as combinações diferentes pedidas.
- Tratar entradas inválidas e permitir que a pessoa tente novamente.

## 🧩 Organização do projeto

```text
src/
├── main/java/restaurante/
│   ├── app/          # Main, menu do terminal e coordenação do sistema
│   ├── cardapio/     # Lanche, interface, fábrica e adicionais
│   └── pedido/       # Pedido individual e gerenciamento das comandas
└── test/java/        # Testes automatizados com JUnit
```

O pacote `app` separa a interação e a coordenação. `cardapio` concentra os itens que podem ser vendidos. `pedido` mantém os dados e as operações das comandas. O módulo financeiro é um bônus opcional e não foi incluído nesta versão.

## 🏗️ Arquitetura

O fluxo principal passa pelo menu e pela fachada, que encaminha as operações às classes de pedidos e do cardápio.

```text
Main
├── MenuConsole             recebe opções e mostra informações
└── RestauranteFacade       coordena as operações
    ├── GerenciadorPedidos  cria, busca e mantém os pedidos
    │   └── Pedido          guarda os itens e calcula o total
    └── LancheFactory       cria os lanches padrão
        └── ItemCardapio    tipo comum para lanches e adicionais
```

## 💻 Tecnologias utilizadas

- **Java 21** — linguagem e plataforma da aplicação.
- **Maven** — organização do projeto e gerenciamento de dependências.
- **JUnit Jupiter 5.10.2** — criação dos testes unitários.
- **Git** — controle de versão do projeto.
- **IntelliJ IDEA** — ambiente usado para abrir e executar o projeto.

## 🧠 Conceitos e padrões aplicados

- **Encapsulamento:** os dados das classes ficam privados e são acessados por métodos.
- **Interface e polimorfismo:** `ItemCardapio` permite tratar lanches e adicionais pelo mesmo tipo.
- **Composição:** um pedido guarda vários itens, e cada adicional guarda o item que está personalizando.
- **Factory:** `LancheFactory` centraliza a criação dos lanches padrão e devolve uma nova instância a cada chamada.
- **Decorator:** `ComCheddar` e `ComBaconExtra` envolvem um item e acrescentam preço e descrição sem alterar `Lanche`.
- **Facade:** `RestauranteFacade` oferece um ponto único para coordenar operações do sistema.
- **Coleções:** `ArrayList` guarda os itens e monta a lista de pedidos para exibição, `HashMap` mantém os pedidos associados aos seus números e `HashSet` reúne descrições diferentes no fechamento.
- **Validação e exceções:** nomes, preços, itens e opções são verificados para impedir dados inválidos.
- **Testes unitários:** JUnit verifica validações, adicionais, totais, numeração e cancelamento de pedidos.

## ▶️ Como executar

**Requisitos:** JDK 21 e Maven.

No IntelliJ IDEA, abra `src/main/java/restaurante/app/Main.java` e execute a classe `Main`.

Para executar os testes pelo terminal, na pasta do projeto, use:

```bash
mvn test
```

## 🎥 Demonstração

Vídeo do sistema em funcionamento: **adicione aqui o link depois de publicar o vídeo**.

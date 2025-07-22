# JavaPL-DesafioCartas
Desafio Técnico, focado na criação de um serviço de jogo de cartas.

## Arquitetura

Este projeto foi desenvolvido utilizando a **Arquitetura Hexagonal** (Portas e Adaptadores). Essa escolha foi motivada pela necessidade de isolar a lógica de negócio (o "core" do jogo) das suas dependências externas. A principal motivação disso é a integração com a API cliente externa deckofcardsapi para gerenciar o baralho e as cartas.

Ao utilizar a Arquitetura Hexagonal, garantimos que a lógica do jogo não seja acoplada diretamente a essa API externa. Se no futuro for necessário trocar o fornecedor do baralho ou adicionar uma nova interface (como um front-end diferente), o impacto no código principal será mínimo, pois a comunicação é feita através de "portas" e "adaptadores" bem definidos.

## Tecnologias Utilizadas

O projeto é construído com Spring Boot e utiliza as seguintes ferramentas e bibliotecas:

- Java 21
- Framework: Spring Boot 3.5.3
- Banco de Dados:
    - Produção: MySQL (via Docker)
    - Testes: H2 Database (banco de dados em memória)
- ORM: Spring Data JPA
- Conectividade Externa: Spring Cloud OpenFeign (para comunicação com APIs REST externas, como a deckofcardsapi.com)
- Mapeamento de Objetos: MapStruct 1.6.3
- Documentação API: Springdoc OpenAPI UI (Swagger UI)
- Contêineres: Docker e Docker Compose

## Estratégias de Teste

A qualidade do código é assegurada através de uma abordagem de testes em diferentes níveis:

- **Testes Unitários**: Focam no isolamento e validação de unidades individuais de código (métodos e classes), garantindo que cada componente funcione conforme o esperado. Utilizam JUnit 5 e Mockito para mockar dependências.

- **Testes de Slice (ou Integração de Camada)**: Testam fatias específicas da aplicação, como a camada Web (Controllers). Utilizam MockMvc para simular requisições HTTP e verificar o comportamento dos endpoints sem iniciar um servidor real.

- **Testes de Integração**: Validam a interação entre múltiplos componentes e camadas da aplicação, incluindo a persistência de dados. Esses testes verificam se as diferentes partes do sistema funcionam em conjunto.

## Para Executar a Aplicação
1. Certifique-se de ter Docker e Docker Compose instalados.

2. No diretório raiz do projeto, execute o comando:  
   ``` docker-compose up --build -d ```

## Acesso a Documentação Swagger

Após a aplicação subir, acesse a documentação interativa da API em seu navegador:
http://localhost:8080/swagger-ui/index.html

(A porta 8080 é a padrão, mas verifique se há alguma configuração diferente no seu application.properties/application.yml).

.
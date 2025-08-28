# Sistema de Gestão de Serviços (SGS)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)
![Maven](https://img.shields.io/badge/Maven-4.0-red)

Este é um projeto didático de um Sistema de Gestão de Serviços (SGS) desenvolvido para aplicar conceitos avançados de Java, Spring Boot e Padrões de Projeto de software em um cenário prático. A aplicação permite o gerenciamento de clientes, funcionários e ordens de serviço, com uma API REST robusta e uma interface web simples para interação.

## ✨ Funcionalidades Principais

-   **Gerenciamento de Clientes:** Cadastro, listagem, atualização e exclusão (CRUD) de clientes.
-   **Gerenciamento de Funcionários:** CRUD completo de funcionários.
-   **Ordens de Serviço (OS):**
    -   Abertura de novas OS, associando um cliente e um funcionário.
    -   Diferenciação entre tipos de OS (Preventiva e Corretiva).
    -   Atualização de status.
    -   Finalização da OS com cálculo de custo automático baseado no tipo.
-   **Interface Web:** Uma interface simples construída com Thymeleaf e Bootstrap para testar e interagir com todas as funcionalidades visualmente.
-   **API REST:** Endpoints bem definidos para todas as entidades, permitindo a integração com outros sistemas.

## 📐 Padrões de Projeto Aplicados

Um dos principais objetivos deste projeto foi a aplicação prática de padrões de projeto clássicos:

-   **Observer:** Utilizado para notificar outras partes do sistema quando eventos importantes ocorrem.
    -   **Exemplo:** Ao criar um novo cliente ou funcionário, um evento é disparado e um "Listener" (observador) simula uma notificação (imprimindo no console). O mesmo ocorre quando o status de uma OS é alterado.
-   **Strategy:** Aplicado para encapsular diferentes algoritmos de cálculo de custo.
    -   **Exemplo:** O custo final de uma Ordem de Serviço é calculado por uma estratégia específica, dependendo se ela é do tipo `PREVENTIVA` (custo fixo) ou `CORRETIVA` (custo variável).
-   **Factory Method:** O conceito foi aplicado na camada de serviço, onde o `OrdemServicoService` atua como uma "fábrica" para criar e configurar novas instâncias de Ordens de Serviço, garantindo que elas sejam criadas em um estado consistente.
-   **Repository:** Implementado através do Spring Data JPA, abstraindo completamente o acesso aos dados e separando a lógica de negócio da persistência.

## 🛠️ Tecnologias Utilizadas

-   **Back-end:**
    -   Java 21
    -   Spring Boot 3
    -   Spring Web
    -   Spring Data JPA (Hibernate)
    -   Spring Security (configurado para desenvolvimento)
-   **Front-end:**
    -   Thymeleaf (Motor de Templates)
    -   Bootstrap 5 (via WebJars)
-   **Banco de Dados:**
    -   PostgreSQL
-   **Build & Dependências:**
    -   Apache Maven

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para executar o projeto em sua máquina local.

### Pré-requisitos

-   [Git](https://git-scm.com/)
-   [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#jdk21-linux)
-   [Apache Maven](https://maven.apache.org/download.cgi)
-   [Docker](https://www.docker.com/products/docker-desktop/) (Recomendado para o banco de dados)

### Instalação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/sistema-gestao-servicos.git](https://github.com/seu-usuario/sistema-gestao-servicos.git)
    cd sistema-gestao-servicos
    ```
    *(Lembre-se de substituir `seu-usuario` pelo seu nome de usuário no GitHub)*

2.  **Inicie o banco de dados PostgreSQL com Docker:**
    *Abra um terminal e execute o comando abaixo para criar um container com o banco de dados já configurado.*
    ```bash
    docker run --name sgs-postgres -e POSTGRES_USER=sgs_user -e POSTGRES_PASSWORD=sgs_password -e POSTGRES_DB=sgs_db -p 5432:5432 -d postgres
    ```

3.  **Verifique as configurações:**
    *O arquivo `src/main/resources/application.properties` já está configurado para se conectar a este banco de dados Docker.*

4.  **Execute a aplicação com Maven:**
    *Ainda no terminal, na raiz do projeto, execute o comando:*
    ```bash
    mvn spring-boot:run
    ```

5.  **Acesse a aplicação:**
    *Após a inicialização, a aplicação estará disponível no seu navegador!*

## 🌐 Acessando a Interface Web

-   **Página Principal (Ordens de Serviço):** `http://localhost:8080/`
-   **Gerenciar Clientes:** `http://localhost:8080/web/clientes`
-   **Gerenciar Funcionários:** `http://localhost:8080/web/funcionarios`

---

Feito com ❤️ por **[Seu Nome Aqui]**.
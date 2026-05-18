# Sistema de Contas e Transferências

Sistema web desenvolvido em Java para gerenciamento de contas bancárias e transferências financeiras.

O projeto foi desenvolvido utilizando Java Web com Servlets, JSP, JDBC e PostgreSQL, sem utilização de frameworks ORM ou Spring Boot.

---

# 🚀 Tecnologias Utilizadas

- Java 11
- Maven 3.8+
- Servlet/JSP
- JSTL
- JDBC
- PostgreSQL 16
- Docker
- Docker Compose
- Apache Tomcat 7

---

# 📁 Estrutura do Projeto

```text
src/main/java/br/com/erionmaia
│
├── controller     # Servlets/controllers
├── dao            # Camada de acesso a dados
├── dto            # Objetos de transferência de dados
├── mapper         # Conversão entre entidades e DTOs
├── model          # Entidades do sistema
├── service        # Regras de negócio
└── util           # Utilitários e conexão com banco
```

---

# 🏗️ Arquitetura

O projeto foi estruturado utilizando arquitetura em camadas:

- **Controller** → Recebe requisições HTTP
- **Service** → Contém regras de negócio
- **DAO** → Responsável pela persistência dos dados
- **DTO** → Transporte de dados
- **Mapper** → Conversão entre entidades e DTOs
- **Model** → Representação das entidades do sistema
- **Util** → Utilitários e gerenciamento de conexão

---

# ⚙️ Funcionalidades

- Cadastro de contas
- Listagem de contas com paginação
- Atualização de contas
- Exclusão de contas
- Transferência entre contas
- Controle de saldo
- Histórico de movimentações
- Extrato bancário

---

# ⭐ Diferenciais Implementados

- Docker para banco de dados
- PostgreSQL 16
- JDBC puro
- Arquitetura em camadas
- DTO Pattern
- Mapper Pattern
- Paginação
- Validações de regras de negócio
- Script automático de inicialização do banco

---

# 📋 Pré-requisitos

Antes de executar o projeto é necessário possuir instalado:

- Java 11
- Maven 3.8+
- Docker
- Docker Compose
- Apache Tomcat 9+

---

# 🐳 Executando o Banco de Dados com Docker

## Subindo o PostgreSQL

Na raiz do projeto execute:

```bash
docker-compose up -d
```

O PostgreSQL será iniciado automaticamente com as configurações abaixo:

| Configuração | Valor |
|---|---|
| Database | contas_db |
| Usuário | postgres |
| Senha | postgres |
| Porta | 5433 |

---

# 🗄️ Inicialização Automática do Banco

O projeto possui um script `init.sql`.

Ao subir o container do PostgreSQL, todas as tabelas e estruturas necessárias serão criadas automaticamente.

Não é necessário executar scripts manualmente.

---

# 🗂️ Estrutura do Banco

Principais tabelas:

- `contas`
- `movimentacoes`

---

# ▶️ Executando o Projeto

## 1 - Clonar o repositório

```bash
git clone https://github.com/erionmaia/teste-analista-dev-java-web.git
```

---

## 2 - Entrar no diretório do projeto

```bash
cd teste-analista-dev-java-web/sistema-contas-transferencias
```

---

## 3 - Subir o banco PostgreSQL

```bash
docker-compose up -d
```

---

## 4 - Compilar o projeto

```bash
mvn clean install
```

---

## 5 - Gerar o arquivo WAR

```bash
mvn package
```

O arquivo será gerado em:

```text
target/sistema-contas-transferencias.war
```

---

# 🌐 Configurando o Apache Tomcat

## Download do Tomcat

Baixe o Apache Tomcat 7:

Versão 7.0.47

---

## Executar a Aplicação

Na raiz do projeto, digite:

```
mvn tomcat7:run
```

---

## Inicializando o Tomcat

Após iniciar o Tomcat, o WAR será extraído automaticamente.

---

# 🌍 URLs da Aplicação

Após subir o Tomcat, o sistema estará disponível em:

## Página Inicial

```text
http://localhost:8080/
```

## Contas

```text
http://localhost:8080/contas
```

## Transferências

```text
http://localhost:8080/movimentacoes
```

---

# ⚙️ Configuração do Banco de Dados

As configurações de conexão estão em:

```text
src/main/java/br/com/erionmaia/util/ConnectionFactory.java
```

Configuração padrão:

```java
private static final String URL =
    "jdbc:postgresql://localhost:5433/java_web_db";

private static final String USER = "postgres";

private static final String PASSWORD = "postgres";
```

---

# 📌 Regras de Negócio

- Não é permitido criar contas com saldo negativo
- O número da conta deve ser único
- Não é permitido realizar transferências sem saldo suficiente
- Todas as movimentações são registradas
- O saldo é atualizado automaticamente após transferências

---

# 🧪 Fluxo Rápido para Testes

## 1 - Criar duas contas

Cadastre duas contas diferentes no sistema.

---

## 2 - Realizar uma transferência

Realize uma transferência entre as contas cadastradas.

---

## 3 - Consultar extrato

Visualize o extrato da conta para validar:

- Entrada/Saída
- Histórico de movimentações
- Atualização de saldo

---

# 📄 Endpoints/Rotas

| Funcionalidade | Rota |
|---|---|
| Listar contas | `/contas` |
| Criar conta | `/contas/nova` |
| Editar conta | `/contas/editar` |
| Excluir conta | `/contas/excluir` |
| Inativar conta | `/contas/inativar` |
| Transferência | `/movimentacoes` |
| Extrato | `/extrato` |

---

# 👨‍💻 Autor

Desenvolvido por Erion Maia

GitHub: https://github.com/erionmaia

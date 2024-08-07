# Product Stock Control API

Esta aplicação fornece uma API para o cadastro de produtos em uma loja. Além de permitir o registro de produtos e seus respectivos fornecedores, a aplicação também envia um e-mail para o fornecedor notificando-o sobre a chegada do produto na loja. 

## Status do Projeto

**Em Aprimoramento**: Este projeto está funcionando, porém ainda sigo implementando melhorias e otimizações.

## Funcionalidades

- Crud de produtos.
- Crud de fornecedores.
- Envio de e-mail para o fornecedor ao registrar um produto.
- Autenticação e autorização com JWT.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Swagger para documentação da API
- MySQL para banco de dados
- Flyway para migração de banco de dados
- JavaMailSender para envio de e-mails
- Thymeleaf para templates de e-mail

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── com/api/product/stock/control/
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   ├── ProductController.java
│   │   │   │   └── SupplierController.java
│   │   │   ├── infra/
│   │   │   │   ├── AuthenticationService.java
│   │   │   │   ├── DataTokenJWTDio.java
│   │   │   │   ├── EmailService.java
│   │   │   │   ├── ErrorHandler.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SecurityFilter.java
│   │   │   │   └── TokenService.java
│   │   │   ├── product/
│   │   │   │   ├── DataProductDto.java
│   │   │   │   ├── Product.java
│   │   │   │   ├── ProductRepository.java
│   │   │   │   └── RegisterProductDto.java
│   │   │   ├── supplier/
│   │   │   │   ├── DataSupplierDto.java
│   │   │   │   ├── RegisterSupplierDto.java
│   │   │   │   ├── Supplier.java
│   │   │   │   └── SupplierRepository.java
│   │   │   ├── user/
│   │   │   │   ├── RegisterAuthenticationDto.java
│   │   │   │   ├── User.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── UserService.java
│   │   │   └── ProductStockControlApplication.java
│   └── resources/
│       ├── application.properties
│       ├── static/
│       │   └── img/
│       │       └── img.png
│       ├── templates/
│       │   └── registration.html
│       └── db/migration/
│           ├── V1__CREATE_TABLE_tb_product.sql
│           ├── V2__ALTER_TABLE_tb_product_add_batch.sql
│           ├── V3__CREATE_TABLE_tb_user.sql
│           ├── V4__CREATE_TABLE_tb_supplier.sql
│           └── V5__ALTER_TABLE_tb_product_add_id_supplier.sql 
```
## Migração do Banco de Dados

O Flyway é utilizado para gerenciar as migrações do banco de dados. As migrações estão localizadas em src/main/resources/db/migration.

## Segurança

A segurança é implementada usando Spring Security e JWT. O SecurityConfig.java configura as regras de segurança, enquanto o TokenService.java lida com a geração e validação dos tokens JWT.

## Envio de E-mails

O EmailService.java utiliza o JavaMailSender para enviar e-mails de notificação para os fornecedores. A formatação do e-mail é feita usando um template Thymeleaf localizado em src/main/resources/templates/registration.html.

## Documentação da API

A documentação da API está disponível no Swagger. Após iniciar o projeto, acesse http://localhost:8080/swagger-ui.html para visualizar a documentação.

## Como Utilizar

### Requisitos

- **Java 11** ou superior
- **Maven** para gerenciamento de dependências
- **MySQL** para banco de dados

### Passos para Configuração

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/product-stock-control.git

2. **Navegue até o diretório do projeto:**

   ```bash
   git clone https://github.com/seu-usuario/product-stock-control.git

3. **Configure o Banco de Dados:**

   Crie um banco de dados MySQL com o nome productsapi:

      ```bash
   CREATE DATABASE productsapi;

4. **Atualize as credenciais de conexão com o banco de dados no arquivo src/main/resources/application.properties**

5. **Configure o Servidor de E-mail no arquivo src/main/resources/application.properties:**

6. **Compile e Inicie o Projeto:**

     ```bash
   ./mvnw spring-boot:run

7. **Acesse a Documentação da API:**

Após iniciar o projeto, acesse a documentação da API no Swagger: http://localhost:8080/swagger-ui.html

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (git checkout -b feature/nova-feature).
3. Commit suas mudanças (git commit -am 'Adicionei uma nova feature').
4. Faça push para a branch (git push origin feature/nova-feature).
5. Crie um novo Pull Request.

## Contato

- Email: correaferreiraalex92@gmail.com
- LinkedIn: https://www.linkedin.com/in/alexferreira92

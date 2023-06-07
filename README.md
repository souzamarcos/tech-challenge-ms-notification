# Tech Challenge FIAP - Burger
Projeto da pós graduação da FIAP de Arquitetura de Software

## Documentos

* [Event Storm - MIRO](https://miro.com/app/board/uXjVMK9Fze8=/?share_link_id=624130302810)

## Requisitos
* [IntelliJ IDEA (Opcional)](https://www.jetbrains.com/idea/download/#section=windows)
* [Java JDK 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
* [Spring Boot 3.1.0](https://spring.io/projects/spring-boot)
* [Gradle 7.6.1](https://gradle.org/)
* [Flyway](https://flywaydb.org/)


## Versionamento de libs gradle

Estamos usando um arquivo [.toml para versionar](https://docs.gradle.org/current/userguide/platforms.html#sub::toml-dependencies-format) as libs utilizadas pelo gradle. Para saber mais acesse o arquivo [libs.versions.toml](gradle/libs.versions.toml).

## Executando aplicação

### Configurando ambiente local

Execute o comando abaixo para iniciar os containers com a base de dados e dependências necessárias para rodar a aplicação localmente.

```bash
docker-compose up --build
```

Para executar a aplicação basta executar o comando abaixona raiz do projeto:
```bash
./gradlew run
```

## Endpoints

* `GET /health`
* `GET /products` - Listar produtos
* `Post /products` - Cadastrar produto
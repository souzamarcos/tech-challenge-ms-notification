# Tech Challenge FIAP - Burger
Projeto da pós graduação da FIAP de Arquitetura de Software

## Documentos

* [Event Storm - MIRO](https://miro.com/app/board/uXjVMK9Fze8=/?share_link_id=624130302810)

## Dependências
* [IntelliJ IDEA (Opcional)](https://www.jetbrains.com/idea/download/#section=windows)
* [Java JDK 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
* [Spring Boot 3.1.0](https://spring.io/projects/spring-boot)
* [Gradle 7.6.1](https://gradle.org/)
* [Flyway](https://flywaydb.org/)
* [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)


## Versionamento de libs gradle

Estamos usando um arquivo [.toml para versionar](https://docs.gradle.org/current/userguide/platforms.html#sub::toml-dependencies-format) as libs utilizadas pelo gradle. Para saber mais acesse o arquivo [libs.versions.toml](gradle/libs.versions.toml).

## Executando aplicação

Execute o comando abaixo para iniciar os containers com a base de dados e executar a aplicação localmente.

```bash
docker-compose up --build
```

### Executando somente dependências

Para executar somente dependências externas (Mysql, RabbitMQ, etc) da aplicação para o ambiente de desevolvimento local basta executar o comando abaixo:

```bash
docker-compose -f docker-compose-without-application.yml up --build
```

A aplicação será exposta na porta 8080.

### Cobertura de teste

Foi configurado juntamente com o plugin do jacoco para o gradle a task com nome `codeCoverageReport` que irá disparar a execução de testes da aplicação e gerar o relatório hml. Para isso execute o comando:
```bash
./gradlew codeCoverageReport 
```
Para acessar esse relatório gerado acesse o caminho `build/reports/jacoco/codeCoverageReport/html/index.hml` e abre no navegador.

![img.png](static/jacoco_report_example.png)

### Sonar [WIP]

O sonarqube foi configurado localmente e pode ser acessar pelo endereço [localhost:9000](http://localhost:9000).
No primeiro acesso basta acessar com o login e senha `admin`/`admin` e alterar a senha de sua escolha.
É necessário configurar a aplicação localmente para enviar estatísticas para o sonarqube adicionando o login e senha nas propriedades `systemProp.sonar.login` e `systemProp.sonar.password` no arquivo [gradle.properties](gradle.properties).
Em seguida basta executar o comando do gradle abaixo para enviar as informações:
Obs: o container com o sonarqube deve está rodando. Para isso basta executando os passos do [tópico acima.](#executando-somente-dependências)

```bash
./gradlew sonarqube
```

## Endpoints

Para visualizar os endpoints disponíveis na aplicação basta acessar o swagger em [http://localhost:8080/swagger](http://localhost:8080/swagger)
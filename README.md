# Sistema Matrículas

O **Sistema Matrículas** é uma aplicação web desenvolvida com o objetivo de oferecer uma solução simples, clara e eficiente para o gerenciamento de alunos, disciplinas e matrículas acadêmicas. Ele foi construído utilizando o ecossistema Spring, aliado ao poder do Thymeleaf para renderização de páginas, e utiliza JPA com Hibernate para realizar a persistência dos dados em um banco MySQL. A aplicação também conta com um módulo de segurança robusto, fornecido pelo Spring Security, garantindo que apenas usuários autorizados possam acessar e manipular informações sensíveis.

Toda a dinâmica do sistema gira em torno de três entidades principais: **Alunos**, **Disciplinas** e **Matrículas**. Cada matrícula representa o vínculo entre um aluno e uma disciplina, garantindo sempre a integridade das relações no banco. Além disso, existe também a entidade de **Usuário**, responsável por controlar o acesso e as permissões dentro do sistema, já que todas as operações são protegidas pelo Spring Security.

A aplicação possui dois tipos de perfis de acesso: **ADMIN** e **SECRETARIA**. Embora ambos tenham permissões muito semelhantes, há uma diferença importante: usuários com a role *SECRETARIA* podem visualizar disciplinas, mas não têm autorização para manipulá-las (criar, editar ou excluir). Todas as demais operações são compatíveis entre ambos os perfis. Outro ponto importante é que a criação de novos usuários é uma ação restrita, só podendo ser realizada por usuários já cadastrados. Por isso, o projeto inclui um script que insere automaticamente um usuário padrão no banco com login `admin` e senha `123456`, garantindo que, ao iniciar a aplicação pela primeira vez, exista ao menos um usuário capaz de gerenciar o sistema.

Um aspecto fundamental do Sistema Matrículas é o seu mecanismo de auditoria. Todas as entidades — incluindo alunos, disciplinas, matrículas e usuários — armazenam no banco a informação sobre qual usuário foi responsável pela sua criação. Isso é feito através da integração do Spring Security com o recurso `AuditorAware` do Spring Data, garantindo rastreabilidade de forma automática sem necessidade de código adicional em cada operação.

---

## Execução do Projeto

Para rodar o sistema localmente, é necessário ter instalado o Java (versão 17 ou superior), o Maven e um servidor MySQL. Antes de iniciar a aplicação, o banco de dados precisa existir. Portanto, crie um banco chamado **sistema_matricula** no seu MySQL, com charset `utf8mb4` e collation `utf8mb4_unicode_ci`. Após isso, confira ou ajuste no arquivo `application.properties` suas credenciais de acesso ao banco. O projeto está configurado da seguinte forma:

```
spring.application.name=sistema-matricula

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_matricula?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12052000
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
server.port=8081
```

Caso utilize usuário e senha diferentes no MySQL, basta ajustar os campos `spring.datasource.username` e `spring.datasource.password`. Após isso, basta entrar na pasta raiz do projeto e executar:

```
mvn spring-boot:run
```

ou, se preferir compilar primeiro:

```
mvn clean package
java -jar target/sistema-matricula.jar
```

Quando a aplicação iniciar, ela ficará disponível no endereço:

```
http://localhost:8081/
```

Na primeira execução, o script interno do sistema criará automaticamente o usuário administrador padrão, permitindo que você acesse a aplicação imediatamente com:

* **Usuário:** admin
* **Senha:** 123456

Esse usuário poderá criar outros usuários, cadastrar alunos, disciplinas, matrículas e navegar por todas as telas do sistema. Os templates Thymeleaf organizam o conteúdo de forma clara, tornando a navegação simples para qualquer tipo de usuário.

Durante o uso da aplicação, qualquer entidade criada será registrada com informações de auditoria, indicando o usuário responsável pela criação. Isso garante transparência, segurança e controle sobre as operações executadas dentro do sistema.

---

Se quiser, posso também gerar uma versão deste README em inglês ou criar uma versão mais curta e objetiva para uso corporativo.

# Spring-Boot-em-Gestao-de-Igrejas

O projeto é uma aplicação desenvolvida em Spring Boot para auxiliar na gestão de igrejas e organizações religiosas.

## Funcionalidades

- CRUD de membros da igreja:
  - Criação, listagem, atualização e exclusão de membros.
- Novo Recurso: CRUD de Ministérios
   - Criação, listagem, atualização e exclusão de ministérios.
   - Atribuir Membros a Ministérios:
        - Permitir que membros da igreja sejam associados a um ministério específico. 
- Aniversariantes dos próximos 7 dias:
  - Consulta para obter os membros que estarão celebrando seus aniversários nos próximos 7 dias.
- Novo Recurso: Enviar E-mails aos Membros:
  -  Este recurso, ainda em fase de testes, permite enviar e-mails aos membros da igreja. É possível enviar e-mails individuais para
membros específicos ou e-mails para todos os membros de uma vez. O envio de e-mails é feito através do serviço Mailtrap, que é uma
ferramenta de simulação de e-mails para testes em ambientes de desenvolvimento.
  - Para utilizar o recurso de envio de e-mails, é necessário criar uma conta no Mailtrap e configurar suas credenciais no arquivo application.properties

## Tecnologias Utilizadas

- Spring Boot: Framework Java para criar aplicativos baseados em Java de forma rápida e fácil.
- PostgreSQL: Sistema de gerenciamento de banco de dados relacional.
- Spring Data JPA: Abordagem da Spring para acessar facilmente os dados de um banco de dados com o Spring MVC.
- HATEOAS: Abordagem para criar APIs RESTful que são navegáveis e autoexplicativas.
- Insomnia: Ferramenta para testar requisições de API.

## Execução do Projeto

1. Clone o repositório para sua máquina local.
2. Configure um banco de dados PostgreSQL local e atualize as configurações de conexão no arquivo application.properties.
3. Execute o aplicativo Spring Boot e certifique-se de que ele esteja em execução.
4. Use ferramentas como Insomnia para testar as diferentes funcionalidades disponíveis.

## Contribuindo

Se você deseja contribuir para este projeto, sinta-se à vontade para abrir uma issue ou enviar uma solicitação de pull request.

---

Este projeto ainda está em desenvolvimento e novas funcionalidades serão adicionadas no futuro. Se você tiver alguma sugestão ou encontrar algum problema, não hesite em entrar em contato.
Feito com foco principal em estudos.


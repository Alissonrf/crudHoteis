<<<<<<< HEAD
## 📖 Descrição do projeto

Esse projeto foi desenvolvido para atender a um desafio, no qual era necesario:

1 -> Reaizar um CRUD para um cadastro de hospedes (Nome, Documento e Telefone) 

2 -> Implementar um sistema de gerenciamento para os check-in feito no hotel, o qual deveria abranger toda a lógica de calculo das diárias

3 -> Implementar diferentes tipos de consultas ao hotel (Consultar hospedes presentes no hotel, consultar hospedes que ja passaram pelo hotel, etc...)


## 🚀 Desenvolvimento

Para executar o projeto, 

Necessário criar um database com o nome "senior" no dbeaver, as tabelas serão geradas automaticamente ao rodar a aplicação

Após isso basta compilar o maven dentro da sua IDE e executar o método Main em <b>src/main/java/com/example/senior/Main.java</b>.

A aplicação funciona via REST, necessario chamar os endpoints via POSTMAN


## 💻 Endpoints

Hospedes:

1 -> Para cadastrar um novo hospede - POST - http://localhost:8080/hospede

2 -> Para atualizar um hospede - PUT - http://localhost:8080/hospede

3 -> Para consultar por um hospede - GET - http://localhost:8080/buscar/hospede/{idHospede}

4 -> Para remover o cadastro de um hospede - DELETE - http://localhost:8080/hospede/remover/{idHospede}


Reservas:

1 -> Para realizar uma reserva - POST - http://localhost:8080/reserva/incluir-reserva

2 -> Para consultar o historico de reserva de um hospede - GET - http://localhost:8080/reserva/consultar-historico/{termo} (Termo pode ser o nome do usuario, documento ou telefone, e nao precisam estar completos)

3 -> Para consultar todos os hospedes presentes no hotel - GET - http://localhost:8080/reserva/consultar-hospedes/presentes

4 -> Para consultar todos os hospedes que ja passaram pelo hotel - GET - http://localhost:8080/reserva/consultar-hospedes/passados

#CEP API

Serviço para buscar informações de endereço por CEP
Possui tratamento rudimentar de usuario e endereços.


####Executando
#####local
Basta rodar a classe CepApplication a partir da IDE ou exetutar da linha de comando `mvn spring-boot:run`

#####docker
Com `docker` e `docker-compose` executar na linha de comando:
```
$ mvn clean package
$ docker-compose -f src/main/docker/cep-service.yml up
```

####Consulta
buscar um CEP:
```
$ curl localhost:8080/cep/72859134
```

cadastrar endereço:
```
$ curl localhost:8080/users -XPOST -H "Content-Type: application/json;charset=UTF-8" -d '{"email":"me@domain.com","password":"1234","name":"John Doe"}'
$ curl localhost:8080/users/me@domain.com/addresses -XPOST -H "Content-Type: application/json;charset=UTF-8" -d '{"cep":{"cep":"72859134","uf":"GO","cidade":"Luziânia","logradouro":"Quadra 134","bairro":"Mansões de Recreio Estrela Dalva VIII","tipoLogradouro":"rua"},"numero":1,"complemento":"ap 1","preferencial":true}'
```

####TODO
 [ ] Swager
 [ ] Sync with correios.com.br
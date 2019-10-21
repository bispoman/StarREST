# StarREST

Essa aplicação serve como um CRUD simples usando SpringBoot e MongoDB. 

É uma base de dados simples de planetas do universo de Star Wars, cada um contendo nome, clima, tempo e número de aparições em filmes da franquia. 

## Como rodar?

A Aplicação está configurada para utilizar um banco **MongoDB** simples, na porta padrão, com nome test e sem senha ou usuário, no endereço a seguir: 
``` mongodb://localhost:27017/test ``` 

Se tiver sido feita uma instalação nova do Mongo, basta abrir o executável do mongo, se estiver no windows ou usar o comando ```./bin/mongo``` na pasta onde o mongodb tiver sido instalado.
Mais informações sobre so MongoDB e como inicializa-lo usando a linha de comando, consulte [esse link](http://www.dba86.com/docs/mongo/2.4/tutorial/getting-started-with-the-mongo-shell.html).

Uma vez que o banco esteja rodando, é só rodar o projeto em si.

Para isso, estando na pasta raíz do projeto, use o comando abaixo: 
```
mvn spring-boot:run
```

## O que acontece quando eu rodo a aplicação?

Toda vez que a aplicação é inicializada, o banco de dados é limpo e logo em seguida, 4 planetas são adicionados ao mesmo. Todas as informações foram retiradas [daqui](https://swapi.co/)
Isso garante que sempre tenham informações no banco para testes iniciais. 

## Como testar a aplicação?

Para testes podemos usar [essa coleção](https://www.getpostman.com/collections/2676687f0400b4f4ef46) do [Postman](https://www.getpostman.com/), ou usar os requests abaixo manualmente.

### Retorna todos os planetas do banco
```
GET /api/planetas HTTP/1.1
Host: localhost:8080
```

### Retorna um planeta baseado no nome
```
GET /api/planeta/nome/{nome} HTTP/1.1
Host: localhost:8080
```

### Retorna um planeta baseado no Id
```
GET /api/planeta/id/{id} HTTP/1.1
Host: localhost:8080
```

### Salva um novo planeta no banco
```
POST /api/planeta HTTP/1.1
Host: localhost:8080
```
Esse request precisa de um payload no body, que deve ser formatado conforme abaixo:
```
{
    "nome": "Dagobah",
    "clima": "murky",
    "terreno": "swamp, jungles", 
    "aif": 3
}
```

Quaisquer dúvidas, fique a vontade para entrar em contato comigo ou submeter uma issue no projeto. 

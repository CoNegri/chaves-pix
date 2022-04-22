"# chaves-pix" 

Endpoints:

    - INCLUSÃO
    
        [POST]   /chaves-pix

        Request:
            {
                "nomeCorrentista": "string",
                "numeroAgencia": 0,
                "numeroConta": 0,
                "sobrenomeCorrentista": "string",
                "tipoChave": "celular",
                "tipoConta": "corrente",
                "valorChave": "string"
            }

        Response:

            {
                "id": "string"
            }

    - ALTERAÇÃO
    
        [PUT]    /chaves-pix

        Request:
        {
            "id": "string",
            "nomeCorrentista": "string",
            "numeroAgencia": 0,
            "numeroConta": 0,
            "sobrenomeCorrentista": "string",
            "tipoConta": "corrente"
        }

        Response:

        {
            "dataInclusao": "string",
            "id": "string",
            "nomeCorrentista": "string",
            "numeroAgencia": 0,
            "numeroConta": 0,
            "sobrenomeCorrentista": "string",
            "tipoChave": "celular",
            "tipoConta": "corrente",
            "valorChave": "string"
        }

    - INATIVAÇÃO
    
        [DELETE] /chaves-pix/{id}

        {
            "dataInativacao": "string",
            "dataInclusao": "string",
            "id": "string",
            "nomeCorrentista": "string",
            "numeroAgencia": 0,
            "numeroConta": 0,
            "sobrenomeCorrentista": "string",
            "tipoChave": "celular",
            "tipoConta": "corrente",
            "valorChave": "string"
        }
    
    - BUSCA
    
        [GET]    /chaves-pix/{id}

        Response:
        {
            "dataInativacao": "string",
            "dataInclusao": "string",
            "id": "string",
            "nomeCorrentista": "string",
            "numeroAgencia": 0,
            "numeroConta": 0,
            "sobrenomeCorrentista": "string",
            "tipoChave": "celular",
            "tipoConta": "corrente",
            "valorChave": "string"
        }

        [GET]   /chaves-pix

        Parameters:

        dataInativacao : string
        dataInclusao : string	
        id : string
        nomeCorrentista : string
        numeroAgencia : integer	
        numeroConta : integer	
        tipoChave : string

        Response:
        [
            {
                "dataInativacao": "string",
                "dataInclusao": "string",
                "id": "string",
                "nomeCorrentista": "string",
                "numeroAgencia": 0,
                "numeroConta": 0,
                "sobrenomeCorrentista": "string",
                "tipoChave": "celular",
                "tipoConta": "corrente",
                "valorChave": "string"
            }
        ]


Twelve-Factor App

- I. Base de Código

    Código armazenado no Git

- II. Dependências

    Dependências isoladas no pom.xml

- III. Configurações

    As credencias para conexão com o banco de dados é realizada através da leitura de variáveis de ambiente.

        Ex: application.properties

            spring.datasource.password=${DATASOURCE_PASSWORD}

- IV. Serviços de Apoio

    É possível alterar o banco de dados através de variáveis de ambiente sem necessidade de alteração do código e o Spring JPA permite que ocorra a troca de bancos sem alteração de queries. 

- VI. Processos

    A aplicação possuí diversos endpoints que funcionam de maneira independente sem armazenamento de estado.

- VII. Vínculo de porta

    Os endpoints são expostos na porta 8080




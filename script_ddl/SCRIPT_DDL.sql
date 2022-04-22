

CREATE TABLE tb_chave_pix (
    id                   VARCHAR(36) NOT NULL,
    tipo_chave           VARCHAR(9) NOT NULL,
    valor_chave          VARCHAR(77) NOT NULL,
    nome_exibicao        VARCHAR(30) NOT NULL,
    sobrenome_exibicao   VARCHAR(45),
    tipo_conta           VARCHAR(10) NOT NULL,
    numero_agencia       INTEGER NOT NULL,
    numero_conta         INTEGER NOT NULL,
    data_hora_inclusao   TIMESTAMP WITH TIME ZONE NOT NULL,
    data_hora_inativacao TIMESTAMP WITH TIME ZONE
);

ALTER TABLE tb_chave_pix ADD CONSTRAINT chave_pix_pk PRIMARY KEY ( id );

CREATE TABLE tb_conta (
    tipo_conta     VARCHAR(10) NOT NULL,
    numero_agencia INTEGER NOT NULL,
    numero_conta   INTEGER NOT NULL
);

ALTER TABLE tb_conta
    ADD CONSTRAINT conta_pk PRIMARY KEY ( tipo_conta,
                                          numero_agencia,
                                          numero_conta );

CREATE TABLE tb_pessoa_fisica (
    id        INTEGER NOT NULL,
    cpf       VARCHAR(11) NOT NULL,
    nome      VARCHAR(30) NOT NULL,
    sobrenome VARCHAR(45) NOT NULL
);

ALTER TABLE tb_pessoa_fisica ADD CONSTRAINT pessoa_fisica_pk PRIMARY KEY ( id );

--ALTER TABLE tb_pessoa_fisica ADD CONSTRAINT pessoa_fisica_pkv1 UNIQUE ( cpf );

CREATE TABLE tb_pessoa_juridica (
    id            INTEGER NOT NULL,
    cnpj          VARCHAR(14) NOT NULL,
    razao_social  VARCHAR(30) NOT NULL,
    nome_fantasia VARCHAR(30)
);

ALTER TABLE tb_pessoa_juridica ADD CONSTRAINT pessoa_juridica_pk PRIMARY KEY ( id );

--ALTER TABLE tb_pessoa_juridica ADD CONSTRAINT pessoa_juridica_pkv1 UNIQUE ( cnpj );

CREATE TABLE tb_titular (
    id          SERIAL NOT NULL,
    tipo_pessoa VARCHAR(8) NOT NULL
);

ALTER TABLE tb_titular ADD CONSTRAINT titular_pk PRIMARY KEY ( id );

CREATE TABLE tb_titulares (
    tipo_conta     VARCHAR(10) NOT NULL,
    numero_agencia INTEGER NOT NULL,
    numero_conta   INTEGER NOT NULL,
    id           INTEGER NOT NULL
);

ALTER TABLE tb_titulares
    ADD CONSTRAINT titulares_pk PRIMARY KEY ( tipo_conta,
                                              numero_agencia,
                                              numero_conta,
                                              id );

ALTER TABLE tb_chave_pix
    ADD CONSTRAINT chave_pix_conta_fk FOREIGN KEY ( tipo_conta,
                                                    numero_agencia,
                                                    numero_conta )
        REFERENCES tb_conta ( tipo_conta,
                           numero_agencia,
                           numero_conta );

ALTER TABLE tb_pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_titular_fk FOREIGN KEY ( id )
        REFERENCES tb_titular ( id );

ALTER TABLE tb_pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_titular_fk FOREIGN KEY ( id )
        REFERENCES tb_titular ( id );

ALTER TABLE tb_titulares
    ADD CONSTRAINT titulares_conta_fk FOREIGN KEY ( tipo_conta,
                                                    numero_agencia,
                                                    numero_conta )
        REFERENCES tb_conta ( tipo_conta,
                           numero_agencia,
                           numero_conta );

ALTER TABLE tb_titulares
    ADD CONSTRAINT titulares_titular_fk FOREIGN KEY ( id )
        REFERENCES tb_titular ( id );
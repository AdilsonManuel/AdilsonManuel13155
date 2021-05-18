Entidades
    *usuários
    *corretores
    *clientes
    *proprietários
    *fiadores
    *imóveis
    *empresa

DROP DATABASE IF EXISTS ucandb;

CREATE DATABASE IF NOT EXISTS ucandb;

USE ucandb;

    CREATE TABLE pais (pais_pk SERIAL NOT NULL PRIMARY KEY , 
                  nome CHARACTER VARYING(45) NOT NULL );

    CREATE TABLE provincia (provincia_pk SERIAL NOT NULL PRIMARY KEY, 
                            nome CHARACTER VARYING(45) NOT NULL ,
                            pais_fk INTEGER NOT NULL ,
                            CONSTRAINT  pais_fk FOREIGN KEY(pais_fk) REFERENCES pais(pais_pk) MATCH SIMPLE);

    CREATE TABLE muncipio (muncipio_pk SERIAL NOT NULL PRIMARY KEY,
                           nome CHARACTER VARYING(45) NOT NULL ,
                           provincia_fk INTEGER NOT NULL ,
                           CONSTRAINT provincia_fk FOREIGN KEY(provincia_fk) REFERENCES provincia(provincia_pk) MATCH SIMPLE);

    CREATE TABLE comuna (comuna_pk SERIAL NOT NULL PRIMARY KEY,
                           nome CHARACTER VARYING(45) NOT NULL ,
                           muncipio_fk INTEGER NOT NULL ,
                           CONSTRAINT muncipio_fk FOREIGN KEY(muncipio_fk) REFERENCES muncipio(muncipio_pk) MATCH SIMPLE);

    CREATE TABLE endereco(endereco_pk SERIAL NOT NULL PRIMARY KEY, 
	                        bairro CHARACTER VARYING(45) NOT NULL,
	                        rua CHARACTER VARYING(45) NOT NULL,
                          numero_casa INTEGER NOT NULL ,
	                        comuna_fk INTEGER NOT NULL ,
                        	CONSTRAINT comuna_fk FOREIGN KEY(comuna_fk) REFERENCES comuna(comuna_pk) MATCH SIMPLE);

    CREATE TABLE telefone(telefone_pk SERIAL NOT NULL PRIMARY KEY,
                            operadora CHARACTER VARYING(45) NOT NULL,
                            numero CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE sexo(sexo_pk SERIAL NOT NULL PRIMARY KEY,
                      nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE estado_civil(estado_civil_pk SERIAL NOT NULL PRIMARY KEY,
                      nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE pessoa(pessoa_pk SERIAL NOT NULL PRIMARY KEY,
                        nome CHARACTER VARYING(45) NOT NULL, 
                        data_nascimento  CHARACTER VARYING(45) NOT NULL,
                        sexo_fk INTEGER NOT NULL,
                        estado_civil_fk INTEGER NOT NULL,
                        endereco_fk INTEGER NOT NULL, 
                        telefone_fk INTEGER NOT NULL,
                        email_fk INTEGER NOT NULL,
                        CONSTRAINT estado_civil_fk FOREIGN KEY(estado_civil_fk) REFERENCES estado_civil(estado_civil_pk) MATCH SIMPLE,
                        CONSTRAINT endereco_fk FOREIGN KEY(endereco_fk) REFERENCES endereco(endereco_pk) MATCH SIMPLE,
                        CONSTRAINT telefone_fk FOREIGN KEY(telefone_fk) REFERENCES telefone(telefone_pk) MATCH SIMPLE);
    
    CREATE TABLE tipo_conta (tipo_conta_pk SERIAL NOT NULL PRIMARY KEY, nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE conta (conta SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, nome_usuario CHARACTER VARYING(45) NOT NULL, 
                        senha_usuario CHARACTER VARYING(45) NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk)MATCH SIMPLE);

    CREATE TABLE corretor (corretor_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);

    CREATE TABLE cliente (cliente_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);

    CREATE TABLE proprietario (proprietario_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);

    CREATE TABLE fiador (fiador_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);

    CREATE TABLE tipo_imovel (tipo_imovel_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE imovel (imovel_pk SERIAL NOT NULL PRIMARY KEY, data_mercado CHARACTER VARYING(45) NOT NULL,data_venda CHARACTER VARYING(45) NOT NULL,proprietario_fk INTEGER NOT NULL,
                        nome_imovel CHARACTER VARYING(45) NOT NULL,descricao CHARACTER VARYING, tipo_imovel_fk INTEGER NOT NULL, endereco_fk INTEGER NOT NULL, numero_quartos INTEGER NOT NULL,
                        preco_venda  NUMERIC(10,2), preco_comprador NUMERIC(10,2), preco_acordado NUMERIC(10,2),
                        CONSTRAINT endereco_fk FOREIGN KEY(endereco_fk) REFERENCES endereco(endereco_pk) MATCH SIMPLE,
                        CONSTRAINT proprietario_fk FOREIGN KEY(proprietario_fk) REFERENCES proprietario(proprietario_pk) MATCH SIMPLE,
                        CONSTRAINT tipo_imovel_fk FOREIGN KEY(tipo_imovel_fk) REFERENCES tipo_imovel(tipo_imovel_pk) MATCH SIMPLE);

   CREATE TABLE contrato (contrato_pk SERIAL NOT NULL PRIMARY KEY, imovel_fk INTEGER NOT NULL, cliente_fk INTEGER NOT NULL, fiador_fk INTEGER NOT NULL, data_inicio CHARACTER VARYING(45) NOT NULL, data_fim CHARACTER VARYING(45) NOT NULL,
                         estado_contrato CHARACTER VARYING (45) NOT NULL, valor_pagamento NUMERIC(10,2), total NUMERIC(10,2),
                          CONSTRAINT imovel_fk FOREIGN KEY(imovel_fk) REFERENCES imovel(imovel_pk) MATCH SIMPLE,
                          CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
                          CONSTRAINT fiador_fk FOREIGN KEY(fiador_fk) REFERENCES fiador(fiador_pk) MATCH SIMPLE);
    


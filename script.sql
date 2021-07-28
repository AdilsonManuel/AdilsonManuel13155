Entidades
    *usuários
    *corretores
    *clientes
    *proprietários
    *fiadores
    *imóveis
    *empresa

DROP DATABASE IF EXISTS ucandb;

CREATE DATABASE ucandb;

USE ucandb;

    CREATE TABLE portfolio (portfolio_pk  CHARACTER VARYING (45) NOT NULL PRIMARY KEY,designacao CHARACTER VARYING (45) NOT NULL,
                                 portfolio_fk_pai  CHARACTER VARYING (45),CONSTRAINT portfolio_fk_pai FOREIGN KEY(portfolio_fk_pai) REFERENCES portfolio(portfolio_pk) MATCH SIMPLE);

    CREATE TABLE localizacao (pkSeq SERIAL NOT NULL,localizacao_pk CHARACTER VARYING (45) NOT NULL PRIMARY KEY, designacao CHARACTER VARYING (45) NOT NULL,
                            eh_distrito boolean NOT NULL, localizacao_fk_pai  CHARACTER VARYING (45),ehNivel CHARACTER VARYING (45) NOT NULL,
                            CONSTRAINT localizacao_fk_pai FOREIGN KEY(localizacao_fk_pai) REFERENCES localizacao(localizacao_pk) MATCH SIMPLE);
    
    CREATE TABLE endereco (endereco_pk SERIAL NOT NULL PRIMARY KEY,rua CHARACTER VARYING(45) NOT NULL,numero_casa CHARACTER VARYING(45) NOT NULL ,
	                        localizacao_fk  CHARACTER VARYING(45),
                          CONSTRAINT localizacao_fk FOREIGN KEY(localizacao_fk) REFERENCES localizacao(localizacao_pk) MATCH SIMPLE);


    CREATE TABLE tipo_telefone(tipo_telefone_pk SERIAL NOT NULL PRIMARY KEY, operadora CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE telefone(telefone_pk SERIAL NOT NULL PRIMARY KEY,
                            tipo_telefone_pk  INTEGER NOT NULL,
                            numero CHARACTER VARYING(45),
                            CONSTRAINT tipo_telefone_pk FOREIGN KEY(tipo_telefone_pk) REFERENCES tipo_telefone(tipo_telefone_pk) MATCH SIMPLE);
                            

    CREATE TABLE sexo(sexo_pk SERIAL NOT NULL PRIMARY KEY,
                      nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE estado_civil(estado_civil_pk SERIAL NOT NULL PRIMARY KEY,
                      nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE tipo_email(tipo_email_pk SERIAL NOT NULL PRIMARY KEY, dominio CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE email (
          email_pk SERIAL NOT NULL PRIMARY KEY,
          nome CHARACTER VARYING(45) NOT NULL,
          tipo_email_fk  INTEGER NOT NULL,
          CONSTRAINT tipo_email_fk FOREIGN KEY(tipo_email_fk) REFERENCES tipo_email(tipo_email_pk) MATCH SIMPLE);

    CREATE TABLE pessoa(pessoa_pk SERIAL NOT NULL PRIMARY KEY,
                        nome CHARACTER VARYING(45) NOT NULL, 
                        data_nascimento  CHARACTER VARYING(45) NOT NULL,
                        sexo_fk INTEGER NOT NULL,
                        estado_civil_fk INTEGER NOT NULL,
                        telefone_fk INTEGER NOT NULL,
                        CONSTRAINT estado_civil_fk FOREIGN KEY(estado_civil_fk) REFERENCES estado_civil(estado_civil_pk) MATCH SIMPLE,
                        CONSTRAINT telefone_fk FOREIGN KEY(telefone_fk) REFERENCES telefone(telefone_pk) MATCH SIMPLE,
                        CONSTRAINT sexo_fk FOREIGN KEY(sexo_fk) REFERENCES sexo(sexo_pk) MATCH SIMPLE);
    
    CREATE TABLE tipo_conta (tipo_conta_pk SERIAL NOT NULL PRIMARY KEY, nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE conta (conta_pk SERIAL NOT NULL PRIMARY KEY, tipo_conta_fk INTEGER NOT NULL, nome_usuario CHARACTER VARYING(45) NOT NULL, 
                        senha_usuario CHARACTER VARYING(45) NOT NULL,
                         CONSTRAINT tipo_conta_fk FOREIGN KEY(tipo_conta_fk) REFERENCES tipo_conta(tipo_conta_pk)MATCH SIMPLE);

    CREATE TABLE tipo_cliente (tipo_cliente_pk SERIAL NOT NULL PRIMARY KEY, nome CHARACTER VARYING(45) NOT NULL);
    
    CREATE TABLE cliente (cliente_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL,tipo_cliente_fk INTEGER NOT NULL,
                            CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE,
                            CONSTRAINT tipo_cliente_fk FOREIGN KEY(tipo_cliente_fk) REFERENCES tipo_cliente(tipo_cliente_Pk) MATCH SIMPLE);

                            
    CREATE TABLE pais (pais_pk SERIAL NOT NULL PRIMARY KEY , 
                  nome CHARACTER VARYING(45) NOT NULL );

     CREATE TABLE categoria (categoria_pk SERIAL NOT NULL PRIMARY KEY , 
                  nome CHARACTER VARYING(45) NOT NULL );


    CREATE TABLE provincia (provincia_pk SERIAL NOT NULL PRIMARY KEY, 
                            nome CHARACTER VARYING(45) NOT NULL ,
                            pais_fk INT NOT NULL ,
                            CONSTRAINT  pais_fk FOREIGN KEY(pais_fk) REFERENCES pais(pais_pk) MATCH SIMPLE);

    CREATE TABLE muncipio (muncipio_pk SERIAL NOT NULL PRIMARY KEY,
                           nome CHARACTER VARYING(45) NOT NULL ,
                           provincia_fk INT NOT NULL ,
                           CONSTRAINT provincia_fk FOREIGN KEY(provincia_fk) REFERENCES provincia(provincia_pk) MATCH SIMPLE);

    CREATE TABLE comuna (comuna_pk SERIAL NOT NULL PRIMARY KEY,
                           nome CHARACTER VARYING(45) NOT NULL ,
                           muncipio_fk INT NOT NULL ,
                           CONSTRAINT muncipio_fk FOREIGN KEY(muncipio_fk) REFERENCES muncipio(muncipio_pk) MATCH SIMPLE);

    CREATE TABLE tipo_funcionario (tipo_funcionario_pk SERIAL NOT NULL PRIMARY KEY, nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE funcionario (funcionario_pk SERIAL NOT NULL PRIMARY KEY,tipo_funcionario_fk INTEGER NOT NULL, pessoa_fk INTEGER NOT NULL,
                              CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE,
                              CONSTRAINT tipo_funcionario_fk FOREIGN KEY(tipo_funcionario_fk) REFERENCES tipo_funcionario(tipo_funcionario_pk) MATCH SIMPLE);

    CREATE TABLE fornecedor (fornecedor_pk SERIAL NOT NULL PRIMARY KEY,nome CHARACTER VARYING(45) NOT NULL);
                             
    CREATE TABLE produto (produto_pk integer NOT NULL DEFAULT nextval('produto_produto_pk_seq'::regclass),
                                 designacao character varying(45) COLLATE pg_catalog."default" NOT NULL,
                                 imagem character varying(45) COLLATE pg_catalog."default",
                                 portfolio_fk character varying(45) COLLATE pg_catalog."default" NOT NULL,
                                 preco double precision NOT NULL,
                                 fornecedor_fk integer NOT NULL,
                                 quantidade integer NOT NULL,
                                 data_registro character varying(45) COLLATE pg_catalog."default" NOT NULL,
                                 portfolio_fk  CHARACTER VARYING (45),CONSTRAINT portfolio_fk FOREIGN KEY(portfolio_fk) REFERENCES portfolio(portfolio_pk) MATCH SIMPLE,
                                 fornecedor_fk  CHARACTER VARYING (45),CONSTRAINT fornecedor_fk FOREIGN KEY(fornecedor_fk) REFERENCES fornecedor(fornecedor_pk) MATCH SIMPLE);

    CREATE TABLE stock (stock_pk  SERIAL NOT NULL PRIMARY KEY,designacao CHARACTER VARYING (45) NOT NULL,valor DOUBLE PRECISION NOT NULL,limite_maximo integer NOT NULL,
                                   produto_fk  INTEGER NOT NULL,CONSTRAINT produto_fk FOREIGN KEY(produto_fk) REFERENCES produto(produto_pk) MATCH SIMPLE);

    CREATE TABLE forma_pagamento (forma_pagamento_pk SERIAL NOT NULL PRIMARY KEY, designacao CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE venda (venda_pk  SERIAL NOT NULL PRIMARY KEY,data_venda DATE NOT NULL,total NUMERIC(10,2) NOT NULL,
                                   cliente_fk INTEGER NOT NULL, funcionario_fk INTEGER not NULL, forma_pagamento_fk INTEGER NOT NULL,
                                   CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
                                   CONSTRAINT funcionario_fk FOREIGN KEY(funcionario_fk) REFERENCES funcionario(funcionario_pk) MATCH SIMPLE,
                                   CONSTRAINT forma_pagamento_fk FOREIGN KEY(forma_pagamento_fk) REFERENCES forma_pagamento(forma_pagamento_pk) MATCH SIMPLE);

    CREATE TABLE item_venda (item_venda_pk  SERIAL NOT NULL PRIMARY KEY,quantidade INTEGER NOT NULL,total NUMERIC(10,2) NOT NULL,
                                   venda_fk INTEGER NOT NULL,  produto_fk  INTEGER NOT NULL,
                                   CONSTRAINT venda_fk FOREIGN KEY(venda_fk) REFERENCES venda(venda_pk) MATCH SIMPLE,
                                   CONSTRAINT produto_fk FOREIGN KEY(produto_fk) REFERENCES produto(produto_pk) MATCH SIMPLE);
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    //CREATE TABLE proprietario/corretor (corretor_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);
   
   // CREATE TABLE fiador (fiador_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE);

    CREATE TABLE tipo_imovel (tipo_imovel_pk SERIAL NOT NULL PRIMARY KEY, pessoa_fk INTEGER NOT NULL, nome CHARACTER VARYING(45) NOT NULL);

    CREATE TABLE imovel (imovel_pk SERIAL NOT NULL PRIMARY KEY, data_mercado CHARACTER VARYING(45) NOT NULL,data_venda CHARACTER VARYING(45) NOT NULL,cliente_fk INTEGER NOT NULL,
                        nome_imovel CHARACTER VARYING(45) NOT NULL,descricao CHARACTER VARYING, tipo_imovel_fk INTEGER NOT NULL, endereco_fk INTEGER NOT NULL, numero_quartos INTEGER NOT NULL,
                        preco_venda  NUMERIC(10,2), preco_comprador NUMERIC(10,2), preco_acordado NUMERIC(10,2),
                        CONSTRAINT endereco_fk FOREIGN KEY(endereco_fk) REFERENCES endereco(endereco_pk) MATCH SIMPLE,
                        CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
                        CONSTRAINT tipo_imovel_fk FOREIGN KEY(tipo_imovel_fk) REFERENCES tipo_imovel(tipo_imovel_pk) MATCH SIMPLE);

   CREATE TABLE contrato (contrato_pk SERIAL NOT NULL PRIMARY KEY, imovel_fk INTEGER NOT NULL, cliente_fk INTEGER NOT NULL, funcionario_fk INTEGER NOT NULL, data_inicio CHARACTER VARYING(45) NOT NULL, data_fim CHARACTER VARYING(45) NOT NULL,
                         estado_contrato CHARACTER VARYING (45) NOT NULL, valor_pagamento NUMERIC(10,2), total NUMERIC(10,2),
                          CONSTRAINT imovel_fk FOREIGN KEY(imovel_fk) REFERENCES imovel(imovel_pk) MATCH SIMPLE,
                          CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
                          CONSTRAINT funcionario_fk FOREIGN KEY(funcionario_fk) REFERENCES funcionario(funcionario_pk) MATCH SIMPLE);
    
  
INSERT INTO pais (nome) VALUES('Angola');

INSERT INTO categoria (nome) VALUES('Apartamento');
INSERT INTO categoria (nome) VALUES('Sala/Escritorio');
INSERT INTO categoria (nome) VALUES('Rural');
INSERT INTO categoria (nome) VALUES('Predio comerical');
INSERT INTO categoria (nome) VALUES('casa');
INSERT INTO categoria (nome) VALUES('Terreno');

INSERT INTO provincia(nome,pais_fk) VALUES('Bengo',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Benguela',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Bié',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Cabinda',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Cuando Cubango',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Cunene',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Huambo',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Huíla',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Kwanza Norte',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Kwanza Sul',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Luanda',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Lunda Norte',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Lunda Sul',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Malanje',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Moxico',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Namibe',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Uíge',1);
INSERT INTO provincia(nome,pais_fk) VALUES('Zaire',1);

SELECT * FROM "provincia" LIMIT 18;

/*********************************Insert das provincias*********************************/
/*Municipios da Provincia do Bengo*/
insert into municipio(nome, provincia_fk) values('Ambriz', 1);
insert into municipio(nome, provincia_fk) values('Bula Atumba', 1);
insert into municipio(nome, provincia_fk) values('Dande', 1);
insert into municipio(nome, provincia_fk) values('Dembos', 1);
insert into municipio(nome, provincia_fk) values('Nambuangongo', 1);
insert into municipio(nome, provincia_fk) values('Pango Aluquém', 1);

/*Municipios da Provincia de Benguela*/
insert into municipio(nome, provincia_fk) values('Baía Farta', 2);
insert into municipio(nome, provincia_fk) values('Balombo', 2);
insert into municipio(nome, provincia_fk) values('Benguela', 2);
insert into municipio(nome, provincia_fk) values('Bocoio', 2);
insert into municipio(nome, provincia_fk) values('Caimbambo', 2);
insert into municipio(nome, provincia_fk) values('Catumbela', 2);
insert into municipio(nome, provincia_fk) values('Chongorói', 2);
insert into municipio(nome, provincia_fk) values('Cubal', 2);
insert into municipio(nome, provincia_fk) values('Ganda', 2);
insert into municipio(nome, provincia_fk) values('Lobito', 2);

/*Municipios da Provincia do Bíe*/
insert into municipio(nome, provincia_fk) values('Andulo', 3);
insert into municipio(nome, provincia_fk) values('Camacupa', 3);
insert into municipio(nome, provincia_fk) values('Catabola', 3);
insert into municipio(nome, provincia_fk) values('Chinguar', 3);
insert into municipio(nome, provincia_fk) values('Chitembo', 3);
insert into municipio(nome, provincia_fk) values('Cuemba', 3);
insert into municipio(nome, provincia_fk) values('Cunhinga', 3);
insert into municipio(nome, provincia_fk) values('Kuito', 3);
insert into municipio(nome, provincia_fk) values('Nharea', 3);

/*Municipios da Provincia de Cabinda*/
insert into municipio(nome, provincia_fk) values('Belize', 4);
insert into municipio(nome, provincia_fk) values('Buco-Zau', 4);
insert into municipio(nome, provincia_fk) values('Cabinda', 4);
insert into municipio(nome, provincia_fk) values('Cacongo', 4);

/*Municipios da Provincia de Cuando-Cubango*/
insert into municipio(nome, provincia_fk) values('Calai', 5);
insert into municipio(nome, provincia_fk) values('Cuangar', 5);
insert into municipio(nome, provincia_fk) values('Cuito Cuanavale', 5);
insert into municipio(nome, provincia_fk) values('Dirico', 5);
insert into municipio(nome, provincia_fk) values('Longa', 5);
insert into municipio(nome, provincia_fk) values('Mavinga', 5);
insert into municipio(nome, provincia_fk) values('Menongue', 5);
insert into municipio(nome, provincia_fk) values('Nancova', 5);
insert into municipio(nome, provincia_fk) values('Rivungo', 5);

/*Municipios da Provincia de Cunene*/
insert into municipio(nome, provincia_fk) values('Cahama', 6);
insert into municipio(nome, provincia_fk) values('Cuanhama', 6);
insert into municipio(nome, provincia_fk) values('Curoca', 6);
insert into municipio(nome, provincia_fk) values('Cuvelai', 6);
insert into municipio(nome, provincia_fk) values('Namacunde', 6);
insert into municipio(nome, provincia_fk) values('Ombadja', 6);

/*Municipios da Provincia de Huambo*/
insert into municipio(nome, provincia_fk) values('Bailundo', 7);
insert into municipio(nome, provincia_fk) values('Catchiungo', 7);
insert into municipio(nome, provincia_fk) values('Caála', 7);
insert into municipio(nome, provincia_fk) values('Ekunha', 7);
insert into municipio(nome, provincia_fk) values('Huambo', 7);
insert into municipio(nome, provincia_fk) values('Londuimbale', 7);
insert into municipio(nome, provincia_fk) values('Longonjo', 7);
insert into municipio(nome, provincia_fk) values('Mungo', 7);
insert into municipio(nome, provincia_fk) values('Tchicala-Tcholoanga', 7);
insert into municipio(nome, provincia_fk) values('Tchindjenje', 7);
insert into municipio(nome, provincia_fk) values('Ucuma', 7);

/*Municipios da Provincia de Huíla*/
insert into municipio(nome, provincia_fk) values('Caconda', 8);
insert into municipio(nome, provincia_fk) values('Cacula', 8);
insert into municipio(nome, provincia_fk) values('Caluquembe', 8);
insert into municipio(nome, provincia_fk) values('Chiange', 8);
insert into municipio(nome, provincia_fk) values('Chiba', 8);
insert into municipio(nome, provincia_fk) values('Chicomba', 8);
insert into municipio(nome, provincia_fk) values('Chipindo', 8);
insert into municipio(nome, provincia_fk) values('Cuvango', 8);
insert into municipio(nome, provincia_fk) values('Humpata', 8);
insert into municipio(nome, provincia_fk) values('Jamba', 8);
insert into municipio(nome, provincia_fk) values('Lubango', 8);
insert into municipio(nome, provincia_fk) values('Matala', 8);
insert into municipio(nome, provincia_fk) values('Quilengues', 8);
insert into municipio(nome, provincia_fk) values('Quiupngo', 8);

/*Municipios da Provincia de Kwanza Norte*/
insert into municipio(nome, provincia_fk) values('Ambaca', 9);
insert into municipio(nome, provincia_fk) values('Banga', 9);
insert into municipio(nome, provincia_fk) values('Bolongongo', 9);
insert into municipio(nome, provincia_fk) values('Cambambe', 9);
insert into municipio(nome, provincia_fk) values('Cazenga', 9);
insert into municipio(nome, provincia_fk) values('Golungo Alto', 9);
insert into municipio(nome, provincia_fk) values('Gonguembo', 9);
insert into municipio(nome, provincia_fk) values('Lucala', 9);
insert into municipio(nome, provincia_fk) values('Quiculungo', 9);
insert into municipio(nome, provincia_fk) values('Samba Caju', 9);

/*Municipios da Provincia de Kwanza Sul*/
insert into municipio(nome, provincia_fk) values('Amboim', 10);
insert into municipio(nome, provincia_fk) values('Cassongue', 10);
insert into municipio(nome, provincia_fk) values('Cela', 10);
insert into municipio(nome, provincia_fk) values('Conda', 10);
insert into municipio(nome, provincia_fk) values('Ebo', 10);
insert into municipio(nome, provincia_fk) values('Libolo', 10);
insert into municipio(nome, provincia_fk) values('Mussende', 10);
insert into municipio(nome, provincia_fk) values('Porto Amboim', 10);
insert into municipio(nome, provincia_fk) values('Quibala', 10);
insert into municipio(nome, provincia_fk) values('Quilenda', 10);
insert into municipio(nome, provincia_fk) values('Seles', 10);
insert into municipio(nome, provincia_fk) values('Sumbe', 10);

/*Municipios da Provincia de Luanda*/
insert into municipio(nome, provincia_fk) values('Belas', 11);
insert into municipio(nome, provincia_fk) values('Cacuaco', 11);
insert into municipio(nome, provincia_fk) values('Cazenga', 11);
insert into municipio(nome, provincia_fk) values('Icolo e Bengo', 11);
insert into municipio(nome, provincia_fk) values('Luanda', 11);
insert into municipio(nome, provincia_fk) values('Quiçama', 11);
insert into municipio(nome, provincia_fk) values('Viana', 11);

/*Municipios da Provincia de Lunda-Norte*/
insert into municipio(nome, provincia_fk) values('Cambulo', 12);
insert into municipio(nome, provincia_fk) values('Capenda-Camulemba', 13);
insert into municipio(nome, provincia_fk) values('Caungula', 12);
insert into municipio(nome, provincia_fk) values('Chitato', 12);
insert into municipio(nome, provincia_fk) values('Cuango', 12);
insert into municipio(nome, provincia_fk) values('Cuílo', 12);
insert into municipio(nome, provincia_fk) values('Lubalo', 12);
insert into municipio(nome, provincia_fk) values('Lucapa', 12);
insert into municipio(nome, provincia_fk) values('Xá-Muteba', 12);

/*Municipios da Provincia de Lunda-Sul*/
insert into municipio(nome, provincia_fk) values('Cacolo', 13);
insert into municipio(nome, provincia_fk) values('Dala', 13);
insert into municipio(nome, provincia_fk) values('Muconda', 13);
insert into municipio(nome, provincia_fk) values('Saurimo', 13);

/*Municipios da Provincia de Malange*/
insert into municipio(nome, provincia_fk) values('Cacuso', 14);
insert into municipio(nome, provincia_fk) values('Calandula', 14);
insert into municipio(nome, provincia_fk) values('Cambundi-Catembo', 14);
insert into municipio(nome, provincia_fk) values('Cangandala', 14);
insert into municipio(nome, provincia_fk) values('Caombo', 14);
insert into municipio(nome, provincia_fk) values('Cuaba Nzogo', 14);
insert into municipio(nome, provincia_fk) values('Cuada-Dia-Baze', 14);
insert into municipio(nome, provincia_fk) values('Luquembo', 14);
insert into municipio(nome, provincia_fk) values('Malanje', 14);
insert into municipio(nome, provincia_fk) values('Marimba', 14);
insert into municipio(nome, provincia_fk) values('Massango', 14);
insert into municipio(nome, provincia_fk) values('Mucari', 14);
insert into municipio(nome, provincia_fk) values('Quela', 14);
insert into municipio(nome, provincia_fk) values('Quirima', 14);

/*Municipios da Provincia de Moxico*/
insert into municipio(nome, provincia_fk) values('Alto Zambeze', 15);
insert into municipio(nome, provincia_fk) values('Bundas', 15);
insert into municipio(nome, provincia_fk) values('Luau', 15);
insert into municipio(nome, provincia_fk) values('Luacano', 15);
insert into municipio(nome, provincia_fk) values('Luchazes', 15);
insert into municipio(nome, provincia_fk) values('Luena', 15);
insert into municipio(nome, provincia_fk) values('Lumeje', 15);
insert into municipio(nome, provincia_fk) values('Moxico', 15);

/*Municipios da Provincia de Namibe*/
insert into municipio(nome, provincia_fk) values('Bibala', 16);
insert into municipio(nome, provincia_fk) values('Camucuio', 16);
insert into municipio(nome, provincia_fk) values('Namibe', 16);
insert into municipio(nome, provincia_fk) values('Tômbua', 16);
insert into municipio(nome, provincia_fk) values('Virei', 16);

/*Municipios da Provincia de Uíge*/
insert into municipio(nome, provincia_fk) values('Alto Caule', 17);
insert into municipio(nome, provincia_fk) values('Ambuíla', 17);
insert into municipio(nome, provincia_fk) values('Bembe', 17);
insert into municipio(nome, provincia_fk) values('Buengas', 17);
insert into municipio(nome, provincia_fk) values('Bungo', 17);

insert into municipio(nome, provincia_fk) values('Damba', 17);
insert into municipio(nome, provincia_fk) values('Macocola', 17);
insert into municipio(nome, provincia_fk) values('Milunga', 17);
insert into municipio(nome, provincia_fk) values('Mucaba', 17);
insert into municipio(nome, provincia_fk) values('Negage', 17);
insert into municipio(nome, provincia_fk) values('Puri', 17);
insert into municipio(nome, provincia_fk) values('Quimbele', 17);
insert into municipio(nome, provincia_fk) values('Quitexe', 17);
insert into municipio(nome, provincia_fk) values('Sanza Pombo', 17);
insert into municipio(nome, provincia_fk) values('Songo', 17);
insert into municipio(nome, provincia_fk) values('Uíge', 17);
insert into municipio(nome, provincia_fk) values('Zombo', 17);

/*Municipios da Provincia de Zaire*/
insert into municipio(nome, provincia_fk) values('Cuimba', 18);
insert into municipio(nome, provincia_fk) values('MBanza Kongo', 18);
insert into municipio(nome, provincia_fk) values('Noqui', 18);
insert into municipio(nome, provincia_fk) values('Nzeto', 18);
insert into municipio(nome, provincia_fk) values('Soyo', 18);
insert into municipio(nome, provincia_fk) values('Tomboco', 18);

insert into municipio(nome, provincia_fk) values('MBanza Kongo', 18);
insert into municipio(nome, provincia_fk) values('Noqui', 18);
insert into municipio(nome, provincia_fk) values('Nzeto', 18);
insert into municipio(nome, provincia_fk) values('Soyo', 18);
insert into municipio(nome, provincia_fk) values('Tomboco', 18);

/*insertir comuna*/

/*provincia Bengo*/
/*Comunas do Municipio do Ambriz*/
insert into comuna(nome, municipio_fk) values('Ambriz', 1);
insert into comuna(nome, municipio_fk) values('Bela Vista', 1);
insert into comuna(nome, municipio_fk) values('Tabi', 1);

/*Comunas do Municipio do Bula Atumba*/
insert into comuna(nome, municipio_fk) values('Bula-Atumba', 2);
insert into comuna(nome, municipio_fk) values('Quiage', 2);

/*Comunas do Municipio do Dande*/
insert into comuna(nome, municipio_fk) values('Barra do Dande', 3);
insert into comuna(nome, municipio_fk) values('Mabubas', 3);
insert into comuna(nome, municipio_fk) values('Quicabo', 3);
insert into comuna(nome, municipio_fk) values('Úcua', 3);

/*Comunas do Municipio do Dembos*/
insert into comuna(nome, municipio_fk) values('Paredes', 4);
insert into comuna(nome, municipio_fk) values('Piri', 4);
insert into comuna(nome, municipio_fk) values('Quibaxe', 4);
insert into comuna(nome, municipio_fk) values('São José das Matas (ex-Quoxe)', 4);

/*Comunas do Municipio do Nambuangongo*/
insert into comuna(nome, municipio_fk) values('Cage', 5);
insert into comuna(nome, municipio_fk) values('Canacassala', 5);
insert into comuna(nome, municipio_fk) values('Gombe', 5);
insert into comuna(nome, municipio_fk) values('Muxaluando', 5);
insert into comuna(nome, municipio_fk) values('Quicunzo', 5);
insert into comuna(nome, municipio_fk) values('Quixico', 5);
insert into comuna(nome, municipio_fk) values('Zala', 5);

/*Comunas do Municipio do Pango Aluquém*/
insert into comuna(nome, municipio_fk) values('Cazuangongo', 6);
insert into comuna(nome, municipio_fk) values('Pango Aluquém', 6);

/*provincia Benguela*/
/*Comunas do Municipio da Baía Farta */
insert into comuna(nome, municipio_fk) values('Baía Farta', 7);
insert into comuna(nome, municipio_fk) values('Dombe Grande', 7);
insert into comuna(nome, municipio_fk) values('Equimina', 7);
insert into comuna(nome, municipio_fk) values('Kalohanga', 7);

/*Comunas do Municipio da Balombo*/
insert into comuna(nome, municipio_fk) values('Balombo', 8);
insert into comuna(nome, municipio_fk) values('Chindumbo', 8);
insert into comuna(nome, municipio_fk) values('Chingongo', 8);
insert into comuna(nome, municipio_fk) values('Maka Mombolo', 8);
insert into comuna(nome, municipio_fk) values('Chingongo', 8);

/*Comunas do Municipio da Benguela */

/*Comunas do Municipio da Bocoio */
insert into comuna(nome, municipio_fk) values('Bocoio', 10);
insert into comuna(nome, municipio_fk) values('Monte Belo (Utue Wombua)', 10);
insert into comuna(nome, municipio_fk) values('Chila', 10);
insert into comuna(nome, municipio_fk) values('Cubal do Lumbo', 10);
insert into comuna(nome, municipio_fk) values('Passe', 10);
insert into comuna(nome, municipio_fk) values('Passe', 10);

/*Comunas do Municipio da Caimbambo */
insert into comuna(nome, municipio_fk) values('Caimbambo', 11);
insert into comuna(nome, municipio_fk) values('Canhamela', 11);
insert into comuna(nome, municipio_fk) values('Catengue', 11);
insert into comuna(nome, municipio_fk) values('Wiyangombe', 11);

/*Comunas do Municipio da Catumbela */
insert into comuna(nome, municipio_fk) values('Biópio', 12);
insert into comuna(nome, municipio_fk) values('Catumbela', 12);
insert into comuna(nome, municipio_fk) values('Gama', 12);
insert into comuna(nome, municipio_fk) values('Egito', 12);

/*Comunas do Municipio da Chorongói */
insert into comuna(nome, municipio_fk) values('Chorongói', 13);
insert into comuna(nome, municipio_fk) values('Bolonguera', 13);
insert into comuna(nome, municipio_fk) values('Camuíne', 13);

/*Comunas do Municipio da Cubal */
insert into comuna(nome, municipio_fk) values('Cubal', 14);
insert into comuna(nome, municipio_fk) values('Capupa', 14);
insert into comuna(nome, municipio_fk) values('Imbala', 14);
insert into comuna(nome, municipio_fk) values('Tumbulo', 14);

/*Comunas do Municipio da Ganda */
insert into comuna(nome, municipio_fk) values('Ganda', 15);
insert into comuna(nome, municipio_fk) values('Babaera', 15);
insert into comuna(nome, municipio_fk) values('Casseque', 15);
insert into comuna(nome, municipio_fk) values('Chikuma', 15);
insert into comuna(nome, municipio_fk) values('Ebanga', 15);

/*Comunas do Municipio da Lobito */
insert into comuna(nome, municipio_fk) values('Lobito', 16);
insert into comuna(nome, municipio_fk) values('Culango', 16);
insert into comuna(nome, municipio_fk) values('Canjala', 16);

/*provincia Bié*/
/*Comunas do Municipio do Andulo*/
insert into comuna(nome, municipio_fk) values('Andulo', 17);
insert into comuna(nome, municipio_fk) values('Calucinga', 17);
insert into comuna(nome, municipio_fk) values('Cassumbe', 17);
insert into comuna(nome, municipio_fk) values('Chivaúlo', 17);

/*Comunas do Municipio do Camacupa*/
insert into comuna(nome, municipio_fk) values('Camacupa', 18);
insert into comuna(nome, municipio_fk) values('Cuanza', 18);
insert into comuna(nome, municipio_fk) values('Ringoma', 18);
insert into comuna(nome, municipio_fk) values('Santo António de Muinha', 18);
insert into comuna(nome, municipio_fk) values('Umpulo', 18);

/*Comunas do Municipio do Catabola*/
insert into comuna(nome, municipio_fk) values('Catabola', 19);
insert into comuna(nome, municipio_fk) values('Caivera', 19);
insert into comuna(nome, municipio_fk) values('Chipeta', 19);
insert into comuna(nome, municipio_fk) values('Chiuca', 19);
insert into comuna(nome, municipio_fk) values('Sande', 19);

/*Comunas do Municipio do Chinguar*/
insert into comuna(nome, municipio_fk) values('Chinguar', 20);
insert into comuna(nome, municipio_fk) values('Cutato', 20);
insert into comuna(nome, municipio_fk) values('Kangote', 20);

/*Comunas do Municipio do Chitembo*/
insert into comuna(nome, municipio_fk) values('Chitembo', 21);
insert into comuna(nome, municipio_fk) values('Cachingues', 21);
insert into comuna(nome, municipio_fk) values('Malengue', 21);
insert into comuna(nome, municipio_fk) values('Mutambo', 21);
insert into comuna(nome, municipio_fk) values('Mumbué', 21);
insert into comuna(nome, municipio_fk) values('Soma Cuanza', 21);

/*Comunas do Municipio do Cuemba*/
insert into comuna(nome, municipio_fk) values('Cuemba', 22);
insert into comuna(nome, municipio_fk) values('Luando', 22);
insert into comuna(nome, municipio_fk) values('Munhango', 22);
insert into comuna(nome, municipio_fk) values('Sachinemuna', 22);

/*Comunas do Municipio do Cunhinga*/
insert into comuna(nome, municipio_fk) values('Cunhinga', 22);
insert into comuna(nome, municipio_fk) values('Belo Horizonte', 22);

/*Comunas do Municipio do Kuito*/
insert into comuna(nome, municipio_fk) values('Kuito', 23);
insert into comuna(nome, municipio_fk) values('Chicala', 23);
insert into comuna(nome, municipio_fk) values('Kambândua', 23);
insert into comuna(nome, municipio_fk) values('Kunje', 23);
insert into comuna(nome, municipio_fk) values('Trumba', 23);

/*Comunas do Municipio do Nharea*/
insert into comuna(nome, municipio_fk) values('Nharea', 24);
insert into comuna(nome, municipio_fk) values('Gamba', 24);
insert into comuna(nome, municipio_fk) values('Dando', 24);
insert into comuna(nome, municipio_fk) values('Lúbia', 24);
insert into comuna(nome, municipio_fk) values('Caieie', 24);

/*provincia Cabinda*/
/*Comunas do Municipio do Belize*/
insert into comuna(nome, municipio_fk) values('Belize', 25);
insert into comuna(nome, municipio_fk) values('Luali', 25);
insert into comuna(nome, municipio_fk) values('Miconje', 25);

/*Comunas do Municipio do Cabinda*/
insert into comuna(nome, municipio_fk) values('Cabinda', 26);
insert into comuna(nome, municipio_fk) values('Malembo', 26);
insert into comuna(nome, municipio_fk) values('Tanto-Zinze', 26);

/*Comunas do Municipio do Buco-Zau*/
insert into comuna(nome, municipio_fk) values('Buco-Zau', 27);
insert into comuna(nome, municipio_fk) values('Inhuca', 27);
insert into comuna(nome, municipio_fk) values('Necuto', 27);

/*Comunas do Municipio do Cacong*/
insert into comuna(nome, municipio_fk) values('Lândana', 28);
insert into comuna(nome, municipio_fk) values('Dinge', 28);
insert into comuna(nome, municipio_fk) values('Massabi', 28);

/*provincia Cuando-Cubango*/
/*Comunas do Municipio do Calai*/
insert into comuna(nome, municipio_fk) values('Calai', 29);
insert into comuna(nome, municipio_fk) values('Maúe', 29);
insert into comuna(nome, municipio_fk) values('Mavengue', 29);

/*Comunas do Municipio do Cuangar*/
insert into comuna(nome, municipio_fk) values('Cuangar', 30);
insert into comuna(nome, municipio_fk) values('Bondo', 30);
insert into comuna(nome, municipio_fk) values('Savate', 30);

/*Comunas do Municipio do Cuchi*/
insert into comuna(nome, municipio_fk) values('Cuchi', 31);
insert into comuna(nome, municipio_fk) values('Cutato', 31);
insert into comuna(nome, municipio_fk) values('Chinguanja', 31);
insert into comuna(nome, municipio_fk) values('Muila', 31);

/*Comunas do Municipio do Cuito Cuanavale*/
insert into comuna(nome, municipio_fk) values('Cuito Cuanavale', 32);
insert into comuna(nome, municipio_fk) values('Baixo Longa', 32);
insert into comuna(nome, municipio_fk) values('Longa', 32);
insert into comuna(nome, municipio_fk) values('Lupire', 32);

/*Comunas do Municipio do  Dirico*/
insert into comuna(nome, municipio_fk) values('Dirico', 33);
insert into comuna(nome, municipio_fk) values('Mucusso', 33);
insert into comuna(nome, municipio_fk) values('Xamavera', 33);

/*Comunas do Municipio do  Mavinga*/
insert into comuna(nome, municipio_fk) values('Mavinga', 34);
insert into comuna(nome, municipio_fk) values('Cunjamba/Dime', 34);
insert into comuna(nome, municipio_fk) values('Cutuile', 34);
insert into comuna(nome, municipio_fk) values('Luengue', 34);

/*Comunas do Municipio do  Menongue*/
insert into comuna(nome, municipio_fk) values('Menongue', 35);
insert into comuna(nome, municipio_fk) values('Caiundo', 35);
insert into comuna(nome, municipio_fk) values('Cueio', 35);

/*Comunas do Municipio do  Nancova*/
insert into comuna(nome, municipio_fk) values('Nancova', 36);
insert into comuna(nome, municipio_fk) values('Rito', 36);

/*Comunas do Municipio do  Rivungo*/
insert into comuna(nome, municipio_fk) values('Rivungo', 37);
insert into comuna(nome, municipio_fk) values('Chipundo/Neriquinha', 37);
insert into comuna(nome, municipio_fk) values('Luiana', 37);

/*provincia Cunene*/
/*Comunas do Municipio do Cahama*/
insert into comuna(nome, municipio_fk) values('Cahama', 38);
insert into comuna(nome, municipio_fk) values('Otchindjau', 38);

/*Comunas do Municipio do Cuanhama*/
insert into comuna(nome, municipio_fk) values('Ondjiva', 39);
insert into comuna(nome, municipio_fk) values('Chimpolo', 39);
insert into comuna(nome, municipio_fk) values('Evale', 39);
insert into comuna(nome, municipio_fk) values('Môngua', 39);
insert into comuna(nome, municipio_fk) values('Nehone', 39);

/*Comunas do Municipio do Curoca*/
insert into comuna(nome, municipio_fk) values('Oncócua', 40);
insert into comuna(nome, municipio_fk) values('Chitado', 40);

/*Comunas do Municipio do Cuvelai*/
insert into comuna(nome, municipio_fk) values('Mukolongondjo', 41);
insert into comuna(nome, municipio_fk) values('Kalonga', 41);
insert into comuna(nome, municipio_fk) values('Kubati', 41);
insert into comuna(nome, municipio_fk) values('Mupa', 41);

/*Comunas do Municipio do Namacunde*/
insert into comuna(nome, municipio_fk) values('Namacunde', 42);
insert into comuna(nome, municipio_fk) values('Shiede', 42);

/*Comunas do Municipio do Ombadja*/
insert into comuna(nome, municipio_fk) values('Xangongo', 43);
insert into comuna(nome, municipio_fk) values('Humbe', 43);
insert into comuna(nome, municipio_fk) values('Mucope', 43);
insert into comuna(nome, municipio_fk) values('Naulila', 43);
insert into comuna(nome, municipio_fk) values('Ombala-yo-Mungo', 43);

/*provincia Huambo*/
/*Comunas do Municipio do Huambo*/
insert into comuna(nome, municipio_fk) values('Huambo', 44);
insert into comuna(nome, municipio_fk) values('Chipipa', 44);
insert into comuna(nome, municipio_fk) values('Kalima', 44);

/*Comunas do Municipio do Bailundo*/
insert into comuna(nome, municipio_fk) values('Bailundo', 45);
insert into comuna(nome, municipio_fk) values('Lunge', 45);
insert into comuna(nome, municipio_fk) values('Luvemba', 45);
insert into comuna(nome, municipio_fk) values('Bimbe', 45);
insert into comuna(nome, municipio_fk) values('Hengue', 45);

/*Comunas do Municipio do Ekunha*/
insert into comuna(nome, municipio_fk) values('Ekunha', 46);
insert into comuna(nome, municipio_fk) values('Tchipeio', 46);

/*Comunas do Municipio do Caála*/
insert into comuna(nome, municipio_fk) values('Caála', 47);
insert into comuna(nome, municipio_fk) values('Kalenga', 47);
insert into comuna(nome, municipio_fk) values('Katata', 47);
insert into comuna(nome, municipio_fk) values('Kuima', 47);

/*Comunas do Municipio do Catchiungo*/
insert into comuna(nome, municipio_fk) values('Catchiungo', 48);
insert into comuna(nome, municipio_fk) values('Chinhama', 48);
insert into comuna(nome, municipio_fk) values('Chiumbo', 48);

/*Comunas do Municipio do Londuimbale*/
insert into comuna(nome, municipio_fk) values('Londuimbale', 49);
insert into comuna(nome, municipio_fk) values('Alto Uama', 49);
insert into comuna(nome, municipio_fk) values('Galanga', 49);
insert into comuna(nome, municipio_fk) values('Kumbila', 49);
insert into comuna(nome, municipio_fk) values('Ussoke', 49);

/*Comunas do Municipio do Longojo*/
insert into comuna(nome, municipio_fk) values('Longojo', 50);
insert into comuna(nome, municipio_fk) values('Chilata', 50);
insert into comuna(nome, municipio_fk) values('Katabola', 50);
insert into comuna(nome, municipio_fk) values('Lepi', 50);

/*Comunas do Municipio do Mungo*/
insert into comuna(nome, municipio_fk) values('Mungo', 51);
insert into comuna(nome, municipio_fk) values('Kambuengo', 51);

/*Comunas do Municipio do Tchicala-Tcholoanga */
insert into comuna(nome, municipio_fk) values('Tchicala-Tcholoanga', 52);
insert into comuna(nome, municipio_fk) values('Mbave', 52);
insert into comuna(nome, municipio_fk) values('Sambo', 52);
insert into comuna(nome, municipio_fk) values('Samboto', 52);

/*Comunas do Municipio do Tchindjenje*/
insert into comuna(nome, municipio_fk) values('Tchindjenje', 53);
insert into comuna(nome, municipio_fk) values('Chiaca', 53);

/*Comunas do Municipio do Ucama*/
insert into comuna(nome, municipio_fk) values('Ucama', 54);
insert into comuna(nome, municipio_fk) values('Kakoma', 54);
insert into comuna(nome, municipio_fk) values('Mundundo', 54);

/*provincia Huíla*/
/*Comunas do Municipio do Caconda*/
insert into comuna(nome, municipio_fk) values('Caconda', 55);
insert into comuna(nome, municipio_fk) values('Cusse', 55);
insert into comuna(nome, municipio_fk) values('Gungui', 55);
insert into comuna(nome, municipio_fk) values('Uaba', 55);

/*Comunas do Municipio do Cacula*/
insert into comuna(nome, municipio_fk) values('Cacula', 56);

/*Comunas do Municipio do Caluquembe*/
insert into comuna(nome, municipio_fk) values('Caluquembe', 57);

/*Comunas do Municipio do Chiange*/
insert into comuna(nome, municipio_fk) values('Chiange', 58);
insert into comuna(nome, municipio_fk) values('Chibemba', 58);

/*Comunas do Municipio do Chibia*/
insert into comuna(nome, municipio_fk) values('Chibia', 59);
insert into comuna(nome, municipio_fk) values('Capunda', 59);
insert into comuna(nome, municipio_fk) values('Cavilongo', 59);
insert into comuna(nome, municipio_fk) values('Jau', 59);
insert into comuna(nome, municipio_fk) values('Quihita', 59);

/*Comunas do Municipio do Chicomba*/
insert into comuna(nome, municipio_fk) values('Chicomba', 60);
insert into comuna(nome, municipio_fk) values('Cutenda', 60);
insert into comuna(nome, municipio_fk) values('Libongue', 60);
insert into comuna(nome, municipio_fk) values('Quê', 60);

/*Comunas do Municipio do Chipindo*/
insert into comuna(nome, municipio_fk) values('Chipindo', 61);
insert into comuna(nome, municipio_fk) values('Bambi', 61);

/*Comunas do Municipio do Cuvango*/
insert into comuna(nome, municipio_fk) values('Cuvango', 62);
insert into comuna(nome, municipio_fk) values('Galangue', 62);
insert into comuna(nome, municipio_fk) values('Vicungo', 62);

/*Comunas do Municipio do Humpata*/
insert into comuna(nome, municipio_fk) values('Humpata', 63);

/*Comunas do Municipio do Jamba*/
insert into comuna(nome, municipio_fk) values('Jamba', 64);
insert into comuna(nome, municipio_fk) values('Dongo', 64);
insert into comuna(nome, municipio_fk) values('Cassinga', 64);

/*Comunas do Municipio do Lubango*/
insert into comuna(nome, municipio_fk) values('Lubango', 65);
insert into comuna(nome, municipio_fk) values('Arimba', 65);
insert into comuna(nome, municipio_fk) values('Hoque', 65);
insert into comuna(nome, municipio_fk) values('Huila', 65);

/*Comunas do Municipio do Matala*/
insert into comuna(nome, municipio_fk) values('Matala', 66);
insert into comuna(nome, municipio_fk) values('Capelongo', 66);
insert into comuna(nome, municipio_fk) values('Mulongo', 66);

/*Comunas do Municipio do Quilengues*/
insert into comuna(nome, municipio_fk) values('Quilengues', 67);
insert into comuna(nome, municipio_fk) values('Dinde', 67);
insert into comuna(nome, municipio_fk) values('Impulo', 67);

/*Comunas do Municipio do Quipungo*/
insert into comuna(nome, municipio_fk) values('Quipungo', 68);

/*provincia Kwanza Norte*/
/*Comunas do Municipio do Ambaca*/
insert into comuna(nome, municipio_fk) values('Ambaca', 69);
insert into comuna(nome, municipio_fk) values('Bindo', 69);
insert into comuna(nome, municipio_fk) values('Luinga', 69);
insert into comuna(nome, municipio_fk) values('Maua', 69);
insert into comuna(nome, municipio_fk) values('Tango', 69);

/*Comunas do Municipio do Banga*/
insert into comuna(nome, municipio_fk) values('Banga', 70);
insert into comuna(nome, municipio_fk) values('Aldeia Nova', 70);
insert into comuna(nome, municipio_fk) values('Caculo Cabaça', 70);
insert into comuna(nome, municipio_fk) values('Cariamba', 70);

/*Comunas do Municipio do Bolongongo*/
insert into comuna(nome, municipio_fk) values('Bolongongo', 71);
insert into comuna(nome, municipio_fk) values('Kiquiemba', 71);
insert into comuna(nome, municipio_fk) values('Terreiro', 71);

/*Comunas do Municipio do Dondo*/
insert into comuna(nome, municipio_fk) values('Dondo', 72);
insert into comuna(nome, municipio_fk) values('Dange-ia-Menha', 72);
insert into comuna(nome, municipio_fk) values('Massangano', 72);
insert into comuna(nome, municipio_fk) values('São Pedro da Quilemba', 72);
insert into comuna(nome, municipio_fk) values('Zenza do Itombe', 72);

/*Comunas do Municipio do Cazengo*/
insert into comuna(nome, municipio_fk) values('Ndalando', 73);
insert into comuna(nome, municipio_fk) values('Canhoca', 73);

/*Comunas do Municipio do Golungo Alto*/
insert into comuna(nome, municipio_fk) values('Golungo Alto', 74);
insert into comuna(nome, municipio_fk) values('Cambondo', 74);
insert into comuna(nome, municipio_fk) values('Cerca', 74);
insert into comuna(nome, municipio_fk) values('Kiluange', 74);

/*Comunas do Municipio do Lucala*/
insert into comuna(nome, municipio_fk) values('Lucala', 75);
insert into comuna(nome, municipio_fk) values('Kiangombe', 75);

/*Comunas do Municipio do Ngonguembo*/
insert into comuna(nome, municipio_fk) values('Ngonguembo (Quilombo dos Dembos)', 76);
insert into comuna(nome, municipio_fk) values('Camane', 76);
insert into comuna(nome, municipio_fk) values('Cavunga', 76);

/*Comunas do Municipio do Quiculungo*/
insert into comuna(nome, municipio_fk) values('Quiculungo', 77);

/*Comunas do Municipio do Samba Cajú */
insert into comuna(nome, municipio_fk) values('Samba Cajú', 78);
insert into comuna(nome, municipio_fk) values('Samba Lucala', 78);

/*provincia Kwanza Sul*/
/*Comunas do Municipio do Amboim*/
insert into comuna(nome, municipio_fk) values('Gabela', 79);
insert into comuna(nome, municipio_fk) values('Assango', 79);

/*Comunas do Municipio do Cassongue*/
insert into comuna(nome, municipio_fk) values('Cassongue', 80);
insert into comuna(nome, municipio_fk) values('Atome', 80);
insert into comuna(nome, municipio_fk) values('Dumbi', 80);
insert into comuna(nome, municipio_fk) values('Pambagala', 80);

/*Comunas do Municipio do Cela*/
insert into comuna(nome, municipio_fk) values('Waku Kungo', 81);
insert into comuna(nome, municipio_fk) values('Quissanga Kungo', 81);
insert into comuna(nome, municipio_fk) values('Sanga', 81);

/*Comunas do Municipio do Conda*/
insert into comuna(nome, municipio_fk) values('Conda', 82);
insert into comuna(nome, municipio_fk) values('Cunje', 82);

/*Comunas do Municipio do Ebo*/
insert into comuna(nome, municipio_fk) values('Ebo', 83);
insert into comuna(nome, municipio_fk) values('Conde', 83);
insert into comuna(nome, municipio_fk) values('Kassange', 83);

/*Comunas do Municipio do Libolo*/
insert into comuna(nome, municipio_fk) values('Calulo', 84);
insert into comuna(nome, municipio_fk) values('Cabuta', 84);
insert into comuna(nome, municipio_fk) values('Munenga', 84);
insert into comuna(nome, municipio_fk) values('Quissongo', 84);

/*Comunas do Municipio do Mussende*/
insert into comuna(nome, municipio_fk) values('Mussende', 85);
insert into comuna(nome, municipio_fk) values('Quipaxe', 85);

/*Comunas do Municipio do Porto Amboim*/
insert into comuna(nome, municipio_fk) values('Porto Amboim', 86);
insert into comuna(nome, municipio_fk) values('Capolo', 86);

/*Comunas do Municipio do Quilenda*/
insert into comuna(nome, municipio_fk) values('Quilenda', 87);
insert into comuna(nome, municipio_fk) values('Quirimbo', 87);

/*Comunas do Municipio do Quibala*/
insert into comuna(nome, municipio_fk) values('Quibala', 88);
insert into comuna(nome, municipio_fk) values('Cariango', 88);
insert into comuna(nome, municipio_fk) values('Dala Cachibo', 88);
insert into comuna(nome, municipio_fk) values('Lonhe', 88);

/*Comunas do Municipio do Seles*/
insert into comuna(nome, municipio_fk) values('Seles', 89);
insert into comuna(nome, municipio_fk) values('Amboiva', 89);
insert into comuna(nome, municipio_fk) values('Botera', 89);

/*Comunas do Municipio do Sumbe*/
insert into comuna(nome, municipio_fk) values('Sumbe', 90);
insert into comuna(nome, municipio_fk) values('Gangula', 90);
insert into comuna(nome, municipio_fk) values('Gungo', 90);
insert into comuna(nome, municipio_fk) values('Kicombo', 90);

/*provincia Luanda*/
/*Comunas do Municipio de Luanda*/
insert into comuna(nome, municipio_fk) values('Distrito de Ingombota', 91);
insert into comuna(nome, municipio_fk) values('Distrito de Maianga', 91);
insert into comuna(nome, municipio_fk) values('Distrito de Kilamba Kiaxi ', 91);
insert into comuna(nome, municipio_fk) values('Distrito de Rangel', 91);
insert into comuna(nome, municipio_fk) values('Distrito de Samba', 91);
insert into comuna(nome, municipio_fk) values('Distrito de Sambizanga', 91);

/*Comunas do Municipio de Belas*/
insert into comuna(nome, municipio_fk) values('Benfica', 92);
insert into comuna(nome, municipio_fk) values('Camama', 92);
insert into comuna(nome, municipio_fk) values('Barra do Kwanza', 92);
insert into comuna(nome, municipio_fk) values('Futungo de Belas', 92);
insert into comuna(nome, municipio_fk) values('Ramiros', 92);

/*Comunas do Municipio de Talatona*/

/*Comunas do Municipio de Viana*/
insert into comuna(nome, municipio_fk) values('Viana', 93);
insert into comuna(nome, municipio_fk) values('Distrito de Bela Flor ', 93);
insert into comuna(nome, municipio_fk) values('Distrito de Zango', 93);
insert into comuna(nome, municipio_fk) values('Calumbo', 93);

/*Comunas do Municipio de Cacuaco*/
insert into comuna(nome, municipio_fk) values('Cacuaco', 94);
insert into comuna(nome, municipio_fk) values('Funda', 94);
insert into comuna(nome, municipio_fk) values('Kikolo', 94);

/*Comunas do Municipio de Quiçama*/
insert into comuna(nome, municipio_fk) values('Cabo Ledo', 95);
insert into comuna(nome, municipio_fk) values('Muxima', 95);
insert into comuna(nome, municipio_fk) values('Demba-Chio', 95);
insert into comuna(nome, municipio_fk) values('Mumbondo', 95);
insert into comuna(nome, municipio_fk) values('Quixingue', 95);

/*Comunas do Municipio de Cazenga*/
insert into comuna(nome, municipio_fk) values('Cazenga Popular', 96);
insert into comuna(nome, municipio_fk) values('Hoji-ya-Henda', 96);
insert into comuna(nome, municipio_fk) values('Tala Hady', 96);

/*Comunas do Municipio de Ícolo e Bengo*/
insert into comuna(nome, municipio_fk) values('Cabiri', 97);
insert into comuna(nome, municipio_fk) values('Bom Jesus', 97);
insert into comuna(nome, municipio_fk) values('Catete', 97);
insert into comuna(nome, municipio_fk) values('Cassoneca', 97);
insert into comuna(nome, municipio_fk) values('Caculo Cahango', 97);

/* provincia Lunda Norte*/
/*Comunas do Municipio de Cambulo*/
insert into comuna(nome, municipio_fk) values('Cambulo', 98);
insert into comuna(nome, municipio_fk) values('Cachimo', 98);
insert into comuna(nome, municipio_fk) values('Canzar', 98);
insert into comuna(nome, municipio_fk) values('Luia', 98);

/*Comunas do Municipio de Capenda-Camulemba*/
insert into comuna(nome, municipio_fk) values('Capenda-Camulemba', 99);
insert into comuna(nome, municipio_fk) values('Xinge', 99);

/*Comunas do Municipio de Cangula*/
insert into comuna(nome, municipio_fk) values('Cangula', 100);
insert into comuna(nome, municipio_fk) values('Camaxilo', 100);

/*Comunas do Municipio de Chitato*/
insert into comuna(nome, municipio_fk) values('Lauchimo (Cidade do Dundo)', 101);

/*Comunas do Municipio de Cuango*/
insert into comuna(nome, municipio_fk) values('Cuango', 102);
insert into comuna(nome, municipio_fk) values('Luremo', 102);

/*Comunas do Municipio de Cuílo*/
insert into comuna(nome, municipio_fk) values('Cuílo', 103);
insert into comuna(nome, municipio_fk) values('Caluango', 103);

/*Comunas do Municipio de Lóvua*/
insert into comuna(nome, municipio_fk) values('Lóvua', 104);

/*Comunas do Municipio de Lubalo*/
insert into comuna(nome, municipio_fk) values('Lubalo', 105);
insert into comuna(nome, municipio_fk) values('Luangue', 105);
insert into comuna(nome, municipio_fk) values('Muruleje', 105);

/*Comunas do Municipio de Lucapa*/
insert into comuna(nome, municipio_fk) values('Lucapa', 106);
insert into comuna(nome, municipio_fk) values('Camissombo', 106);
insert into comuna(nome, municipio_fk) values('Capaia', 106);
insert into comuna(nome, municipio_fk) values('Xá-Cassau', 106);

/*Comunas do Municipio de Xá-Muteba*/
insert into comuna(nome, municipio_fk) values('Xá-Muteba', 107);
insert into comuna(nome, municipio_fk) values('Cassangue-Calocala', 107);
insert into comuna(nome, municipio_fk) values('Longo', 107);

/* provincia Lunda Sul*/
/*Comunas do Municipio de Cacolo*/
insert into comuna(nome, municipio_fk) values('Cacolo', 108);
insert into comuna(nome, municipio_fk) values('Alto Chicapa', 108);
insert into comuna(nome, municipio_fk) values('Cucumbi', 108);
insert into comuna(nome, municipio_fk) values('Xassengue', 108);

/*Comunas do Municipio de Dala*/
insert into comuna(nome, municipio_fk) values('Dala', 109);
insert into comuna(nome, municipio_fk) values('Cazage', 109);
insert into comuna(nome, municipio_fk) values('Luma-Cassai', 109);

/*Comunas do Municipio de Muconda*/
insert into comuna(nome, municipio_fk) values('Muconda', 110);
insert into comuna(nome, municipio_fk) values('Cassai-Sul', 110);
insert into comuna(nome, municipio_fk) values('Chiluange', 110);
insert into comuna(nome, municipio_fk) values('Murieje', 110);

/*Comunas do Municipio de Saurimo*/
insert into comuna(nome, municipio_fk) values('Saurimo', 111);
insert into comuna(nome, municipio_fk) values('Mona-Quimbundo', 111);
insert into comuna(nome, municipio_fk) values('Sombo', 111);

/* provincia Malanje*/
/*Comunas do Municipio de Caombo*/
insert into comuna(nome, municipio_fk) values('Caombo', 112);
insert into comuna(nome, municipio_fk) values('Bange-Angola', 112);
insert into comuna(nome, municipio_fk) values('Kambo Suinginge', 112);
insert into comuna(nome, municipio_fk) values('Micanda', 112);

/*Comunas do Municipio de Kacuso*/
insert into comuna(nome, municipio_fk) values('Kacuso', 113);
insert into comuna(nome, municipio_fk) values('Lombe', 113);
insert into comuna(nome, municipio_fk) values('Pungo-Andongo', 113);
insert into comuna(nome, municipio_fk) values('Quizenga', 113);

/*Comunas do Municipio de Kalandula*/
insert into comuna(nome, municipio_fk) values('Kalandula', 114);
insert into comuna(nome, municipio_fk) values('Kota', 114);
insert into comuna(nome, municipio_fk) values('Kinge', 114);
insert into comuna(nome, municipio_fk) values('Kinge', 114);
insert into comuna(nome, municipio_fk) values('Kateco-Kangola', 114);
insert into comuna(nome, municipio_fk) values('Kuale', 114);

/*Comunas do Municipio de Cabundi-Catembo*/
insert into comuna(nome, municipio_fk) values('Cabundi-Catembo', 115);
insert into comuna(nome, municipio_fk) values('Dumba Cabango', 115);
insert into comuna(nome, municipio_fk) values('Kitape', 115);
insert into comuna(nome, municipio_fk) values('Tala-Mungongo', 115);

/*Comunas do Municipio de Cangandala*/
insert into comuna(nome, municipio_fk) values('Cangandala', 116);
insert into comuna(nome, municipio_fk) values('Caribo', 116);
insert into comuna(nome, municipio_fk) values('Culamagia', 116);
insert into comuna(nome, municipio_fk) values('Mbembo', 116);

/*Comunas do Municipio de Cuaba Nzogo*/
insert into comuna(nome, municipio_fk) values('Cuaba Nzogo', 117);
insert into comuna(nome, municipio_fk) values('Mufuma', 117);

/*Comunas do Municipio de Cunda-Dia-Baze*/
insert into comuna(nome, municipio_fk) values('Cunda-Dia-Baze', 118);
insert into comuna(nome, municipio_fk) values('Lemba', 118);
insert into comuna(nome, municipio_fk) values('Milando', 118);

/*Comunas do Municipio de Luquembo*/
insert into comuna(nome, municipio_fk) values('Luquembo', 119);
insert into comuna(nome, municipio_fk) values('Dombo', 119);
insert into comuna(nome, municipio_fk) values('Capunda', 119);
insert into comuna(nome, municipio_fk) values('Quimbango', 119);
insert into comuna(nome, municipio_fk) values('Ringa', 119);

/*Comunas do Municipio de Malanje*/
insert into comuna(nome, municipio_fk) values('Malanje', 120);
insert into comuna(nome, municipio_fk) values('Cambaxe', 120);
insert into comuna(nome, municipio_fk) values('Cambondo', 120);
insert into comuna(nome, municipio_fk) values('Cangando', 120);
insert into comuna(nome, municipio_fk) values('Ngola-Luíje', 120);
insert into comuna(nome, municipio_fk) values('Quimbamba', 120);

/*Comunas do Municipio de Marimba*/
insert into comuna(nome, municipio_fk) values('Marimba', 121);
insert into comuna(nome, municipio_fk) values('Dala Samba ', 121);
insert into comuna(nome, municipio_fk) values('embo-Aluma', 121);

/*Comunas do Municipio de Massango*/
insert into comuna(nome, municipio_fk) values('Massango', 122);
insert into comuna(nome, municipio_fk) values('Quihuhu', 122);
insert into comuna(nome, municipio_fk) values('Quinguengue', 122);

/*Comunas do Municipio de Mucari*/
insert into comuna(nome, municipio_fk) values('Mucari (Caculama)', 123);
insert into comuna(nome, municipio_fk) values('Catala', 123);
insert into comuna(nome, municipio_fk) values('Caxinga', 123);
insert into comuna(nome, municipio_fk) values('Muquixi', 123);

/*Comunas do Municipio de Quela*/
insert into comuna(nome, municipio_fk) values('Quela', 124);
insert into comuna(nome, municipio_fk) values('Missão dos Bangalas', 124);
insert into comuna(nome, municipio_fk) values('Moma', 124);
insert into comuna(nome, municipio_fk) values('Xandele', 124);

/*Comunas do Municipio de Quirima*/
insert into comuna(nome, municipio_fk) values('Quirima', 125);
insert into comuna(nome, municipio_fk) values('Sautar', 125);

/* provincia Moxico*/
/*Comunas do Municipio de Alto Zambeze*/
insert into comuna(nome, municipio_fk) values('Cazombo', 126);
insert into comuna(nome, municipio_fk) values('Caianda', 126);
insert into comuna(nome, municipio_fk) values('Calunda', 126);
insert into comuna(nome, municipio_fk) values('Cazombo', 126);
insert into comuna(nome, municipio_fk) values('Kaquengue', 126);
insert into comuna(nome, municipio_fk) values('Kavungo', 126);
insert into comuna(nome, municipio_fk) values('Lóvua', 126);
insert into comuna(nome, municipio_fk) values('Macondo', 126);

/*Comunas do Municipio de Bundas*/
insert into comuna(nome, municipio_fk) values('Chiume', 127);
insert into comuna(nome, municipio_fk) values('Lumbala Nguimbo', 127);
insert into comuna(nome, municipio_fk) values('Lutembo', 127);
insert into comuna(nome, municipio_fk) values('Luvuei', 127);
insert into comuna(nome, municipio_fk) values('Mussuma', 127);
insert into comuna(nome, municipio_fk) values('Ninda', 127);
insert into comuna(nome, municipio_fk) values('Sessa', 127);

/*Comunas do Municipio de Camongue*/
insert into comuna(nome, municipio_fk) values('Camongue', 128);

/*Comunas do Municipio de Cameia*/
insert into comuna(nome, municipio_fk) values('Cameia', 129);

/*Comunas do Municipio de Luacano*/
insert into comuna(nome, municipio_fk) values('Luacano', 130);
insert into comuna(nome, municipio_fk) values('Lago-Dilolo', 130);

/*Comunas do Municipio de Luau*/
insert into comuna(nome, municipio_fk) values('Luau', 131);

/*Comunas do Municipio de Luchazes*/
insert into comuna(nome, municipio_fk) values('Luchazes', 132);
insert into comuna(nome, municipio_fk) values('Camgombo', 132);
insert into comuna(nome, municipio_fk) values('Cassamba', 132);
insert into comuna(nome, municipio_fk) values('Muié', 132);
insert into comuna(nome, municipio_fk) values('Tempue', 132);

/*Comunas do Municipio de Luena*/
insert into comuna(nome, municipio_fk) values('Luena', 133);
insert into comuna(nome, municipio_fk) values('Cangumbe/Kachipoque', 133);
insert into comuna(nome, municipio_fk) values('Lucusse', 133);
insert into comuna(nome, municipio_fk) values('Lutuai (muangai)', 133);

/*Comunas do Municipio de Léua*/
insert into comuna(nome, municipio_fk) values('Léua', 134);
insert into comuna(nome, municipio_fk) values('Liangongo (Sandando)', 134);

/* provincia Namibe*/
/*Comunas do Municipio de Bibala*/
insert into comuna(nome, municipio_fk) values('Bibala', 135);
insert into comuna(nome, municipio_fk) values('Caitou', 135);
insert into comuna(nome, municipio_fk) values('Lola', 135);
insert into comuna(nome, municipio_fk) values('Kapagombe', 135);

/*Comunas do Municipio de Camacuio*/
insert into comuna(nome, municipio_fk) values('Camacuio', 136);
insert into comuna(nome, municipio_fk) values('Chingo', 136);
insert into comuna(nome, municipio_fk) values('Chinquite', 136);

/*Comunas do Municipio de Namibe*/
insert into comuna(nome, municipio_fk) values('Namibe', 137);
insert into comuna(nome, municipio_fk) values('Lucira', 137);
insert into comuna(nome, municipio_fk) values('Bentiaba', 137);
insert into comuna(nome, municipio_fk) values('Tômbua', 137);
insert into comuna(nome, municipio_fk) values('Baía dos Tigres', 137);
insert into comuna(nome, municipio_fk) values('Virei', 137);
insert into comuna(nome, municipio_fk) values('Virei', 137);
insert into comuna(nome, municipio_fk) values('Cainde', 137);

/* provincia Uíge*/
/*Comunas do Municipio de Ambuíla*/
insert into comuna(nome, municipio_fk) values('Ambuíla', 138);
insert into comuna(nome, municipio_fk) values('Quipedro', 138);

/*Comunas do Municipio de Bumbe*/
insert into comuna(nome, municipio_fk) values('Bumbe', 139);
insert into comuna(nome, municipio_fk) values('Lucanga', 139);
insert into comuna(nome, municipio_fk) values('Mabaia', 139);

/*Comunas do Municipio de Buengas*/
insert into comuna(nome, municipio_fk) values('Cuilo Cambozo', 140);
insert into comuna(nome, municipio_fk) values('Nova Esperança', 140);
insert into comuna(nome, municipio_fk) values('Quimbianda', 140);

/*Comunas do Municipio de Bungo*/
insert into comuna(nome, municipio_fk) values('Bungo', 141);

/*Comunas do Municipio de Cangola*/
insert into comuna(nome, municipio_fk) values('Cangola', 142);
insert into comuna(nome, municipio_fk) values('Bengo', 142);
insert into comuna(nome, municipio_fk) values('Kaiango', 142);

/*Comunas do Municipio de Damba*/
insert into comuna(nome, municipio_fk) values('Damba', 143);
insert into comuna(nome, municipio_fk) values('Camatambo', 143);
insert into comuna(nome, municipio_fk) values('Lemboa', 143);
insert into comuna(nome, municipio_fk) values('Lombe (NSosso)', 143);
insert into comuna(nome, municipio_fk) values('Pete Cusso (Sacamo)', 143);

/*Comunas do Municipio de Maquela do Zombo*/
insert into comuna(nome, municipio_fk) values('Maquela do Zombo', 144);
insert into comuna(nome, municipio_fk) values('Béu', 144);
insert into comuna(nome, municipio_fk) values('Cuilo Futa', 144);
insert into comuna(nome, municipio_fk) values('Quibocolo', 144);
insert into comuna(nome, municipio_fk) values('Sacandica', 144);

/*Comunas do Municipio de Mucaba*/
insert into comuna(nome, municipio_fk) values('Uando', 145);
insert into comuna(nome, municipio_fk) values('Quinzala', 145);

/*Comunas do Municipio de Negaje*/
insert into comuna(nome, municipio_fk) values('Negaje', 146);
insert into comuna(nome, municipio_fk) values('Dimuca', 146);
insert into comuna(nome, municipio_fk) values('Quisseque', 146);

/*Comunas do Municipio de Puri*/
insert into comuna(nome, municipio_fk) values('Puri', 147);

/*Comunas do Municipio de Quimbele*/
insert into comuna(nome, municipio_fk) values('Qumbele', 148);
insert into comuna(nome, municipio_fk) values('Alto-Zaza', 148);
insert into comuna(nome, municipio_fk) values('Cuango', 148);
insert into comuna(nome, municipio_fk) values('Icoca', 148);

/*Comunas do Municipio de Quitexe*/
insert into comuna(nome, municipio_fk) values('Quitexe', 149);
insert into comuna(nome, municipio_fk) values('Cambamba', 149);
insert into comuna(nome, municipio_fk) values('Quifuafua (Vista alegre)', 149);
insert into comuna(nome, municipio_fk) values('Quitende (Aldeia Vissosa)', 149);

/*Comunas do Municipio de Santa Cruz*/
insert into comuna(nome, municipio_fk) values('Santa Cruz (Milunga)', 150);
insert into comuna(nome, municipio_fk) values('Macocola', 150);
insert into comuna(nome, municipio_fk) values('Macolo', 150);
insert into comuna(nome, municipio_fk) values('Massau', 150);

/*Comunas do Municipio de Sanza Pombo*/
insert into comuna(nome, municipio_fk) values('Sanza Pombo', 151);
insert into comuna(nome, municipio_fk) values('Alfândega', 151);
insert into comuna(nome, municipio_fk) values('Cuilo Pombo', 151);
insert into comuna(nome, municipio_fk) values('Wamba', 151);

/*Comunas do Municipio de Songo*/
insert into comuna(nome, municipio_fk) values('Songo', 152);
insert into comuna(nome, municipio_fk) values('Kinvuenga', 152);

/*Comunas do Municipio de Uíge*/
insert into comuna(nome, municipio_fk) values('Uíge', 152);

/* provincia Zaire*/
/*Comunas do Municipio de Cuimba*/
insert into comuna(nome, municipio_fk) values('Cuimba', 153);
insert into comuna(nome, municipio_fk) values('Buela', 153);
insert into comuna(nome, municipio_fk) values('Kanda', 153);
insert into comuna(nome, municipio_fk) values('Luvaca', 153);

/*Comunas do Municipio de MBanza Congo*/
insert into comuna(nome, municipio_fk) values('MBanza Congo', 154);
insert into comuna(nome, municipio_fk) values('Kalambata', 154);
insert into comuna(nome, municipio_fk) values('Kaluka', 154);
insert into comuna(nome, municipio_fk) values('Kiende', 154);
insert into comuna(nome, municipio_fk) values('Luvo', 154);
insert into comuna(nome, municipio_fk) values('Madimba', 154);

/*Comunas do Municipio de Nzeto*/
insert into comuna(nome, municipio_fk) values('Nzeto', 155);
insert into comuna(nome, municipio_fk) values('Kindenje', 155);
insert into comuna(nome, municipio_fk) values('Musserra', 155);
insert into comuna(nome, municipio_fk) values('Quibala Norte (Ioge)', 155);

/*Comunas do Municipio de Nóqui*/
insert into comuna(nome, municipio_fk) values('Nóqui', 156);
insert into comuna(nome, municipio_fk) values('Lufico', 156);
insert into comuna(nome, municipio_fk) values('Lulendo (Mpala)', 156);

/*Comunas do Municipio de Soyo*/
insert into comuna(nome, municipio_fk) values('Soyo', 157);
insert into comuna(nome, municipio_fk) values('Mangue Grande', 157);
insert into comuna(nome, municipio_fk) values('Pedra de Feitiço', 157);
insert into comuna(nome, municipio_fk) values('Quêlo', 157);
insert into comuna(nome, municipio_fk) values('Sumba', 157);

/*Comunas do Municipio de Tomboco*/
insert into comuna(nome, municipio_fk) values('Tomboco', 158);
insert into comuna(nome, municipio_fk) values('Kiximba', 158);
insert into comuna(nome, municipio_fk) values('Kinzau', 158);



/*insertir Endereco*/
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Nelito Soares', 'Portugualia', 1,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Martires', 'Portugualia', 8,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Cassenda', 'Desconhecida', 6,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Prenda', 'Desconhecida', 7,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Mutamba', 'Desconhecida', 12,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Alvalade', 'Desconhecida', 6,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Projecto Nandó', 'M', 16,1);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Golfo 2', 'Desconhecida', 0,1);

insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Comissão Katepa', 'Cantinas', 1,120);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Bispado', 'NSF', 1,120);
insert into endereco(bairro, rua, numero_casa, comuna_fk) values('Maxinde', 'Missão',2, 120);



/*insertir Sexo*/
insert into sexo(nome) values('Masculino');
insert into sexo(nome) values('Feminino');

INSERT INTO estado_civil(nome) VALUES('Solteiro');
INSERT INTO estado_civil(nome) VALUES('Casado');
INSERT INTO estado_civil(nome) VALUES('Viuvo');

INSERT INTO tipo_telefone(operadora) VALUES('Unitel');
INSERT INTO tipo_telefone(operadora) VALUES('Movicel');

INSERT INTO tipo_email(dominio) VALUES('@gmail.com');
INSERT INTO tipo_email(dominio) VALUES('@google.com');
INSERT INTO tipo_email(dominio) VALUES('@outlook.com');
INSERT INTO tipo_email(dominio) VALUES('@live.pt');

INSERT INTO tipo_conta(nome) VALUES('root');
INSERT INTO tipo_conta(nome) VALUES('admin');
INSERT INTO tipo_conta(nome) VALUES('funcionario');
INSERT INTO tipo_conta(nome) VALUES('cliente');


INSERT INTO fornecedor(nome) VALUES('superAfrica');


INSERT INTO conta (tipo_conta_fk,nome_usuario, senha_usuario) VALUES(1, 'root','root');
INSERT INTO conta (tipo_conta_fk,nome_usuario, senha_usuario) VALUES(2, 'admin','admin');
INSERT INTO conta (tipo_conta_fk,nome_usuario, senha_usuario) VALUES(3, 'funcionario','funcionario');
INSERT INTO conta (tipo_conta_fk,nome_usuario, senha_usuario) VALUES(4, 'cliente','cliente');
INSERT INTO conta (tipo_conta_fk,nome_usuario, senha_usuario) VALUES(4, 'cliente_cadastrado','cliente_cadastrado');


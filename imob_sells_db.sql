/* Entidades
 * usuários
 * corretores
 * clientes 
 * proprietários
 * fiadores
 * imóveis
 * empresa */
DROP DATABASE imob_sells_db;
CREATE DATABASE imob_sells_db;
USE imob_sells_db;
CREATE TABLE portfolio (
    portfolio_pk CHARACTER VARYING (45) NOT NULL PRIMARY KEY,
    designacao CHARACTER VARYING (45) NOT NULL,
    portfolio_fk_pai CHARACTER VARYING (45),
    CONSTRAINT portfolio_fk_pai FOREIGN KEY(portfolio_fk_pai) REFERENCES portfolio(portfolio_pk) MATCH SIMPLE
);
CREATE TABLE localizacao (
    pkSeq SERIAL NOT NULL,
    localizacao_pk CHARACTER VARYING (45) NOT NULL PRIMARY KEY,
    designacao CHARACTER VARYING (45) NOT NULL,
    eh_distrito boolean NOT NULL,
    localizacao_fk_pai CHARACTER VARYING (45),
    ehNivel CHARACTER VARYING (45) NOT NULL,
    CONSTRAINT localizacao_fk_pai FOREIGN KEY(localizacao_fk_pai) REFERENCES localizacao(localizacao_pk) MATCH SIMPLE
);
CREATE TABLE endereco (
    endereco_pk SERIAL NOT NULL PRIMARY KEY,
    rua CHARACTER VARYING(45) NOT NULL,
    numero_casa CHARACTER VARYING(45) NOT NULL,
    localizacao_fk CHARACTER VARYING(45),
    CONSTRAINT localizacao_fk FOREIGN KEY(localizacao_fk) REFERENCES localizacao(localizacao_pk) MATCH SIMPLE
);
CREATE TABLE telefone(
    telefone_pk SERIAL NOT NULL PRIMARY KEY,
    numero CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE sexo(
    sexo_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE estado_civil(
    estado_civil_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE email (
    email_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE pessoa(
    pessoa_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL,
    data_nascimento CHARACTER VARYING(45) NOT NULL,
    sexo_fk INT NOT NULL,
    estado_civil_fk INT NOT NULL,
    telefone_fk INT NOT NULL,
    endereco_fk INT NOT NULL,
    email_fk INT NOT NULL,
    CONSTRAINT estado_civil_fk FOREIGN KEY(estado_civil_fk) REFERENCES estado_civil(estado_civil_pk) MATCH SIMPLE,
    CONSTRAINT telefone_fk FOREIGN KEY(telefone_fk) REFERENCES telefone(telefone_pk) MATCH SIMPLE,
    CONSTRAINT sexo_fk FOREIGN KEY(sexo_fk) REFERENCES sexo(sexo_pk) MATCH SIMPLE,
    CONSTRAINT email_fk FOREIGN KEY(email_fk) REFERENCES email(email_pk) MATCH SIMPLE,
    CONSTRAINT endereco_fk FOREIGN KEY(endereco_fk) REFERENCES endereco(endereco_pk) MATCH SIMPLE
);
DROP TABLE pessoa CASCADE;
ALTER TABLE PUBLIC.pessoa
ADD COLUMN email_fk INT NOT NULL;
ALTER TABLE PUBLIC.pessoa
ADD CONSTRAINT email_fk FOREIGN KEY(email_fk) REFERENCES email(email_pk) MATCH SIMPLE;
CREATE TABLE tipo_conta (
    tipo_conta_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE conta (
    conta_pk SERIAL NOT NULL PRIMARY KEY,
    tipo_conta_fk INT NOT NULL,
    nome_usuario CHARACTER VARYING(45) NOT NULL,
    senha_usuario CHARACTER VARYING(45) NOT NULL,
    CONSTRAINT tipo_conta_fk FOREIGN KEY(tipo_conta_fk) REFERENCES tipo_conta(tipo_conta_pk) MATCH SIMPLE
);
CREATE TABLE tipo_cliente (
    tipo_cliente_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE cliente (
    cliente_pk SERIAL NOT NULL PRIMARY KEY,
    pessoa_fk INT NOT NULL,
    tipo_cliente_fk INT NOT NULL,
    CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE,
    CONSTRAINT tipo_cliente_fk FOREIGN KEY(tipo_cliente_fk) REFERENCES tipo_cliente(tipo_cliente_Pk) MATCH SIMPLE
);
CREATE TABLE categoria (
    categoria_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE tipo_funcionario (
    tipo_funcionario_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE funcionario (
    funcionario_pk SERIAL NOT NULL PRIMARY KEY,
    tipo_funcionario_fk INT NOT NULL,
    pessoa_fk INT NOT NULL,
    CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE,
    CONSTRAINT tipo_funcionario_fk FOREIGN KEY(tipo_funcionario_fk) REFERENCES tipo_funcionario(tipo_funcionario_pk) MATCH SIMPLE
);
CREATE TABLE tipo_fornecedor (
    tipo_fornecedor_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE fornecedor (
    fornecedor_pk SERIAL NOT NULL PRIMARY KEY,
    nome CHARACTER VARYING(45),
    tipo_fornecedor_fk INT NOT NULL,
    pessoa_fk INT,
    CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE,
    CONSTRAINT tipo_fornecedor_fk FOREIGN KEY(tipo_fornecedor_fk) REFERENCES tipo_fornecedor(tipo_fornecedor_pk) MATCH SIMPLE
);
SELECT CURRENT_USER,
    CURRENT_DATE;
CREATE TABLE produto (
    produto_pk SERIAL NOT NULL PRIMARY KEY,
    designacao CHARACTER varying(45) NOT NULL,
    imagem CHARACTER varying(45),
    portfolio_fk CHARACTER varying(45) NOT NULL,
    preco DOUBLE PRECISION NOT NULL,
    fornecedor_fk INT NOT NULL,
    quantidade INT NOT NULL,
    data_registro DATE NOT NULL,
    CONSTRAINT portfolio_fk FOREIGN KEY(portfolio_fk) REFERENCES portfolio(portfolio_pk) MATCH SIMPLE,
    CONSTRAINT fornecedor_fk FOREIGN KEY(fornecedor_fk) REFERENCES fornecedor(fornecedor_pk) MATCH SIMPLE
);
CREATE TABLE stock (
    stock_pk SERIAL NOT NULL PRIMARY KEY,
    designacao CHARACTER VARYING (45) NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    limite_maximo INT NOT NULL,
    produto_fk INT NOT NULL,
    CONSTRAINT produto_fk FOREIGN KEY(produto_fk) REFERENCES produto(produto_pk) MATCH SIMPLE
);
CREATE TABLE forma_pagamento (
    forma_pagamento_pk SERIAL NOT NULL PRIMARY KEY,
    designacao CHARACTER VARYING(45) NOT NULL
);
CREATE TABLE venda (
    venda_pk SERIAL NOT NULL PRIMARY KEY,
    data_venda DATE NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    cliente_fk INT NOT NULL,
    funcionario_fk INT NOT NULL,
    forma_pagamento_fk INT NOT NULL,
    CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
    CONSTRAINT funcionario_fk FOREIGN KEY(funcionario_fk) REFERENCES funcionario(funcionario_pk) MATCH SIMPLE,
    CONSTRAINT forma_pagamento_fk FOREIGN KEY(forma_pagamento_fk) REFERENCES forma_pagamento(forma_pagamento_pk) MATCH SIMPLE
);
CREATE TABLE item_venda (
    item_venda_pk SERIAL NOT NULL PRIMARY KEY,
    quantidade INT NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    venda_fk INT NOT NULL,
    produto_fk INT NOT NULL,
    CONSTRAINT venda_fk FOREIGN KEY(venda_fk) REFERENCES venda(venda_pk) MATCH SIMPLE,
    CONSTRAINT produto_fk FOREIGN KEY(produto_fk) REFERENCES produto(produto_pk) MATCH SIMPLE
);
/* / / CREATE TABLE proprietario  corretor (
 corretor_pk SERIAL NOT NULL PRIMARY KEY,
 pessoa_fk INT NOT NULL,
 CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE
 );
 
 / / CREATE TABLE fiador (
 fiador_pk SERIAL NOT NULL PRIMARY KEY,
 pessoa_fk INT NOT NULL,
 CONSTRAINT pessoa_fk FOREIGN KEY(pessoa_fk) REFERENCES pessoa(pessoa_pk) MATCH SIMPLE
 );
 
 CREATE TABLE tipo_imovel (
 tipo_imovel_pk SERIAL NOT NULL PRIMARY KEY,
 pessoa_fk INT NOT NULL,
 nome CHARACTER VARYING(45) NOT NULL
 );
 
 CREATE TABLE imovel (
 imovel_pk SERIAL NOT NULL PRIMARY KEY,
 data_mercado CHARACTER VARYING(45) NOT NULL,
 data_venda CHARACTER VARYING(45) NOT NULL,
 cliente_fk INT NOT NULL,
 nome_imovel CHARACTER VARYING(45) NOT NULL,
 descricao CHARACTER VARYING,
 tipo_imovel_fk INT NOT NULL,
 endereco_fk INT NOT NULL,
 numero_quartos INT NOT NULL,
 preco_venda NUMERIC(10, 2),
 preco_comprador NUMERIC(10, 2),
 preco_acordado NUMERIC(10, 2),
 CONSTRAINT endereco_fk FOREIGN KEY(endereco_fk) REFERENCES endereco(endereco_pk) MATCH SIMPLE,
 CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
 CONSTRAINT tipo_imovel_fk FOREIGN KEY(tipo_imovel_fk) REFERENCES tipo_imovel(tipo_imovel_pk) MATCH SIMPLE
 );
 
 CREATE TABLE contrato (
 contrato_pk SERIAL NOT NULL PRIMARY KEY,
 imovel_fk INT NOT NULL,
 cliente_fk INT NOT NULL,
 funcionario_fk INT NOT NULL,
 data_inicio CHARACTER VARYING(45) NOT NULL,
 data_fim CHARACTER VARYING(45) NOT NULL,
 estado_contrato CHARACTER VARYING (45) NOT NULL,
 valor_pagamento NUMERIC(10, 2),
 total NUMERIC(10, 2),
 CONSTRAINT imovel_fk FOREIGN KEY(imovel_fk) REFERENCES imovel(imovel_pk) MATCH SIMPLE,
 CONSTRAINT cliente_fk FOREIGN KEY(cliente_fk) REFERENCES cliente(cliente_pk) MATCH SIMPLE,
 CONSTRAINT funcionario_fk FOREIGN KEY(funcionario_fk) REFERENCES funcionario(funcionario_pk) MATCH SIMPLE
 ); */
/*insertir Endereco*/
INSERT INTO public.localizacao (
        localizacao_pk,
        designacao,
        eh_distrito,
        localizacao_fk_pai,
        ehNivel
    )
VALUES ('0', 'default', 'False', '0', '1'),
    ('1', 'Angola', 'false', '0', '1'),
    ('1.1', 'Luanda', 'False', '1', '2'),
    ('1.1.1.2', 'Palanca', 'True', '1.1', '3'),
    ('1.1.1.1', 'Golfe', 'True', '1.1', '3'),
    ('1.1.1.3', 'Vila Estóril', 'True', '1.1', '3'),
    ('1.1.1.4', 'Sapú', 'True', '1.1', '3');
INSERT INTO PUBLIC.endereco (rua, numero_casa, localizacao_fk)
VALUES ('A', 200, '1.1.1.4'),
    ('B', 201, '1.1.1.4'),
    ('C', 202, '1.1.1.4'),
    ('D', 203, '1.1.1.4'),
    ('E', 204, '1.1.1.3'),
    ('F', 205, '1.1.1.3'),
    ('G', 206, '1.1.1.3'),
    ('H', 207, '1.1.1.3');
INSERT INTO public.pessoa (
        nome,
        data_nascimento,
        sexo_fk,
        estado_civil_fk,
        localizacao_fk
    )
VALUES ('Adão Silva', '20-10-1995', 1, 2, 1.1),
    (' Silva Antonio', '12-01-1987', 1, 1, 1.1),
    ('Rita Isbael', '2-04-1982', 2, 3, 1.1),
    ('Domingas Lourenço', '15-07-1993', 2, 2, 1.1);
/*insert Sexo*/
INSERT INTO sexo(nome)
VALUES ('Masculino'),
    ('Feminino');
/****/
INSERT INTO estado_civil(nome)
VALUES ('Solteiro'),
    ('Casado'),
    ('Viuvo');
INSERT INTO fornecedor(nome)
VALUES ('superAfrica');
/* *************** */
INSERT INTO tipo_conta(nome)
VALUES ('root'),
    ('admin'),
    ('funcionario'),
    ('cliente');
/*  */
INSERT INTO conta (tipo_conta_fk, nome_usuario, senha_usuario)
VALUES (1, 'root', 'root'),
    (2, 'admin', 'admin'),
    (3, 'funcionario', 'funcionario'),
    (4, 'cliente', 'cliente'),
    (4, 'cliente_cadastrado', 'cliente_cadastrado');
INSERT INTO telefone (numero)
VALUES ('923028762'),
    ('929214112'),
    ('926974576'),
    ('925014329'),
    ('930707729');
/***/
INSERT INTO email (nome)
VALUES ('teste@imobsells.com'),
    ('dedenol@imobsells.com'),
    ('vision@imobsells.com'),
    ('tito@imobsells.com'),
    ('zeca@imobsells.com');
/***/
INSERT INTO tipo_cliente (nome)
VALUES ('fisica'),
    ('juridica');
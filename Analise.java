
/********************************************************************************
* Ficheiro:Analise.java														*
* Projecto:P5	
* Autor: Adilson Zumba Manuel												*
* Nº 13155
* Data: 30 Junho 2021														*
* Objectivo: Analise do Sistema de Gerencia de um supermercado				*
* Docente: Prof.Dr.Eng. Aires Veloso 					        				*
* Cadeira: Distribuidos Paralelos I											*
*******************************************************************************/

 /*
1- Definicao do Problema: 
	Uma aplicação web para gestão de uma Loja de Relogios, com as seguintes funcionalidade:
	1.1- Registar, Visualizar, Editar, Pesquisar Cliente
	1.2- Registar, Visualizar, Editar, Pesquisar Funcionario
	1.3- Registar, Visualizar, Editar, Pesquisar Vendas
	1.3- Registar, Visualizar, Editar, Pesquisar Produtos (Relogio)

____________________________________________________________________________________________________________________________
	2- Analise:
		2.1. 1.2 INTERFACE COM O UTILIZADOR:
		# Termos de Uso;
		# Login;
		# Menu Principal (com funcionalidades diferentes dependendo das credenciais do utilizador).
		**** Menu Principal para o 'Administrador':

			-> Home
	
			-> Armazem:

				-> Catalogar Produto
				-> Cadastrar Produto
				-> Definir Preços
				-> Listar Produto
				
			-> Funcionario:

				-> Cadastrar
				-> Listar

			-> Montra:

				-> Gestão
				-> Listar
				
			-> Balancetes:

				-> Diários
				-> Mensais
				-> Intervalo de Tempo
				-> Tipo de Produto
				-> Em Ruptura
				-> Quase Ruptura
				-> Pré-Ruptura
			
			* Menu Principal para o 'Funcionario':

				Este Menu é Analogo ao do Administrador.
			
			* Menu Principal para o 'Cliente':

			-> Home

			-> Montra:

				-> Listar

			-> Cliente:

				-> Registar-se


	
_______________________________________________________________________________________________________________________________

		2.2. Identificacao das entidades ligadas a Interface do Utilizador

			 Entidade Provincia
				pk_provincia:				String
				designacao:				String
	
			Entidade Municipio:
				pk_municipio:				String
				designacao:				String
				provincia:				Provincia

			Entidade Comuna:
				pk_comuna:				String
				designacao:				String
				municipio:				Municipio
		
			Entidade Contacto
				codigo					String
				email:					String
				telefone1:				String
				telefone2:				String

			Entidade Conta
				codigo:					String
				user:					String
				senha:					String
				nivelAcesso:			int
	
			Entidade Pessoa
				codigo:					String
				nome:					String
				genero:					String
				foto:                	String
				dataNascimento:			Date
				dataRegistro:			Date
				municipio:				Municipio
				contacto:				String
				conta:					int
	
			Entidade Fornecedor (Fabricante)
				codigo:					int
				nome:					String

			

			Entidade produto (Produto)
				codigo:					int
				titulo:					String
				foto:					String / Image
				dataRegistro:			String
				quantidade:				int
				marca:					int
				descricao:				String
				preco: 					double
				funcionario:			int
				fornecedor:				int


            Entidade Montra
                produtos:               int
                funcionario:            int

			Entidade Cliente herda de Pessoa
	
			Entidade ItemVenda 
				codVenda:				int
				quantidade:				int
				produto:				int
				valor:					float
	
			Entidade VendaCompra 
				codigo:					int
				dataVenda:				String
				total:					float
				cliente:				int
,
				formaPagamento:			String
                
	
			Entidade Funcionario herda Pessoa
				categoria				String
____________________________________________________________________________________________	  
	3- Desenho(definicao do formulario, refinamento, identificacao dos metodos):
      ALGORITMO 
       {  
         Termo de Uso
         Opcao = Ler_Opcao() { Aceito, Nao Aceito } 
 
         se opcao == Nao Aceito 
         	abortar programa 
         se opcao == Aceito 
	         fazer
	         { 
	         	Login						

			se Palavra passe e Username existe na BD 

				chamar Menu (Pode ser do funcionario, cliente ou fornecedor dependendo do nivel de acesso)  

			senao
 
				palavra Passe incorreta 
	           
             }      
       }

*/

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function carregarComboBox(id, item)
{
    var iterar, conteudo;

    if (item !== null)
    {
        for (iterar = 0; iterar < item.length; iterar++)
            conteudo = conteudo + +'<option>' + itens [iterar] + '</option> ';

        document.getElementById(id).innerHTML = conteudo;
    }
}

function guardarFormCliente(acao)
{
    var form = document.getElementById("clienteForm");
    var erro = false;
    if (form.txtnome.value.trim().toLowerCase() == "root")
    {
        erro = true;
        alert("Nome Inválido");
    } else if (form.txtnome.value.trim().toLowerCase() == "admin")
    {
        erro = true;
        alert("Nome Inválido");
    } else if (form.txtnome.value == "")
    {
        erro = true;
        alert("Nome usuario Inválido");
    } else if (form.senha_usuario.value == "")
    {
        erro = true;
        alert("Senha Inválida");
    } else if (form.txtData_nascimento.value == "")
    {
        erro = true;
        alert("Data de Nascimento Inválida");
    }
    if (erro)
        return;
    form.action = acao;
    form.submit();
}
function eliminarFormCliente(acao)
{
    var form = document.getElementById("clienteForm");
    form.action = acao;
    form.mehod = "DELETE";
    alert(form)
    form.submit();
}
function guardarFormFuncionario(acao)
{
    var form = document.getElementById("funcioanrioForm");
    var erro = false;
    if (form.txtnome.value.trim().toLowerCase() == "root")
    {
        erro = true;
        alert("Nome Inválido");
    } else if (form.txtnome.value.trim().toLowerCase() == "admin")
    {
        erro = true;
        alert("Nome Inválido");
    } else if (form.txtnome.value == "")
    {
        erro = true;
        alert("Nome usuario Inválido");
    } else if (form.senha_usuario.value == "")
    {
        erro = true;
        alert("Senha Inválida");
    } else if (form.txtData_nascimento.value == "")
    {
        erro = true;
        alert("Data de Nascimento Inválida");
    }
    if (erro)
        return;
    form.action = acao;
    form.mehod = "POST"
//    alert(acao);
    form.submit();
}

function guardarFormProduto(acao)
{
    var form = document.getElementById("produtoForm");

    form.action = acao;
    form.mehod = "POST";
    alert(form)
    form.submit();
}

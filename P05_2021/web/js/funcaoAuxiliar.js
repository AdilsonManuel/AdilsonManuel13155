/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.toString();
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

function guardarCLiente(acao)
{
    var form = document.getElementById("clienteForm");
    var erro = false;
    
    if (erro)
        return;
    form.action = acao;
    form.submit();
}

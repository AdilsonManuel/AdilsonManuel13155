/*
*	Autor: Telmo Marinho;
*	Alterado por: Euclides Mfumu;
*/

var GUARDAR = 1;
var ALTERAR = 2;

function direccionar(nomedoformulario, servlet, operacao, id) 
{
    
    if (operacao == GUARDAR)
        document.getElementById(""+nomedoformulario+"").action= servlet+"?comando=guardar";
    else
        document.getElementById(""+nomedoformulario+"").action= servlet+"?comando=alterar" + "&txtCodigo=" + id;
    document.getElementById(""+nomedoformulario+"").submit();
    
}
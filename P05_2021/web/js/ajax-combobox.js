/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// carrega combos categorias quando a página é carregada
function onloadComboPortifolio() {
    autoCarregarComboPortifolioPai("cbCategoria1", "");
    autoCarregarComboPortifolioFilho("cbCategoria1", "cbCategoria2");
    autoCarregarComboPortifolioFilho("cbCategoria2", "cbCategoria3");
    autoCarregarComboPortifolioFilho("cbCategoria3", "cbCategoria4");
}

function autoCarregarComboPortifolioPai(idCb1, codigoPai)
{
    //console.log("autoCarregarComboPortifolioPai");
    var cb1 = $('#' + idCb1);
    var param1 = {
        codigoPai: codigoPai,
        tabela: "portifolio"
    };
    carregarCOMBO("TabelaServlet", param1, cb1);
}

function autoCarregarComboPortifolioFilho(idCb1, idCb2)
{
    //console.log("autoCarregarComboPortifolioFilho");
    var cb2 = $('#' + idCb2);
    var param2 = {
        codigoPai: $('#' + idCb1).val(),
        tabela: "portifolio"
    };
    carregarCOMBO("TabelaServlet", param2, cb2);
}


// carregar combos  categorias onChange 
$(document).ready(function ()
{
    $('#cbCategoria1').change(function () {
        autoCarregarComboPortifolioFilho("cbCategoria1", "cbCategoria2");
        autoCarregarComboPortifolioFilho("cbCategoria2", "cbCategoria3");
        autoCarregarComboPortifolioFilho("cbCategoria3", "cbCategoria4");
    });
    $('#cbCategoria2').change(function () {
        autoCarregarComboPortifolioFilho("cbCategoria2", "cbCategoria3");
        autoCarregarComboPortifolioFilho("cbCategoria3", "cbCategoria4");
    });
     $('#cbCategoria3').change(function () {
        autoCarregarComboPortifolioFilho("cbCategoria2", "cbCategoria3");
    });
});


function carregarCOMBO(servlet, param, cmb) {
    $.ajax(
            {
                type: 'GET',
                url: servlet,
                data: param,
                async: false,
                statusCode: {
                    404: function () {
                        alert('Pagina não encontrada');
                    },
                    500: function () {
                        alert('Erro no servidor');
                    }
                },
                success: function (dados) {
                    $(cmb).empty();
                    var pegardados = dados.split(";");
                    for (var i = 0; i < pegardados.length - 1; i++) {
                        var codigo = pegardados[i].split("-")[0];
                        var designacao = pegardados[i].split("-")[1];
                        if (i === 0)
                        {
                            $(cmb).append('<option value="' + codigo + '" selected >' + designacao + '</option>');
                            i + 1;
                        } else
                        {
                            $(cmb).append('<option value=' + codigo + '>' + designacao + '</option>');
                        }
                    }
                }
                , beforeSend: function () {
                    $(cmb).empty();
                    $(cmb).append('<option value="">--Carregando--</option>');
                },
                complete: function () {
                    console.log('ajax acabou');
                }
            });
}
//funções para alterar dinamicamente o conteúdo de uma combo box, com 
//base na seleção feita na combo box pai

function getObject(objectId) 
{
    if (document.all && !document.getElementById)
        return document.all(objectId);
    else
        return document.getElementById(objectId);
}


function selectChange(control, controlToPopulate, ItemArray, GroupArray, CodArray)
{
    var myEle;
    var x;
    var arrItemsValue;
    ctrlTopotulate = getObject(controlToPopulate);
    ctrl = getObject(control);

    for (var q = ctrlTopotulate.options.length; q >= 0; q--)
        ctrlTopotulate.options[q] = null;
    myEle = document.createElement("option");
    ctrlTopotulate.appendChild(myEle);
    myEle.value = '';
    myEle.text = "-- escolha --";
    myEle.selectedIndex = "0";
    myEle.disabled = true;


    for (x = 0; x < ItemArray.length; x++)
    {
        if (GroupArray[x] == ctrl.options[ctrl.selectedIndex].value)
        {
            myEle = document.createElement("OPTION");

            ctrlTopotulate.appendChild(myEle);
            myEle.value = CodArray[x];
            myEle.text = ItemArray[x];
        }
    }
    ctrlTopotulate.onchange();
}


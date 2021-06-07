//Escreve a data do sistema somente em Portugu�s

var d=new Date()
var weekDay=new Array("Domingo","Segunda-feira","Ter&ccedil;a-feira","Quarta-feira","Quinta-feira","Sexta-feira","S&aacute;bado")
var monthName=new Array("Janeiro","Fevereiro","Mar&ccedil;o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro")
document.write(weekDay[d.getDay()] + ", ")
document.write( d.getDate() + " de ")
document.write(monthName[d.getMonth()] + " de ")
document.write(d.getFullYear())


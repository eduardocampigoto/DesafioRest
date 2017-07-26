var lista = "";
$(document).ready(function() {
	$("input").focus(function(){
		$(this).css("background-color","#FFFFFF");
		$('#alerta').slideUp().html("");
	});
	
	$('#cnpj').mask("00.000/0000-00");
	
	$('#btBuscar').click(function(){
		buscarCliente($('#busca').val());
	});
	
});


function mostraMensagem(tipo, msg){
	if(tipo == "alerta"){
		$('#alerta').css("background-color","#ffcccc");
	}else if(tipo == "sucesso"){
		$('#alerta').css("background-color","#ddffcc");
	};
	$('#alerta').html(msg);
	$('#alerta').slideDown();
	
}

function adicionarCliente() {
	var cliente = JSON.stringify(getCliente());
	if (validarFormularios()) {
		$.ajax({
			url : "rest/cliente/",
			method : "POST",
			data : cliente,
			success : function(msg) {
					mostraMensagem("sucesso",msg);
					$('#alerta').delay(3000).slideUp();	
					carregarPagina('index.html');
					
			},
			error : function(msg) {
				mostraMensagem("alerta",msg);

			}
		});
	} else {
		mostraMensagem("alerta","Os campos em vermelho precisam ser preenchidos \n para os dados serem enviados ! ");
	}
}

function buscarCliente(valor) {

if (valor != ""){
	$.ajax({
		url : "rest/cliente/" + valor,
		method : "GET",
		success : function(lista) {
			if(lista != undefined && lista != null && lista !=""){				
				popularListaCliente(lista);
			}else{
				mostraMensagem("alerta"," Não foram encontrados clientes com o valor informado!");
			}
		},
		error : function(msg) {
			
		}

	});
}else{
	mostraMensagem("alerta","A busca não pode estar vazia");
	
}

}



function editarCliente() {
	cliente = JSON.stringify(getCliente());
	if(validarFormularios()){
	$.ajax({
		url : "rest/cliente/",
		method : "PUT",
		data: cliente,
		success : function(msg) {
			mostraMensagem("sucesso", msg);
			$('#alerta').delay(3000).slideUp();
			carregarPagina('index.html');
		},
		error : function(msg) {
			mostraMensagem("alerta", msg);
			
		}

	});
}
}


function excluirCliente(id) {

	$.ajax({
		url : "rest/cliente/id/" + id,
		method : "DELETE",
		success : function(msg) {
				mostraMensagem("sucesso", msg);
				carregarPagina('index.html');			
		},
		error : function(msg) {
			mostraMensagem("alerta", msg);
		}

	});
}

function carregarPagina(pagina){
	$.ajax({
		url: pagina,
		success : function(pagina) {
			$('body').html(pagina);
		},
		error : function() {
			
		}

	});
	
}

function validarFormularios() {
	var retorno = true;
	

	if ($('#nome').val() == "") {
		$('#nome').css("background-color", "#ffcccc");
		retorno = false;
	}

	var email = $('#email').val();
	var emailExpressao=/^.+@.+\..{2,}$/;
	var charsInvalidos= /[\(\)\<\>\,\;\:\\\/\"\[\]]/;
	if ($('#email').val() == "" || !(emailExpressao.test(email))||email.match(charsInvalidos)) {
		$('#email').css("background-color", "#ffcccc");
		$('#email').attr("title","O email deve obedecer o formato usuario@domínio.com");
		retorno = false;
	}

	if ($('#cnpj').val() == "") {
		$('#cnpj').css("background-color", "#ffcccc");
		retorno = false;
	}
	
	
	

	return retorno;

}

function getCliente() {
	return {
		"id" : $('#id').val(),
		"name" : $('#nome').val(),
		"email" : $('#email').val(),
		"cnpj" : $('#cnpj').val(),
		"comment" : $('#comentario').val()
	}
}

function setCliente(id) {
	
	this.lista.forEach(function(obj){
		if(obj.id == id){
			cliente = obj;
		}
	});
		$('#btAdicionar').remove();
		$('#btEditar').css('display','block');
		$('#btCancelar').css('display','block');
		$('#id').css('display','block')
		$('#id').val(cliente.id);
		$('#nome').val(cliente.name);
		$('#email').val(cliente.email);
		$('#cnpj').val(cliente.cnpj);
		$('#comentario').val(cliente.comment);
	
}






function popularListaCliente(lista) {
	this.lista = lista;
	$('#listaClientes').html("");
	lista.forEach(function(cliente){	
	$('#listaClientes').append(
			"<tr><td  rows='1'>" + cliente.id + "</td><td>" + cliente.name
					+ "</td>" + "<td>" + cliente.cnpj + "</td><td>"
					+ cliente.email + "</td><td>" + cliente.comment
					+ "</td><td>" + "<a type='button' class='btn btForm glyphicon glyphicon-edit' id='btEditarLista' style='color: #010101' onclick='javascript: setCliente("+cliente.id+")'> Editar</a>"+
					"<a type='button' class='btn btForm glyphicon glyphicon-remove' id='btExcluirLista' style='color: #101010' onclick='javascript: excluirCliente("+cliente.id+")'> Excluir</a>" + "</td></tr>");

	});
}

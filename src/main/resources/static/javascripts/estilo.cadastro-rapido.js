$(function() {

	var modal = $('#modalCadastroRapidoEstilo');
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() }); //desabilita o submeter do form quando da enter
	
	var url = form.attr('action'); // recupera a url que gera
	var inputNomeEstilo = $('#nomeEstilo');
	
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose); 
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	function onModalClose(){
		inputNomeEstilo.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeEstilo = inputNomeEstilo.val().trim();
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 'nome': nomeEstilo }),
			error: onErroSalvandoEstilo,
			success: onEstiloSalvo
		});
	}
	
	function onErroSalvandoEstilo(){
		console.log(arguments);
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value ='+ estilo.id + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.id);
		modal.modal('hide');
	}

	
});
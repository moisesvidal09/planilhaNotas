//valida o formulario
function validarFormulario() {
	var campoNome = document.getElementById('formulario:nomeAluno');
	var campoMatricula = document.getElementById('formulario:matricula');
	var campoNota = document.getElementById('formulario:notaAluno');
	var campoSituacao = document.getElementById('formulario:situacao');
	var campoDataAvaliacao = document
			.getElementById('formulario:dataAvaliacao');

	if (campoNome.value.length == 0) {
		alert('O campo nome deve ser preenchido.');
		campoNome.focus();
		return false;
	} else if (campoNome.value.search(/^[a-záàâãéèêíïóôõöúçñ ]+$/i)) {
		alert('O campo nome não pode conter numeros.');
		campoNome.focus();
		return false;
	} else if (campoMatricula.value.length == 0) {
		alert('O campo matricula deve ser preenchido.');
		campoMatricula.focus();
		return false;
	} else if (campoMatricula.value.search(/[0-9]+/g)) {
		alert('O campo matricula não pode conter letras.');
		campoMatricula.focus();
		return false;
	} else if (campoNota.value.length == 0) {
		alert('O campo nota deve ser preenchido.');
		campoNota.focus();
		return false;
	} else if (campoNota.value < 0 || campoNota.value > 10) {
		alert('O campo nota deve ser maior que 0(zero) e menor que 10(Dez).');
		campoNota.focus();
		return false;
	} else if (campoSituacao.value == "") {
		alert('Selecione uma situação.');
		campoSituacao.focus();
		return false;
	} else if (campoDataAvaliacao.value
			.search(/^(?:(31)(\D)(0?[13578]|1[02])\2|(29|30)(\D)(0?[13-9]|1[0-2])\5|(0?[1-9]|1\d|2[0-8])(\D)(0?[1-9]|1[0-2])\8)((?:1[6-9]|[2-9]\d)?\d{2})$|^(29)(\D)(0?2)\12((?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$/g)) {
		alert('Data inválida!');
		campoDataAvaliacao.focus();
		return false;
	}

	return true;

}


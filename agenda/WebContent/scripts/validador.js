/**
 * Validação de furmulário
 */
function validar(){
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if (nome === ""){
		alert('Tem de preencher o campo Nome')
		frmContato.nome.focus()
		return false
	}
	else if (fone === ""){
		alert('Tem de preencher o campo fone')
		frmContato.fone.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}
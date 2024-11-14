/**
 *  Confrimacao de exclusao d um contato
 * @author Guilherme Centeio
 */
function confirmar(idcon){
	let resposta = confirm("Confrimar a exclusao deste contato?")
	if (resposta===true){
		//alert(idcon)
		window.location.href = "delete?idcon=" + idcon
	}
}
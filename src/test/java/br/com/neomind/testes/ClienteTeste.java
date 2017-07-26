package br.com.neomind.testes;
import br.com.neomind.exception.GlobalException;
import br.com.neomind.rest.ClienteRest;
import junit.framework.TestCase;

public class ClienteTeste extends TestCase {
	private ClienteRest rest = new ClienteRest();

	public void testDeveRetornarAdicionadoComSucesso() throws GlobalException {
		String cliente = "{ \"id\": 0, \"name\": \"fornec0\", \"email\": \"fornec0@loripsom\", \"comment\": \"teste comentario 0\", \"cnpj\": \"00.000/0000-00\" }";
		String retorno = "\"fornec0 adicionado com sucesso !\"";
		assertEquals(retorno, rest.adicionarCliente(cliente));
	
	}
	public void testEditarClienteDeveRetornarNaoEncontrado() throws GlobalException {
		String parametro = "{ \"id\": 7, \"name\": \"fornec lorimospm\", \"email\": \"fornec@loripsom\", \"comment\": \"loreipsum\", \"cnpj\": \"00.000/0000-00\" }";
		String retorno = "Cliente não encontrado";		
		assertEquals(retorno, rest.editarCliente(parametro));
	}
	
	public void testEditarClienteRetornaSucesso() throws GlobalException {
		String cliente = "{ \"id\": 0, \"name\": \"fornec lorimospm\", \"email\": \"fornec@loripsom\", \"comment\": \"loreipsum\", \"cnpj\": \"00.000/0000-00\" }";
		rest.adicionarCliente(cliente);
		String parametro = "{ \"id\": 1, \"name\": \"fornec lorimospm\", \"email\": \"fornec@loripsom\", \"comment\": \"loreipsum\", \"cnpj\": \"00.000/0000-00\" }";
		String retorno = "fornec lorimospm editado com sucesso !";
		assertEquals(retorno, rest.editarCliente(parametro));
	}
	
	public void testExcluirDeveRetornarTrue() throws GlobalException {
		int parametro = 1;
		boolean retorno = true;
		assertEquals(retorno, rest.excluirCliente(parametro));
	}
	
	public void testExcluirDeveRetornarFalse() throws GlobalException {
		int parametro = 2;
		boolean retorno = true;
		assertEquals(retorno, rest.excluirCliente(parametro));
	}

	
	
}

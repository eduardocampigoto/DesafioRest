package br.com.neomind.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.neomind.exception.GlobalException;
import br.com.neomind.objetos.Cliente;
import br.com.neomind.servico.ClienteServico;

@Path("/cliente")
public class ClienteRest extends UtilRest {

	private Cliente cliente;
	private ClienteServico servico;

	public ClienteRest() {
		cliente = new Cliente();
		servico = new ClienteServico();
	}

	@POST
	@Consumes("application/*")
	public String adicionarCliente(String clienteParam) throws GlobalException {
		try {
				cliente = getObjectMapper().readValue(clienteParam, Cliente.class);
				String msg = servico.adicionarCliente(cliente);
				return this.buildResponse(msg);

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw new GlobalException("Erro ao adicionar cliente!");
		}

	}

	@GET
	@Path("{clienteParam}")
	@Produces({MediaType.APPLICATION_JSON})
	public String buscarCliente(@PathParam("clienteParam") String clienteParam)
			throws GlobalException {
		try {
			List<Cliente> listaCliente = servico.buscarCliente(clienteParam);
			return this.buildResponse(listaCliente);
		} catch (Exception e) {
			e.getMessage();
			throw new GlobalException("Erro ao buscar cliente!");
		}
	}

	

	@PUT
	public String editarCliente(String clienteParam) throws GlobalException {

		try {
			cliente = getObjectMapper().readValue(clienteParam, Cliente.class);
			String msg = servico.editarCliente(cliente);
			return msg;

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw new GlobalException("Erro ao editar cliente!");
		}

	}

	@DELETE
	@Path("id/{id}")
	public boolean excluirCliente(@PathParam("id") int id)
			throws GlobalException {
		try {
			cliente.setId(id);
			boolean resposta = servico.excluirCliente(cliente);
			return resposta;

		} catch (Exception e) {
			e.getMessage();
			throw new GlobalException("Erro ao excluir cliente!");
		}

	}

}

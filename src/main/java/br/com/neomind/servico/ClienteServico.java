package br.com.neomind.servico;

import java.util.ArrayList;
import java.util.List;

import br.com.neomind.exception.GlobalException;
import br.com.neomind.objetos.Cliente;
import br.com.neomind.persistencia.ClientePersist;

public class ClienteServico {
	private List<Cliente> listaClientes;
	
	public ClienteServico() {
		listaClientes = new ArrayList<Cliente>(); 
	}

	public String adicionarCliente(Cliente novoCliente) throws GlobalException {
			String  resposta = ClientePersist.adicionarCliente(novoCliente);
			return resposta;
	}

	public List<Cliente> buscarCliente(String buscar) throws GlobalException {
			listaClientes = ClientePersist.buscarCliente(buscar); 
			return listaClientes;
	}
	
	public String editarCliente(Cliente cliente) throws GlobalException {
		String resposta  = ClientePersist.editarCliente(cliente);
		return resposta;
	}

	public boolean excluirCliente(Cliente cliente) throws GlobalException {
		boolean resposta  = ClientePersist.excluirCliente(cliente);
		return resposta;
	}

}

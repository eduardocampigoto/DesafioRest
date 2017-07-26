package br.com.neomind.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.neomind.exception.GlobalException;
import br.com.neomind.objetos.Cliente;

public final class ClientePersist {

	private static List<Cliente> listaClientes = new ArrayList<Cliente>();
	private static int id = 0;
	public static String adicionarCliente(Cliente novoCliente)
			throws GlobalException {
		String resposta ="";
		try {
			if(novoCliente != null && !novoCliente.equals("")){
				id++;
				novoCliente.setId(id);								
			listaClientes.add(novoCliente);			
				resposta = novoCliente.getName() + " adicionado com sucesso !";			
			}else{				
				resposta ="Falha ao adicionar cliente";			
			}
			return resposta;
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw new GlobalException("Erro ao adicionar cliente "+ novoCliente.getName());
		}
	}

	public static List<Cliente> buscarCliente(String buscar)
			throws GlobalException {
		try {
			List<Cliente> clientesEncontrados = new ArrayList<Cliente>();
			Iterator<Cliente> iteratorClientes = listaClientes.iterator();
			while (iteratorClientes.hasNext()) {
				Cliente compara = iteratorClientes.next();
				String comparaId = Integer.toString(compara.getId());
			  if(comparaId.equals(buscar)|| compara.getName().contains(buscar)
						|| compara.getCnpj().contains(buscar)	|| compara.getEmail().contains(buscar)
						|| compara.getComment().contains(buscar)) {
					
					clientesEncontrados.add(compara);
				}
				
				
			}
			return clientesEncontrados;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw new GlobalException("Erro ao buscar cliente com o valor "+ buscar + "!");
		}

	}

	

	public static String editarCliente(Cliente cliente) throws GlobalException {
		try {
			String resposta="Cliente não encontrado";
			Iterator<Cliente> iteratorClientes = listaClientes.iterator();
			
			while (iteratorClientes.hasNext()) {
				Cliente compara = iteratorClientes.next();
				
				if (compara.getId() == cliente.getId()) {					
					compara.setName(cliente.getName());
					compara.setCnpj(cliente.getCnpj());
					compara.setEmail(cliente.getEmail());
					compara.setComment(cliente.getComment());
					resposta = cliente.getName() + " editado com sucesso !";
				}
			}
			
			return resposta;
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			throw new GlobalException("Falha ao editar cliente!");
		}

	}

	public static boolean excluirCliente(Cliente cliente)
			throws GlobalException {

		try {
			Iterator<Cliente> iteratorClientes = listaClientes.iterator();
			while (iteratorClientes.hasNext()) {
				Cliente compara = iteratorClientes.next();
				if (compara.getId() == cliente.getId()) {
					iteratorClientes.remove();
				}
			}
			return true;
		} catch (Exception e) {
			e.getMessage();
			throw new GlobalException("Erro ao excluir cliente com o ID"
					+ cliente.getId());
		}

	}

}

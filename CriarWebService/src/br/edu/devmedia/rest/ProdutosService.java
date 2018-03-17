package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.net.devmedia.dao.ProdutoDAO;
import br.net.devmedia.entidade.Produto;

@Path("/produtos")
public class ProdutosService {

	private ProdutoDAO produtoDAO;
	
	@PostConstruct
	private void init() {
		produtoDAO = new ProdutoDAO();	
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProdutos(){
		List<Produto> lista = null;
		try {
			lista = produtoDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	/**
	 * Criação de um novo produto via serviço.  
	 * @param produto
	 * @return
	 */
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduto(Produto produto) {
		String msg = "";
		System.out.println(produto.getDescricao());
	
		try {
			produtoDAO.salvar(produto);
			
			msg= "Produto adicionado com sucesso";
		} catch (Exception e) {
			msg = "Erro ao adicionar um produto";
			e.printStackTrace();
		}
		
		return msg;	
	}
	
	@GET
	@Path("/get/{codigo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscarPorI(@PathParam("codigo") Long codigoProduto) {
		Produto produto = null;
		try {
			produto = produtoDAO.buscarPorId(codigoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}
}

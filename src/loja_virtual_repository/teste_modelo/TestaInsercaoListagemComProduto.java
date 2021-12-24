package loja_virtual_repository.teste_modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import loja_virtual_repository.ConnectionFactory;
import loja_virtual_repository.dao.ProdutoDAO;
import loja_virtual_repository.modelo.Produto;

public class TestaInsercaoListagemComProduto {

	public static void main(
			String[] args) throws SQLException {
		Produto s = new Produto("Sapato","Marrom");
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(s);
			
			List<Produto> produtos = produtoDao.listar();
			
			produtos.stream().forEach(p->System.out
					.println(p));
		}
		
		
	}
}

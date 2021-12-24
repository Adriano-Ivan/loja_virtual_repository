package loja_virtual_repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import loja_virtual_repository.modelo.Categoria;
import loja_virtual_repository.modelo.Produto;

public class ProdutoDAO {

	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection=connection;
	}
	
	public void salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ){
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					Integer id = rst.getInt(1);
					produto.setId(id);
				}
			}
		}
	}
	
	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Produto p = new Produto(rst.getInt(1),rst.getString(2),rst.getString(3));
					
					produtos.add(p);
				}
			}
		}
		
		return produtos;
	}
	
	public List<Produto> listarPorCategoria(Categoria c) throws SQLException{
		List<Produto> produtos = new ArrayList<Produto>();
		
		
		String sql = "SELECT ID, NOME, DESCRICAO, CATEGORIA_ID FROM PRODUTO WHERE CATEGORIA_ID = ?";
		
		try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
			pstm.setInt(1, c.getId());
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					String nome = rst.getString("nome");
					Integer id = rst.getInt("id");
					String descricao = rst.getString("descricao");
					Integer categoria_id = rst.getInt("categoria_id");
					
					Produto produto = new Produto(id, categoria_id, nome, descricao);
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
}

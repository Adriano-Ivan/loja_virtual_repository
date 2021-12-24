package loja_virtual_repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loja_virtual_repository.modelo.Categoria;
import loja_virtual_repository.modelo.Produto;

public class CategoriaDAO {

	private Connection connection;
	public CategoriaDAO(Connection connection) {
		this.connection=connection;
	}
	
	public List<Categoria> listar() throws SQLException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		System.out
				.println("EXECUTANDO LISTAGEM DE CATEGORIAS");
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Integer id = rst.getInt("id");
					String nome = rst.getString("nome");
					
					Categoria c = new Categoria(id,nome);
					categorias.add(c);
				}
				
			}
		}
		return categorias;
	}
	public List<Categoria> listarComProduto() throws SQLException{
		Categoria ultima =null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT C.ID, C.NOME,P.ID,P.NOME,P.DESCRICAO FROM CATEGORIA C INNER JOIN"
				+" PRODUTO P ON C.ID = P.CATEGORIA_ID";
		
		try(PreparedStatement pstm = this.connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					if(ultima==null || !ultima.getNome().equals(rst.getString(2))) {
						Integer id = rst.getInt("id");
						String nome = rst.getString("nome");
						
						Categoria categoria = new Categoria(id,nome);
						ultima=categoria;
						categorias.add(categoria);
						
					}
					Produto produto = new Produto(rst.getInt(3),rst.getString(4),rst.getString(5));
					
					ultima.adicionar(produto);
				}
				
			}
		}
		return categorias;
	}
}

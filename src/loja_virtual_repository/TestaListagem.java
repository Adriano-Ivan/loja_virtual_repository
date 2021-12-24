package loja_virtual_repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestaListagem {

	public static void main(
			String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.recuperarConexao();
		PreparedStatement stm= conn.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		stm.execute();
		
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			
			System.out
					.println("ID: "+id);
			System.out
					.println("NOME: "+nome);
			
			System.out.println("DESCRIÇÃO: "+descricao);
		}
		conn.close();
	}
}

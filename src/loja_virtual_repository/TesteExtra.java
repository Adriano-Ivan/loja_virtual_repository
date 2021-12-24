package loja_virtual_repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteExtra {

	public static void main(
			String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/veiculos_suspeitos?useTimezone=true&serverTimezone=UTC","root","admin");
	
		Statement stm = conn.createStatement();
		
		stm.execute("select * from bots_telegram");
		
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			String nome = rst.getString("nome");
			String token =rst.getString("token_telegram");
			String chat_id=rst.getString("chat_id");
			
			System.out
					.println("NOME: "+nome);
			
			System.out
					.println("TOKEN: "+token);
			
			System.out
					.println("CHAT ID: "+chat_id);
		}
	}
}

package loja_virtual_repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(
			String[] args) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.recuperarConexao();
		
		Statement stm = conn.createStatement();
		boolean resultado =
		stm.execute("INSERT INTO produto(nome,descricao) values('chocolate belga', 'delicioso e gourmet chocolate belga')",
				Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rst = stm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out
					.println("ID gerado no MySQL: "+id);
		}
	}
}

package loja_virtual_repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaExclusao {

	public static void main(
			String[] args) throws SQLException {
		Integer id = 2;
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.recuperarConexao();
		
		PreparedStatement stm = conn.prepareStatement("DELETE FROM PRODUTO WHERE id > ?");
		stm.setInt(1, id);
		stm.execute();
		
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out
				.println("LINHAS MODIFICADAS: "+linhasModificadas);
	}
}

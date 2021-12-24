package loja_virtual_repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoParametro {

	public static void main(
			String[] args)
			throws SQLException {
		// String nome = "chocolate belga";

		//		String descricao = "delicioso e gourmet chocolate belga";
		ConnectionFactory factory = new ConnectionFactory();

		try (Connection conn = factory
				.recuperarConexao()) {

			conn.setAutoCommit(false);

			try (
					PreparedStatement stm = conn
							.prepareStatement(
									"INSERT INTO produto(nome,descricao) values(?,?)",
									Statement.RETURN_GENERATED_KEYS)) {
				adicionarRegistro(
						"Armário - 2",
						"Madeira maciça - 2",
						stm);
				adicionarRegistro(
						"Smart TV 2",
						"50 POLEGADAS - 2",
						stm);

				conn.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out
						.println(
								"ROLLBACK EXECUTADO.");
				conn.rollback();
			}
		}
		// conn.close();
	}

	private static void adicionarRegistro(
			String nome,
			String descricao,
			PreparedStatement stm)
			throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

//		if (nome.equals("Smart TV 2")) {
//			throw new RuntimeException(
//					"Não foi possível adicionar o produto");
//		}
		stm.execute();

		try (ResultSet rst = stm
				.getGeneratedKeys()) {

			while (rst.next()) {
				Integer id = rst
						.getInt(1);
				System.out
						.println(
								"ID gerado no MySQL: "
										+ id);
			}
		}

	}
}

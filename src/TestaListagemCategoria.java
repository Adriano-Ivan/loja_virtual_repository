import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import loja_virtual_repository.ConnectionFactory;
import loja_virtual_repository.dao.CategoriaDAO;
import loja_virtual_repository.dao.ProdutoDAO;
import loja_virtual_repository.modelo.Categoria;
import loja_virtual_repository.modelo.Produto;

public class TestaListagemCategoria {

	public static void main(
			String[] args)
			throws SQLException {

		try (Connection connection = new ConnectionFactory()
				.recuperarConexao()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO(
					connection);
			ProdutoDAO produtoDAO = new ProdutoDAO(
					connection);

			List<Categoria> listaDeCategorias = categoriaDAO
					.listarComProduto();
			listaDeCategorias.stream()
					.forEach(ct -> {
						System.out
								.println(
										ct.getNome()
												.toUpperCase());

						for (Produto p : ct
								.getProdutos()) {
							System.out
									.println(
											ct.getNome()
													+ " - "
													+ p.getNome()
													+ " - "
													+ p.getDescricao());
						}

					});
		}

	}

}

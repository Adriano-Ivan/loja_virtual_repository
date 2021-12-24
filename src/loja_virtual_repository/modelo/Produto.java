package loja_virtual_repository.modelo;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoria_id;
	
	public Produto(String nome,
			String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	public Produto(Integer id,String nome, String descricao) {
		this.id=id;
		this.nome=nome;
		this.descricao=descricao;
	}
	public Produto(Integer id,Integer categoria_id,String nome, String descricao) {
		this.id=id;
		this.nome=nome;
		this.descricao=descricao;
		this.categoria_id=categoria_id;
	}

	
	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return this.descricao;
	}

	public void setId(Integer id) {
		this.id = id;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("O produto é: %d, %s, %s", this.id, this.nome, this.descricao);
		
	}
}

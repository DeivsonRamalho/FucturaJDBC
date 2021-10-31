package dao;

import java.util.List;

import model.Endereco;

public interface EnderecoDao {

	public int getSequence();
	public void salvar(Endereco endereco);
	public void alteara(Endereco endereco);
	public void remover(int endereco);
	public Endereco pesquisar(int endereco);
	public List<Endereco>listarTodos();
}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.PessoaDao;
import model.Pessoa;
import model.util.ConexaoFuctura;

public class PessoaDaoImpl implements PessoaDao {

	ConexaoFuctura conexao = new ConexaoFuctura();
	EnderecoDaoImpl enderecoDao = new EnderecoDaoImpl();
	ContaDaoImpl contaDao = new ContaDaoImpl();

	@Override
	public void salvar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO PESSOAS(NOME, CPF, GENERO, IDADE, ID_ENDERECO, NUMERO_CONTA)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try {
			this.contaDao.salvar(pessoa.getConta());
			pessoa.getEndereco().setId(this.enderecoDao.getSequence());
			this.enderecoDao.salvar(pessoa.getEndereco());

			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, pessoa.getNome());
			fc.setString(2, pessoa.getCpf());
			fc.setString(3, pessoa.getGenero());
			fc.setInt(4, pessoa.getIdade());
			fc.setInt(5, pessoa.getEndereco().getId());
			fc.setInt(6, pessoa.getConta().getNumero());
			fc.execute();
			System.out.println("Pessoa inserida com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao inseri Pessoas no Banco de dados");
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	@Override
	public void alterar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();
		String sql = "UPDATE PESSOA SET (NOME = ?, IDADE = ?, GENERO = ?" + "WHERE CPF = ? ";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, pessoa.getNome());
			fc.setInt(2, pessoa.getIdade());
			fc.setString(3, pessoa.getGenero());
			fc.setString(4, pessoa.getCpf());
			fc.executeUpdate();
			System.out.println("Dados alterados com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar os dados da pessoa" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}
	
	public void remover(String cpf) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();
		Pessoa p = pesquisar(cpf);
		String sql = "DELETE FROM PESSOA WHERE CPF =?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, cpf);
			fc.execute();
			System.out.println("Pessoa Deletada com Seucesso");
			contaDao.remover(p.getEndereco().getId());
			enderecoDao.remover(p.getEndereco().getId());

		} catch (Exception e) {
			System.out.println("Erro ao deletar no pessoa banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}
	
	public Pessoa pesquisar(String cpf) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();
		Pessoa pessoa = new Pessoa();
		String sql = "SELETC * FROM PESSOA WHERE CPF = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, cpf);
			ResultSet rs = fc.executeQuery();
			while (rs.next()) {
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setGenero(rs.getString("GENERO"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setEndereco(this.enderecoDao.pesquisar(rs.getInt("ID_ENDERECO")));
				pessoa.setConta(this.contaDao.pesquisar(rs.getInt("NUMERO_CONTA")));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar Pessoa" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return pessoa;
	}

	@Override
	public void remover(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pessoa pesquisar(int pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> listaTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDao;
import model.Conta;
import model.util.ConexaoFuctura;

public class ContaDaoImpl implements ContaDao {

	ConexaoFuctura conexao = new ConexaoFuctura();

	public void salvar(Conta conta) {
		Connection conn = conexao.getConnection();

		String sql = "INSERT INTO CONTA(NUMERO, SALDO, LIMITE) " + "VALUES(?, ?, ?)";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setInt(1, conta.getNumero());
			fc.setDouble(2, conta.getSaldo());
			fc.setDouble(3, conta.getLimite());
			fc.execute();
			System.out.println("Conta inserida com sucesso");
		} catch (SQLException e) {

			System.out.println("Erro ao inserir conta no banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(int numero) {
		Connection conn = conexao.getConnection();
		String sql = "DELETE FROM CONTA WHERE NUMERO = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setInt(1, numero);
			fc.execute();
			System.out.println("Conta Deletada com Sucesso");

		} catch (Exception e) {
			System.out.println("Erro ao deletar conta no banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Conta conta) {
		Connection conn = conexao.getConnection();
		String sql = "UPDATE CONTA SET SALDO = ?, LIMITE = ? WHERE NUMERO= ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setDouble(1, conta.getSaldo());
			fc.setDouble(2, conta.getLimite());
			fc.setInt(3, conta.getNumero());
			fc.executeUpdate();
			System.out.println("Conta atualizada com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar conta no banco " + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}

	@Override
	public Conta pesquisar(int numero) {
		Connection conn = conexao.getConnection();
		Conta conta = new Conta();
		String sql = "SELECT * FROM CONTA WHERE NUMERO = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setInt(1, numero);
			ResultSet rs = fc.executeQuery();

			while (rs.next()) {
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));

			}

		} catch (Exception e) {
			System.out.println("Erro ao pesquisar conta" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

		return conta;
	}

	@Override
	public List<Conta> listarTodos() {
		Connection conn = conexao.getConnection();
		List<Conta> retorno = new ArrayList<Conta>();
		String sql = "SELECT * FROM CONTA";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			ResultSet rs = fc.executeQuery();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				retorno.add(conta);

			}

		} catch (Exception e) {
			System.out.println("Erro ao pesquisar Conta" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return retorno;
	}
}

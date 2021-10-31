package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EnderecoDao;
import model.Endereco;
import model.util.ConexaoFuctura;

public class EnderecoDaoImpl implements EnderecoDao {

	ConexaoFuctura conexao = new ConexaoFuctura();

	public int getSequence() {
		Connection conn = conexao.getConnection();
		Integer retorno = null;
		String sql = "SELECT S_ID_ENDERECO.NEXTVAL AS SEQUENCE FROM DUAL";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			ResultSet rs = fc.executeQuery();
			while (rs.next()) {
				retorno = rs.getInt("SEQUENCE");

			}
		} catch (Exception e) {
			System.out.println("Erro ao sequence" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
		return retorno;
	}

	public void salvar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO ENDERECO (ID_ENDERECO, RUA, NUMERO, COMPLEMENTO)" + "VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setInt(1, endereco.getId());
			fc.setString(2, endereco.getRua());
			fc.setInt(3, endereco.getNumero());
			fc.setString(4, endereco.getComplemento());
			fc.execute();
			System.out.println("Endereço inserido com sucesso");

		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereco no banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}

	/*public void alterar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		String sql = "UPDATE ENDERECO SET RUA = ?, NUMERO =?, COMPLEMENTO = ?" + "WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, endereco.getRua());
			fc.setInt(2, endereco.getNumero());
			fc.setString(3, endereco.getComplemento());
			fc.setInt(4, endereco.getId());
			fc.executeUpdate();
			System.out.println("Endereco alterado com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar endereco no banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}*/

	public Endereco pesquisar(Integer numero) {
		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();
		String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setInt(1, numero);
			ResultSet rs = fc.executeQuery();
			while (rs.next()) {
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setRua(rs.getString("RUA"));

			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar endereco - " + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
		return endereco;
	}

	public List<Endereco> listarTodos(){
		Connection conn = conexao.getConnection();
		List<Endereco> retorno = new ArrayList<Endereco>();
		String sql = "SELECT * FROM ENDERECO ";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			ResultSet rs = fc.executeQuery();
			while(rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setRua(rs.getString("RUA"));
				retorno.add(endereco);
			}
		}catch(Exception e) {
			System.out.println("Erro ao pesquisar endereço - " + e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}
		return retorno;
	}

	public void alteara(Endereco endereco) {
		// TODO Auto-generated method stub
		Connection conn = conexao.getConnection();
		String sql = "UPDATE ENDERECO SET RUA = ?, NUMERO =?, COMPLEMENTO = ?" + "WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement fc = conn.prepareStatement(sql);
			fc.setString(1, endereco.getRua());
			fc.setInt(2, endereco.getNumero());
			fc.setString(3, endereco.getComplemento());
			fc.setInt(4, endereco.getId());
			fc.executeUpdate();
			System.out.println("Endereco alterado com Sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar endereco no banco" + e.getMessage());

		} finally {
			conexao.fecharConexao(conn);
		}
	}
	

	@Override
	public void remover(int endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco pesquisar(int endereco) {
		// TODO Auto-generated method stub
		return null;
	}
}

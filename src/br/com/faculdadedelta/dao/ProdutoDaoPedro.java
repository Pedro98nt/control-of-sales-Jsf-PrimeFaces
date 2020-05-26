package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.ProdutoPedro;

public class ProdutoDaoPedro {

	public void incluir(ProdutoPedro produtoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO produtos (desc_produto) VALUES(?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produtoPedro.getProduto().trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ProdutoPedro produtoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE produtos SET desc_produto =? WHERE id_produto=?  ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, produtoPedro.getProduto().trim());
			ps.setLong(2, produtoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(ProdutoPedro produtoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM produtos WHERE id_produto=? ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, produtoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	private ProdutoPedro popularProduto(ResultSet rs) throws Exception {
		ProdutoPedro produtoPedro = new ProdutoPedro();

		if (rs.next()) {
			produtoPedro.setId(rs.getLong("id_produto"));
			produtoPedro.setProduto(rs.getString("desc_produto"));

		}

		return produtoPedro;

	}

	public List<ProdutoPedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_produto,desc_produto FROM  produtos  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProdutoPedro> listaRetorno = new ArrayList<ProdutoPedro>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ProdutoPedro produtoPedro = popularProduto(rs);
			listaRetorno.add(produtoPedro);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}

	public ProdutoPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_produto,desc_produto FROM  produtos WHERE id_produto=?  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProdutoPedro retorno = new ProdutoPedro();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ps.setLong(1, id);

			if (rs.next()) {
				retorno = popularProduto(rs);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return retorno;

	}

}

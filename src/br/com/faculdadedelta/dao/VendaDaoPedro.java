package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.ProdutoPedro;
import br.com.faculdadedelta.modelo.VendaPedro;

public class VendaDaoPedro {

	public void incluir(VendaPedro vendaPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO vendas(desc_produto,id_produto,valor_produto)VALUES(?,?,?)";
		PreparedStatement ps = null;
		
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vendaPedro.getDescProduto().trim());
			ps.setLong(2, vendaPedro.getProduto().getId());
			ps.setDouble(3, vendaPedro.getValor());
		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(VendaPedro vendaPedro ) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE vendas"
				+" SET desc_produto=?,"
				+"id_produto=?,"
				+"valor_produto=?,"
				+"WHERE id_venda=?  ";
		PreparedStatement ps = null;
		try {
			ps= conn.prepareStatement(sql);
			
			ps.setString(1, vendaPedro.getDescProduto());
			ps.setLong(2,vendaPedro.getProduto().getId());
			ps.setDouble(3, vendaPedro.getValor());
			ps.setLong(4, vendaPedro.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
		
	}
	
	public void excluir(VendaPedro vendaPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM vendas WHERE id_venda=?  ";
		PreparedStatement ps = null;
		
		try {
			
			ps=conn.prepareStatement(sql);
			
			ps.setLong(1, vendaPedro.getId());
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public List<VendaPedro> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT  "
				+ "v.id_venda AS  idVenda "
				+"v.desc_produto AS descVenda   "
				+"v.valor_produto AS valorProduto  "
				+" p.id_produto AS idProduto  "
				+ "p.desc_produto AS ProdutoPedro"
				+"  FROM vendas v"
				+" INNER JOIN produtos p ON v.id_venda =id_produto";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<VendaPedro> listaRetorno = new ArrayList<VendaPedro>();
		try {
			
			ps=  conn.prepareStatement(sql);
			rs= ps.executeQuery();
			
			while(rs.next()) {
				VendaPedro vendaPedro = new VendaPedro();
				vendaPedro.setId(rs.getLong("idVenda"));
				vendaPedro.setDescProduto(rs.getString("descVenda"));
				vendaPedro.setValor(rs.getDouble("valorProduto"));
				
				ProdutoPedro produtoPedro = new ProdutoPedro();
				
				produtoPedro.setId(rs.getLong("idProduto"));
				produtoPedro.setProduto(rs.getString("ProdutoPedro"));
				
				vendaPedro.setProduto(produtoPedro);
				listaRetorno.add(vendaPedro);
			}
		
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	}finally {
		Conexao.fecharConexao(conn, ps, rs);
	}
	
		
		return listaRetorno;
		
	}
	
	
}

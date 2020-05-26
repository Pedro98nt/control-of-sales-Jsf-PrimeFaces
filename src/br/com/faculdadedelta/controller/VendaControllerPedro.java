package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VendaDaoPedro;
import br.com.faculdadedelta.modelo.ProdutoPedro;
import br.com.faculdadedelta.modelo.VendaPedro;

@ManagedBean
@SessionScoped
public class VendaControllerPedro {

	private VendaPedro vendaPedro  = new VendaPedro();
	private VendaDaoPedro dao = new VendaDaoPedro();
	private ProdutoPedro produtoSelecionado = new ProdutoPedro();
	
	public VendaPedro getVenda() {
		return vendaPedro;
	}

	public void setVenda(VendaPedro vendaPedro) {
		this.vendaPedro = vendaPedro;
	}

	public ProdutoPedro getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(ProdutoPedro produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void limparCampo() {
		vendaPedro = new VendaPedro();
		produtoSelecionado = new ProdutoPedro();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		
		try {
		if(vendaPedro.getId()==null) {
			vendaPedro.setProduto(produtoSelecionado);
				dao.incluir(vendaPedro);
				limparCampo();
				exibirMensagem("inlusão realizada com sucesso");
			} else {
				dao.alterar(vendaPedro);
				limparCampo();
				exibirMensagem("alteração realizada com sucesso");
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "cadastroVenda.xhtml";
		
	}
	
	public String editar() {
		produtoSelecionado = vendaPedro.getProduto();
		return "cadastroVenda.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(vendaPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
	
		return "listaVenda.xhtml";
		
	}
	
	public List<VendaPedro> getLista(){
	List<VendaPedro> listaRetorno = new ArrayList<VendaPedro>();
	
	try {
		listaRetorno = dao.listar();
		
	} catch (Exception e) {
		e.printStackTrace();
		exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
	}
		
		return listaRetorno;
		
	}
	
	
}

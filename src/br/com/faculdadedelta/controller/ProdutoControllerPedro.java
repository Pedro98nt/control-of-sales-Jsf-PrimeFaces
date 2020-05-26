package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProdutoDaoPedro;
import br.com.faculdadedelta.modelo.ProdutoPedro;
@ManagedBean
@SessionScoped
public class ProdutoControllerPedro {

	
	private ProdutoPedro produtoPedro = new ProdutoPedro();
	private ProdutoDaoPedro dao = new ProdutoDaoPedro();

	public ProdutoPedro getProduto() {
		return produtoPedro;
	}

	public void setProduto(ProdutoPedro produtoPedro) {
		this.produtoPedro = produtoPedro;
	}
	
	public void limparCampo() {
		produtoPedro = new ProdutoPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		
		if(produtoPedro.getId()==null) {
				dao.incluir(produtoPedro);
				limparCampo();
				exibirMensagem("inclusão realizada com sucesso");
			} else {
				dao.alterar(produtoPedro);
				limparCampo();
				exibirMensagem("alteção realizada com sucesso");
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "cadastroProduto.xhtml";
		
	}
	
	public String editar() {
		return "cadastroProduto.xhtml";
		}
	
	public String excluir() {
		try {
			dao.excluir(produtoPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "listaProduto.xhtml";
		
	}
	
	public List<ProdutoPedro> getLista(){
	List<ProdutoPedro> listaRetorno= new ArrayList<ProdutoPedro>();
	try {
	
		listaRetorno= dao.listar();
	} catch (Exception e) {
		exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		e.printStackTrace();
	}
		
		return listaRetorno;
		
	}
	
	
}

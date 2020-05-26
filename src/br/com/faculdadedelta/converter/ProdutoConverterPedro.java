package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.ProdutoDaoPedro;
import br.com.faculdadedelta.modelo.ProdutoPedro;

@FacesConverter(value = "produtoConverter")
public class ProdutoConverterPedro implements Converter {

	private ProdutoDaoPedro dao = new ProdutoDaoPedro();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {

			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if(valor !=null) {
			return String.valueOf(((ProdutoPedro)valor).getId());
		}
		return null;
	}

}

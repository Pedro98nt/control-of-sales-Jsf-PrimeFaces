package br.com.faculdadedelta.modelo;

public class VendaPedro {
	private Long id;
	private String descProduto;
	private ProdutoPedro produtoPedro;
	private double valor;
	public VendaPedro() {
		super();
	}
	public VendaPedro(Long id, String descProduto, ProdutoPedro produtoPedro, double valor) {
		super();
		this.id = id;
		this.descProduto = descProduto;
		this.produtoPedro = produtoPedro;
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public ProdutoPedro getProduto() {
		return produtoPedro;
	}
	public void setProduto(ProdutoPedro produtoPedro) {
		this.produtoPedro = produtoPedro;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaPedro other = (VendaPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}

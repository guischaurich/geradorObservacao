package ObsNotaFiscal;

public class NotaFiscal {

	private Integer numero;
	
	private Double valorTotal;
	
	public NotaFiscal(Integer numero, Double valorTotal){
		
		this.numero = numero;
		
		this.valorTotal = valorTotal;
	}

	public Integer getNumero() {
		
		return numero;
	}

	public void setNumero(Integer numero) {
		
		this.numero = numero;
	}

	public Double getValorTotal() {
		
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		
		this.valorTotal = valorTotal;
	}
}
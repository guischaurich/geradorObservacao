package ObsNotaFiscal;

import java.text.DecimalFormat;

import java.util.List;

public class GeradorObservacao { 

	private static final String NAO_HA_FATURAS_DE_NOTA_FISCAL = "Não há faturas de nota fiscal.";

	private static final String FATURA_DAS_NOTAS_DE_SIMPLES_REMESSA = "Fatura das notas fiscais de simples remessa: ";
	
	private static final String FATURA_DA_NOTA_DE_SIMPLES_REMESSA = "Fatura da nota fiscal de simples remessa: ";
	
	private static final String CUJO_VALOR_EH_COM_ESPACOS =  " cujo valor é ";
	
	private static final String PONTO_FINAL_E_VALOR_TOTAL = ". Total = ";
	
	private static final String SEPARADOR_E_COM_ESPACOS = " e ";
	
	private static final DecimalFormat FORMATO_DE_VALOR_COM_CIFRAO = new DecimalFormat("R$ ###,###,###,##0.00");
	
	public String geraObservacao(List<Object> faturas) {
		
		if (!faturas.isEmpty()) {		
			
			return montaObservacaoComFaturasDeNotas(faturas);	
			
		} else {
			
			return NAO_HA_FATURAS_DE_NOTA_FISCAL;
		}
	}

	private String montaObservacaoComFaturasDeNotas(List<Object> faturas) {
		
		String observacao = montaPrefixoObservacaoConformeQuantidadeDeFaturas(faturas.size());
		
		return observacao + montaTextoObservacao(faturas);
	}
	
	private String montaPrefixoObservacaoConformeQuantidadeDeFaturas(Integer quantidadeDeFaturas) {
				
		return quantidadeDeFaturas >= 2 ? 
				FATURA_DAS_NOTAS_DE_SIMPLES_REMESSA : 
				FATURA_DA_NOTA_DE_SIMPLES_REMESSA;
	}
	
	private String montaTextoObservacao(List<Object> faturas) {
		
		boolean imprimirFaturaComoNotaFiscal = deveImprimirFaturaComoNotaFiscal(faturas.get(0));
		
		double valorTotal = 0;		
		
		String observacao = "";
		
		for(Integer posicao = 0; posicao < faturas.size(); posicao++) {
			 
			observacao += montaSeparadorObservacao(faturas.size(), posicao);
			
			if(imprimirFaturaComoNotaFiscal) {
				
				NotaFiscal nota = (NotaFiscal) faturas.get(posicao);			
				
				observacao += montaFraseObservacaoParaNotaFiscal(nota);
				
				valorTotal += nota.getValorTotal();
				
			} else {
			 	
				observacao += faturas.get(posicao);				
			}			
		}						
		return observacao + adicionaSufixo(imprimirFaturaComoNotaFiscal, valorTotal);
	}

	private String montaFraseObservacaoParaNotaFiscal(NotaFiscal nota) {
		
		return nota.getNumero() + CUJO_VALOR_EH_COM_ESPACOS + FORMATO_DE_VALOR_COM_CIFRAO.format(nota.getValorTotal());
	}
	
	private boolean deveImprimirFaturaComoNotaFiscal(Object fatura) {
		
		return fatura instanceof NotaFiscal;
	}

	private String montaSeparadorObservacao(Integer quantidadeFaturas, Integer posicao) {
		
		String observacao = "";
		
		if(primeiraFatura(posicao)) {
		
			observacao = "";

		} else if(naoEhUltimoSeparador(quantidadeFaturas, posicao)) {
		
			observacao += ", ";
			
		} else {
			
			observacao += SEPARADOR_E_COM_ESPACOS;
		}
		return observacao;
	}

	private boolean primeiraFatura(Integer posicao) {

		return posicao == 0;
	}
	
	private boolean naoEhUltimoSeparador(Integer quantidadeFaturas, Integer posicao) {
		
		return posicao < quantidadeFaturas -1;
	}
	
	private String adicionaSufixo(boolean impressaoDeNotas, double valorTotal) {
		
		return impressaoDeNotas 
				? PONTO_FINAL_E_VALOR_TOTAL + FORMATO_DE_VALOR_COM_CIFRAO.format(valorTotal) + "." 
				: ".";
	}
}
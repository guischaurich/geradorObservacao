package Teste;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

import ObsNotaFiscal.*;

public class TesteGeradorObservacao {
	
	private List<Object> criaRemessasSemNotaFiscal(Integer quantidadeDeRemessas) {
		
		List<Object> remessas = new ArrayList<Object>();
		
		for(Integer remessa = 1; remessa <= quantidadeDeRemessas; remessa++) {
		
			remessas.add(remessa); 
		}		
		return remessas;
	}
	
	private List<Object> criaRemessasComoNotaFiscal(Integer quantidadeDeRemessas) {
		
		List<Object> remessas = new ArrayList<Object>();
		
		for(Integer remessa = 1; remessa <= quantidadeDeRemessas; remessa++) {
		
			NotaFiscal notaFiscal = new NotaFiscal(remessa, remessa * 10.0);
			
			remessas.add(notaFiscal); 
		}		
		return remessas;
	}

	@Test
	public void DeveRetornarObservacaoSemFaturas() {
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(new ArrayList<Object>());
		
		assertEquals(observacaoGerada, "Não há faturas de nota fiscal.");
	}
	
	@Test
	public void DeveRetornarObservacaodeUmaRemessa() {
		
		List<Object> remessas = criaRemessasSemNotaFiscal(1);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas); 
		
		assertEquals(observacaoGerada, "Fatura da nota fiscal de simples remessa: 1.");
	}
	
	@Test
	public void DeveRetornarObservacaodeDuasRemessas() {
		
		List<Object> remessas = criaRemessasSemNotaFiscal(2);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas); 
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1 e 2.");
	}

	@Test
	public void DeveRetornarObservacaodeTresRemessas() {
		
		List<Object> remessas = criaRemessasSemNotaFiscal(3);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas); 
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1, 2 e 3.");
	}
	
	@Test
	public void DeveRetornarObservacaodeCincoRemessas() {
		
		List<Object> remessas = criaRemessasSemNotaFiscal(4);
				
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas); 
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1, 2, 3 e 4.");
	}
	
	@Test
	public void DeveRetornarNumeroDaNotaFiscalGravadoNoConstrutor() {
		
		NotaFiscal notaFiscal = new NotaFiscal(1, 100.5);
			
		assertEquals(notaFiscal.getNumero().intValue(), 1);
	}
	
	@Test
	public void DeveRetornarNumeroDaNotaFiscalGravadoNoSetter() {
		
		NotaFiscal notaFiscal = new NotaFiscal(1, 100.5);
		
		notaFiscal.setNumero(2);
			
		assertEquals(notaFiscal.getNumero().intValue(), 2);
	}
	
	@Test
	public void DeveRetornarValorTotalDaNotaFiscalGravadoNoConstrutor() {
		
		NotaFiscal notaFiscal = new NotaFiscal(1, 100.5);
			
		assertEquals(notaFiscal.getValorTotal().toString(), "100.5");
	}
	
	@Test
	public void DeveRetornarValorTotalDaNotaFiscalGravadoNoSetter() {
		
		NotaFiscal notaFiscal = new NotaFiscal(1, 100.5);
		
		notaFiscal.setValorTotal(200.0);
			
		assertEquals(notaFiscal.getValorTotal().toString(), "200.0");
	}
	
	@Test
	public void DeveRetornarObservacaodeUmaRemessaComValor() {
		
		List<Object> remessas = criaRemessasComoNotaFiscal(1);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas); 
		
		assertEquals(observacaoGerada, "Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total = R$ 10,00.");
	}
	
	@Test
	public void DeveRetornarObservacaodeDuasRemessasComValor() {
		
		List<Object> remessas = criaRemessasComoNotaFiscal(2);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas);
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00"
				+ " e 2 cujo valor é R$ 20,00. Total = R$ 30,00.");
	}
	
	@Test
	public void DeveRetornarObservacaodeTresRemessasComValor() {
		
		List<Object> remessas = criaRemessasComoNotaFiscal(3);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas);
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00,"
				+ " 2 cujo valor é R$ 20,00 e 3 cujo valor é R$ 30,00. Total = R$ 60,00.");
	}
	
	@Test
	public void DeveRetornarObservacaodeQuatroRemessasComValor() {
		
		List<Object> remessas = criaRemessasComoNotaFiscal(4);
		
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas);
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00,"
				+ " 2 cujo valor é R$ 20,00, 3 cujo valor é R$ 30,00 e 4 cujo valor é R$ 40,00. Total = R$ 100,00.");
	}

	@Test
	public void DeveRetornarObservacaodeCincoRemessasComValor() {
		
		List<Object> remessas = new ArrayList<Object>();
		
		NotaFiscal notaFiscal1 = new NotaFiscal(1, 10.0);
		
		remessas.add(notaFiscal1);

		NotaFiscal notaFiscal2 = new NotaFiscal(2, 35.0);
		
		remessas.add(notaFiscal2);

		NotaFiscal notaFiscal3 = new NotaFiscal(3, 5.0);
		
		remessas.add(notaFiscal3);

		NotaFiscal notaFiscal4 = new NotaFiscal(4, 1500.0);
		
		remessas.add(notaFiscal4);

		NotaFiscal notaFiscal5 = new NotaFiscal(5, 0.30);
		
		remessas.add(notaFiscal5);
				
		GeradorObservacao observacao = new GeradorObservacao();
		
		String observacaoGerada = observacao.geraObservacao(remessas);
		
		assertEquals(observacaoGerada, "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00,"
				+ " 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = R$ 1.550,30.");
	}
}
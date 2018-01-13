package br.com.caelum.leilao;

import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	
	public void avalia(Leilao leilao) {
		
		leilao.getLances().forEach(lance -> {
			if (lance.getValor() < getMenorLance()) setMenorLance(lance.getValor());
			if (lance.getValor() > getMaiorLance()) setMaiorLance(lance.getValor());
		});
		
	
	}
	
	public Double getValorMedioLances(Leilao leilao) {
		return leilao
				.getLances()
				.parallelStream()
				.mapToDouble(lance -> lance.getValor())
				.average()
				.getAsDouble();
	}

	public double getMaiorLance() {
		return maiorLance;
	}

	public double getMenorLance() {
		return menorLance;
	}
	
	private void setMaiorLance(Double lance) {
		this.maiorLance = lance;
	}
	
	private void setMenorLance(Double lance) {
		this.menorLance = lance;
	}

}

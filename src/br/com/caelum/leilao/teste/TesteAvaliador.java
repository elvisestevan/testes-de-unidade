package br.com.caelum.leilao.teste;

import org.junit.Test;

import br.com.caelum.leilao.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;;

public class TesteAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(maria, 250));
		leilao.propoe(new Lance(jose, 400));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		Assert.assertEquals(500.0, avaliador.getMaiorLance(), 0.0001);
		Assert.assertEquals(250.0, avaliador.getMenorLance(), 0.0001);
		
		
	}

}

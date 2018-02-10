package br.com.caelum.leilao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;
	
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("José");
	}
	
	@After
	public void finaliza() {
	  System.out.println("fim");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 500.0)
				.lance(maria, 250.0)
				.lance(jose, 400)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(500.0, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(250.0, leiloeiro.getMenorLance(), 0.0001);
		
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 250.0)
				.lance(maria, 400.0)
				.lance(jose, 500)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(500.0, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(250.0, leiloeiro.getMenorLance(), 0.0001);
		
		
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 1000.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveCalcularAMediaDosLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 500.0)
				.lance(maria, 250.0)
				.lance(jose, 400)
				.constroi();
		
		double media = leiloeiro.getValorMedioLances(leilao);
		
		Assert.assertEquals(383.33333, media, 0.00001);
	}
	
	@Test
	public void deveEncontrarOsMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(joao, 500.0)
				.lance(maria, 250.0)
				.lance(jose, 400)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getMaiores();
		
		Assert.assertEquals(3, maiores.size());
		Assert.assertEquals(250.0, maiores.get(0).getValor(), 0.0001);
		Assert.assertEquals(400.0, maiores.get(1).getValor(), 0.0001);
		Assert.assertEquals(500.0, maiores.get(2).getValor(), 0.0001);
	}

}

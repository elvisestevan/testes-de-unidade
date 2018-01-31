package br.com.caelum.leilao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;;

public class AvaliadorTest {
	
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
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 400));
		leilao.propoe(new Lance(jose, 500));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		Assert.assertEquals(500.0, avaliador.getMaiorLance(), 0.0001);
		Assert.assertEquals(250.0, avaliador.getMenorLance(), 0.0001);
		
		
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Usuario joao = new Usuario("Joao");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		Assert.assertEquals(1000.0, avaliador.getMaiorLance(), 0.0001);
		Assert.assertEquals(1000.0, avaliador.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveCalcularAMediaDosLances() {

		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(maria, 250));
		leilao.propoe(new Lance(jose, 400));
		
		Avaliador avaliador = new Avaliador();
		double media = avaliador.getValorMedioLances(leilao);
		
		Assert.assertEquals(383.33333, media, 0.00001);
	}
	
	@Test
	public void deveEncontrarOsMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(maria, 250));
		leilao.propoe(new Lance(jose, 400));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		List<Lance> maiores = avaliador.getMaiores();
		
		Assert.assertEquals(3, maiores.size());
		Assert.assertEquals(250.0, maiores.get(0).getValor(), 0.0001);
		Assert.assertEquals(400.0, maiores.get(1).getValor(), 0.0001);
		Assert.assertEquals(500.0, maiores.get(2).getValor(), 0.0001);
	}

}

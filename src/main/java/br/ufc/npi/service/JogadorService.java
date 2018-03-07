package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.repository.JogadorRepository;

@Service
public class JogadorService {
	
		@Autowired
		JogadorRepository jogador_rep;
		
		public Jogador salvarJogador(String nome, int idade){
			Jogador jogador = new Jogador();
			jogador.setNome(nome);
			jogador.setIdade(idade);
			
			this.jogador_rep.save(jogador);
			
			return jogador;
		}
		
		public List<Jogador> getTodosOsJogadores(){
			return this.jogador_rep.findAll();
		}

		public List<Jogador> getJogadoresSemTime(){
			return this.jogador_rep.findJogadoresSemTime();
		}

}

package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repository.JogadorRepository;
import br.ufc.npi.repository.TimeRepository;



@Service
public class TimeService {
	
	@Autowired
	TimeRepository time_rep;
	
	@Autowired
	JogadorRepository jogador_rep;
	
	public Time salvarTime(String nome){
		Time time= new Time();
		time.setNome(nome);
		
		this.time_rep.save(time);
		
		return time;
	}
	
	public List<Time> getTodosOsTimes(){
		return this.time_rep.findAll();
	}
	
	public Time getTime(Integer id) {
		
		return this.time_rep.getOne(id);
    }

	public boolean addJogadorAoTime(Integer idTime, Integer jogadorSemTimeID) {

		  Time time = time_rep.getOne(idTime);
		  if(time.getJogadores().size()==7){
		    return false;
		  }else {
		    Jogador jogador = jogador_rep.getOne(jogadorSemTimeID);
		    time.getJogadores().add(jogador);
		    jogador.setTime(time);
		    time_rep.save(time);
		    jogador_rep.save(jogador);
		    return true;
		  }
     }

    
    public void delJogadorAoTime(Integer idTime, Integer jogadorId){
    	
        Time time = time_rep.getOne(idTime);	
        Jogador jogador = jogador_rep.getOne(idTime);
        
        time.getJogadores().remove(jogador);
        jogador.setTime(null);
        
        
        time_rep.save(time);
        jogador_rep.save(jogador);
        
      	
      }

}

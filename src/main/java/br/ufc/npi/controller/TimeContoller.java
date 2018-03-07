package br.ufc.npi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.service.JogadorService;
import br.ufc.npi.service.TimeService;

@Controller
@RequestMapping (path="/times/")
public class TimeContoller {

	@Autowired
	TimeService service_time;
	
	@Autowired
	JogadorService service_jogador;
	
	@RequestMapping (path="/")
	public ModelAndView index (){
		
		ModelAndView model = new ModelAndView("times");
		List<Time> times = this.service_time.getTodosOsTimes();
		
		model.addObject("times", times);
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesTime(@PathVariable("id") Integer id, @RequestParam(required=false) String erro){

	  ModelAndView model = new ModelAndView("detalhes-time");
	  Time time = service_time.getTime(id);
	  List<Jogador> jogadoresSemTime = service_jogador.getJogadoresSemTime();

	  model.addObject("jogadoresSemTime", jogadoresSemTime);
	  model.addObject("time", time);
	  model.addObject("erro", erro);

	  return model;
	}
	
	@PostMapping (path="/salve")
	public String salveTime (@RequestParam String nomeTime){
		  this.service_time.salvarTime(nomeTime);
		return "redirect:/times/";
	}
	
	@RequestMapping(path="/{idTime}/adicionarjogador", method=RequestMethod.POST)
	public ModelAndView adicionarJogadorAoTime(@PathVariable("idTime") Integer idTime, 
	                                           @RequestParam Integer jogadorSemTimeId ){

	  ModelAndView model = new ModelAndView("redirect:/times/"+idTime);
	  boolean itsOk = service_time.addJogadorAoTime(idTime, jogadorSemTimeId);
	  if(itsOk==false){
	    String erro = "O time já está completo.";
	    model.addObject("erro", erro);
	  }
	  return model;
	}

	
	@RequestMapping (path="/{idTime}/removerjogador/{idJogador}")
	public String removerJogadorDoTime (@PathVariable("idTime") Integer idTime,
			                            @PathVariable("idJogador") Integer jogadorSemTimeId)	{   
        this.service_time.delJogadorAoTime(idTime, jogadorSemTimeId);
		
		return "redirect:/times/"+idTime;
	}
	
	
	
}

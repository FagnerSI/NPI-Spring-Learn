package br.ufc.npi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Jogador;

@Repository
@Transactional
public interface JogadorRepository extends JpaRepository<Jogador, Integer>{
	
	Jogador findByNome(String nome);
	
	@Query("from Jogador j where j.time is null")
	List<Jogador> findJogadoresSemTime ();
}

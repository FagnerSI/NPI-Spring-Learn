package br.ufc.npi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Time;

@Repository
@Transactional
public interface TimeRepository extends JpaRepository<Time, Integer> {
	
	Time findByNome(String nome);		
	
	
}

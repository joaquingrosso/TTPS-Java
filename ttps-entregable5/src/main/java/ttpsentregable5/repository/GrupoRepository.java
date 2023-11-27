package ttpsentregable5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ttpsentregable5.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
	@Query("SELECT g FROM Grupo g WHERE LOWER(g.nombre)=LOWER(:name)")
	Grupo recuperarPorNombre(@Param("name") String name);
	
}
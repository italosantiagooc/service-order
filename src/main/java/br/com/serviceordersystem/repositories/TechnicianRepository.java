package br.com.serviceordersystem.repositories;

import br.com.serviceordersystem.models.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
    @Query("SELECT obj FROM Technician obj WHERE obj.cpf =:cpf")
    Technician findByCpf(@Param("cpf") String cpf);
}

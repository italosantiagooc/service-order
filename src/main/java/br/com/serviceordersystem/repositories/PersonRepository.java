package br.com.serviceordersystem.repositories;

import br.com.serviceordersystem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT obj FROM Person obj WHERE obj.cpf =:cpf")
    Person findByCpf(@Param("cpf") String cpf);
}

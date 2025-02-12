package madstodolist.repository;

import madstodolist.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface personaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByCorreo(String s);
}

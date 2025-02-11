package madstodolist.repository;

import madstodolist.model.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface personaRepository extends CrudRepository<Persona, Integer> {
    Optional<Persona> findByCorreo(String s);
}

package madstodolist.repository;

import madstodolist.model.Mensaje;
import madstodolist.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface mensajeRepository extends JpaRepository<Mensaje, Integer> {
}

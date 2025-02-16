package madstodolist.repository;

import madstodolist.model.Dieta;
import madstodolist.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface mensajeRepository extends JpaRepository<Mensaje, Long> {
}

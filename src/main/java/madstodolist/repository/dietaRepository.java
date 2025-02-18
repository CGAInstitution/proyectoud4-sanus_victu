package madstodolist.repository;

import madstodolist.model.Dieta;
import madstodolist.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface dietaRepository extends JpaRepository<Dieta, Long> {
    List<Dieta> findByUsuarioId(Long usuarioId);
}

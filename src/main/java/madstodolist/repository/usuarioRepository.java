package madstodolist.repository;

import madstodolist.model.Persona;
import madstodolist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {

}

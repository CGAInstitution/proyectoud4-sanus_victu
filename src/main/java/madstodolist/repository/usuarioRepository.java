package madstodolist.repository;

import madstodolist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNutricionistaId(Long id_nutricionista);
}
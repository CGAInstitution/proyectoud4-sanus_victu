package madstodolist.repository;

import madstodolist.model.Nutricionista;
import madstodolist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface nutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}

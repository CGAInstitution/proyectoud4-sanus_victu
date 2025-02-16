package madstodolist.repository;

import madstodolist.model.Mensaje;
import madstodolist.model.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface supermercadoRepository extends JpaRepository<Supermercado, Long> {
    Supermercado findByNombre(String nombre);
}

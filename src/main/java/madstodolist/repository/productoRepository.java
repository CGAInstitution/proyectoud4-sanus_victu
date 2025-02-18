package madstodolist.repository;

import madstodolist.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface productoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}

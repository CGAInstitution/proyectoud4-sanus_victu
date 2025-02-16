package madstodolist.repository;

import madstodolist.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productoRepository extends JpaRepository<Producto, Long> {
}

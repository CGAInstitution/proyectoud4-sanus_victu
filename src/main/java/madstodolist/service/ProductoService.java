package madstodolist.service;

import madstodolist.model.Nutricionista;
import madstodolist.model.Producto;
import madstodolist.repository.nutricionistaRepository;
import madstodolist.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private productoRepository producrep;

    public List<Producto> obtenerTodos() {
        return producrep.findAll();
    }

}

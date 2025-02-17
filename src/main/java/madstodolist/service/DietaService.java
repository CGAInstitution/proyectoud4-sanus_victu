package madstodolist.service;

import madstodolist.model.Producto;
import madstodolist.repository.dietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DietaService {

    @Autowired
    private dietaRepository dietaRepository;

    @Transactional
    public void añadirProducto(Producto producto) {

    }
}

package madstodolist.service;

import madstodolist.model.Dieta;
import madstodolist.model.Producto;
import madstodolist.model.Usuario; // Asegúrate de importar el modelo Usuario
import madstodolist.repository.dietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DietaService {

    @Autowired
    private dietaRepository dietaRepository;

    @Transactional
    public Dieta crearDieta(String nombreDieta, boolean favorito, Usuario usuario) {
        // Crear una nueva instancia de Dieta
        Dieta nuevaDieta = new Dieta();
        nuevaDieta.setNombre_dieta(nombreDieta);
        nuevaDieta.setFavorito(favorito);
        nuevaDieta.setUsuario(usuario);
        nuevaDieta.setProductos(new HashSet<>()); // Inicializar el conjunto de productos

        // Guardar la dieta en el repositorio
        return dietaRepository.save(nuevaDieta);
    }

    @Transactional
    public void añadirProducto(Long dietaId, Producto producto) {
        Optional<Dieta> dietaOpt = dietaRepository.findById(dietaId);
        if (dietaOpt.isPresent()) {
            Dieta dieta = dietaOpt.get();
            dieta.getProductos().add(producto);
            dietaRepository.save(dieta);
        } else {
            throw new RuntimeException("Dieta no encontrada con ID: " + dietaId);
        }
    }
}
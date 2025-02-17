package madstodolist.service;

import madstodolist.model.Supermercado;
import madstodolist.repository.supermercadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupermercadoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private supermercadoRepository supermercadoRepository;

    // Método estático para convertir nombres de supermercados a entidades Supermercado
    public static Set<Supermercado> convertirNombresASupermercados(List<String> nombresSupermercados, supermercadoRepository repo) {
        return nombresSupermercados.stream()
                .map(nombreSupermercado -> {
                    // Buscar el supermercado por nombre
                    Supermercado supermercado = repo.findByNombre(nombreSupermercado);
                    if (supermercado == null) {
                        // Si no existe, crear y guardar un nuevo supermercado
                        supermercado = new Supermercado();
                        supermercado.setNombre(nombreSupermercado);
                        repo.save(supermercado);
                    }
                    return supermercado;
                })
                .collect(Collectors.toSet());
    }

    @Transactional
    public List<Supermercado> obtenerTodosSupermercados() {
        return supermercadoRepository.findAll();
    }
    @Transactional
    public void guardarSupermercados(Set<Supermercado> supermercados) {
        supermercadoRepository.saveAll(supermercados);
    }
}
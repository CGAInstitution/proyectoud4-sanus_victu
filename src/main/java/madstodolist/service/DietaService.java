package madstodolist.service;

import madstodolist.repository.dietaRepository;
import madstodolist.model.Dieta;  // Asegúrate de tener la clase Dieta importada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietaService {

    @Autowired
    private dietaRepository dietaRepository;

    public Dieta guardarDieta(Dieta dieta) {
        return dietaRepository.save(dieta);
    }
}

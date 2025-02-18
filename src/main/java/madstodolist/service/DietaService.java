package madstodolist.service;

import madstodolist.model.Dieta;
import madstodolist.repository.dietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DietaService {

    @Autowired
    private dietaRepository dietaRepository;

    @Transactional
    public Dieta guardarDieta(Dieta dieta) {
        return dietaRepository.save(dieta);
    }

    @Transactional
    public List<Dieta> obtenerDietasPorUsuario(Long usuarioId) {
        return dietaRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public void eliminarDietaPorId(Long dietaId) {
        dietaRepository.deleteById(dietaId);
    }

    @Transactional
    public Optional<Dieta> buscarPorId(Long dietaId) {
        return dietaRepository.findById(dietaId);
    }
}

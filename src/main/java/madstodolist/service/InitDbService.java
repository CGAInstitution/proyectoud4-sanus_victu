package madstodolist.service;

import madstodolist.repository.personaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
// Se ejecuta solo si el perfil activo es 'dev'
@Profile("dev")
public class InitDbService {

    @Autowired
    private personaRepository personaRepository;

    // Se ejecuta tras crear el contexto de la aplicación
    // para inicializar la base de datos
   /* @PostConstruct
    public void initDatabase() {
        Usuario usuario = new Usuario("user@ua");
        usuario.setNombre("Usuario Ejemplo");
        usuario.setPassword("123");
        usuarioRepository.save(usuario);
    }
*/
}

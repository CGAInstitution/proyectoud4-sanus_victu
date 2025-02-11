package madstodolist.service;

import madstodolist.model.Administrador;
import madstodolist.model.Nutricionista;
import madstodolist.model.Persona;
import madstodolist.model.Usuario;
import madstodolist.repository.personaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
// Se ejecuta solo si el perfil activo es 'dev'
@Profile("dev")
public class InitDbService {

    private Set<Usuario> pacientes =new HashSet<>();
    @Autowired
    private personaRepository personaRepository;

    // Se ejecuta tras crear el contexto de la aplicación
    // para inicializar la base de datos
    @PostConstruct
    public void initDatabase() {
        Administrador admin = new Administrador();
        admin.setNickName("admin");
        admin.setNombre("admin");
        admin.setContraseña("a");
        admin.setCorreo("admin@teis.es");

        Nutricionista nutricionista1 = new Nutricionista();

        Usuario usuario = new Usuario();
        usuario.setNombre("usuario1");
        usuario.setContraseña("u");
        usuario.setCorreo("user@teis.es");
        usuario.setNutricionista(nutricionista1);
        usuario.setPeso(60);
        usuario.setSexo("Masculino");
        usuario.setEdad(25);

        nutricionista1.setNombre("nutricionista1");
        nutricionista1.setContraseña("n");
        nutricionista1.setCorreo("correo@teis.es");
        pacientes.add(usuario);
        nutricionista1.setPacientes(pacientes);

        personaRepository.save(admin);
        personaRepository.save(nutricionista1);
        personaRepository.save(usuario);
    }

}

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
        // Creación del administrador con sus datos
        Administrador admin = new Administrador();
        admin.setNickName("admin"); // Establece el apodo del administrador
        admin.setNombre("admin"); // Establece el nombre del administrador
        admin.setContraseña("a"); // Establece la contraseña del administrador
        admin.setCorreo("admin@teis.es"); // Establece el correo del administrador

        // Creación de nutricionistas
        Nutricionista nutricionista1 = new Nutricionista();
        Nutricionista nutricionista2 = new Nutricionista();

        // Creación del primer usuario con sus datos
        Usuario usuario = new Usuario();
        usuario.setNombre("usuario1"); // Establece el nombre del usuario
        usuario.setContraseña("u"); // Establece la contraseña del usuario
        usuario.setCorreo("user@teis.es"); // Establece el correo del usuario
        usuario.setNutricionista(nutricionista1); // Asocia el nutricionista al usuario
        usuario.setPeso(60); // Establece el peso del usuario
        usuario.setSexo("Masculino"); // Establece el sexo del usuario
        usuario.setEdad(25); // Establece la edad del usuario

        // Creación del segundo usuario con sus datos
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("usuario2");
        usuario2.setContraseña("u");
        usuario2.setCorreo("user2@teis.es");
        usuario2.setNutricionista(nutricionista1); // Asocia el mismo nutricionista
        usuario2.setPeso(60);
        usuario2.setSexo("Masculino");
        usuario2.setEdad(25);

        // Creación del tercer usuario con sus datos
        Usuario usuario3 = new Usuario();
        usuario3.setNombre("usuario3");
        usuario3.setContraseña("u");
        usuario3.setCorreo("user3@teis.es");
        usuario3.setNutricionista(nutricionista2); // Asocia un nutricionista diferente
        usuario3.setPeso(60);
        usuario3.setSexo("Masculino");
        usuario3.setEdad(25);

        // Configuración del primer nutricionista
        nutricionista1.setNombre("nutricionista1");
        nutricionista1.setContraseña("n");
        nutricionista1.setCorreo("correo@teis.es");
        // Agrega el primer usuario a la lista de pacientes del nutricionista
        pacientes.add(usuario);
        pacientes.add(usuario2);
        nutricionista1.setPacientes(pacientes); // Asocia la lista de pacientes al nutricionista

        // Configuración del segundo nutricionista
        nutricionista2.setNombre("nutricionista2");
        nutricionista2.setContraseña("n");
        nutricionista2.setCorreo("correo2@teis.es");
        // Agrega el tercer usuario a la lista de pacientes del segundo nutricionista
        pacientes.add(usuario3);
        nutricionista2.setPacientes(pacientes); // Asocia la lista de pacientes al segundo nutricionista

        // Guarda todos los objetos creados en el repositorio
        personaRepository.save(admin); // Guarda el administrador
        personaRepository.save(nutricionista1); // Guarda el primer nutricionista
        personaRepository.save(nutricionista2); // Guarda el segundo nutricionista
        personaRepository.save(usuario); // Guarda el primer usuario
        personaRepository.save(usuario2); // Guarda el segundo usuario
        personaRepository.save(usuario3); // Guarda el tercer usuario
    }

}

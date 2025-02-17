package madstodolist.service;

import madstodolist.model.*;
import madstodolist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("dev")
public class InitDbService {

    @PersistenceContext
    private EntityManager entityManager; // Inyectar el EntityManager

    private Set<Usuario> pacientes = new HashSet<>();

    @Autowired
    private personaRepository personaRepository;

    @Autowired
    private productoRepository productoRepository;

    @Autowired
    private supermercadoRepository supermercadoRepository;

    @Autowired
    private dietaRepository dietaRepository;

    @Autowired
    private mensajeRepository mensajeRepository;

    @Autowired
    private nutricionistaRepository nutricionistaRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private ProductoService productoSupermercadoService;

    public void init() {
        initDatabase();   // Inicializar la base de datos con datos de prueba
    }

    @Transactional
    public void delectDatabase() {
        // Borrar todos los registros de las tablas
        personaRepository.deleteAll();
        productoRepository.deleteAll();
        supermercadoRepository.deleteAll();
        dietaRepository.deleteAll();
        mensajeRepository.deleteAll();
        nutricionistaRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Resetear las secuencias de autoincremento
        resetAutoIncrement("persona");
        resetAutoIncrement("producto");
        resetAutoIncrement("supermercado");
        resetAutoIncrement("dieta");
        resetAutoIncrement("mensaje");
        resetAutoIncrementNutricionista("nutricionista");
        resetAutoIncrementUsuario("usuario");
    }

    private void resetAutoIncrement(String tableName) {
        // Ejecutar una sentencia SQL nativa para resetear el autoincremento
        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
    private void resetAutoIncrementNutricionista(String tableName) {
        // Ejecutar una sentencia SQL nativa para resetear el autoincremento
        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 2";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
    private void resetAutoIncrementUsuario(String tableName) {
        // Ejecutar una sentencia SQL nativa para resetear el autoincremento
        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 4";
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public void initDatabase() {
        // Creación del administrador con sus datos
        Administrador admin = new Administrador();
        admin.setId(1L);
        admin.setNickName("admin");
        admin.setNombre("admin");
        admin.setContraseña("a");
        admin.setCorreo("a@a");

        // Creación de nutricionistas
        Nutricionista nutricionista1 = new Nutricionista();
        Nutricionista nutricionista2 = new Nutricionista();

        // Creación del primer usuario con sus datos
        Usuario usuario = new Usuario();
        usuario.setId(4L);
        usuario.setNombre("usuario1");
        usuario.setContraseña("u");
        usuario.setCorreo("user@teis.es");
        usuario.setNutricionista(nutricionista1);
        usuario.setPeso(60);
        usuario.setSexo("Masculino");
        usuario.setEdad(25);

        // Creación del segundo usuario con sus datos
        Usuario usuario2 = new Usuario();
        usuario2.setId(5L);
        usuario2.setNombre("usuario2");
        usuario2.setContraseña("u");
        usuario2.setCorreo("user2@teis.es");
        usuario2.setNutricionista(nutricionista1);
        usuario2.setPeso(60);
        usuario2.setSexo("Masculino");
        usuario2.setEdad(25);

        // Creación del tercer usuario con sus datos
        Usuario usuario3 = new Usuario();
        usuario3.setId(6L);
        usuario3.setNombre("usuario3");
        usuario3.setContraseña("u");
        usuario3.setCorreo("user3@teis.es");
        usuario3.setNutricionista(nutricionista2);
        usuario3.setPeso(60);
        usuario3.setSexo("Masculino");
        usuario3.setEdad(25);

        // Configuración del primer nutricionista
        nutricionista1.setId(2L);
        nutricionista1.setNombre("nutricionista1");
        nutricionista1.setContraseña("n");
        nutricionista1.setCorreo("correo@teis.es");
        pacientes.add(usuario);
        pacientes.add(usuario2);
        nutricionista1.setPacientes(pacientes);

        // Configuración del segundo nutricionista
        nutricionista2.setId(3L);
        nutricionista2.setNombre("nutricionista2");
        nutricionista2.setContraseña("n");
        nutricionista2.setCorreo("correo2@teis.es");
        pacientes.add(usuario3);
        nutricionista2.setPacientes(pacientes);

        // Guarda todos los objetos creados en el repositorio
        personaRepository.save(admin);
        personaRepository.save(nutricionista1);
        personaRepository.save(nutricionista2);
        personaRepository.save(usuario);
        personaRepository.save(usuario2);
        personaRepository.save(usuario3);

        // Cargar y guardar productos desde el JSON
        cargarYGuardarProductos();
    }

    private void cargarYGuardarProductos() {
        // Obtener la lista de productos desde el JSON
        List<Producto> productos = productoSupermercadoService.cargarYConvertirProductos();

        // Guardar los supermercados primero
        Set<Supermercado> supermercadosUnicos = new HashSet<>();
        for (Producto producto : productos) {
            supermercadosUnicos.addAll(producto.getSupermercados());
        }
        supermercadoRepository.saveAll(supermercadosUnicos);

        // Guardar los productos
        productoRepository.saveAll(productos);
    }
}
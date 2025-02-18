package madstodolist.service;

import madstodolist.model.Supermercado;
import madstodolist.repository.supermercadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class SupermercadoServiceTest {

    @Mock
    private supermercadoRepository supermercadoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SupermercadoService supermercadoService;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertirNombresASupermercados_Existente() {
        // Preparación de datos
        String nombreSupermercado = "Supermercado1";
        Supermercado supermercadoExistente = new Supermercado();
        supermercadoExistente.setNombre(nombreSupermercado);

        // Simulación del comportamiento del repositorio
        when(supermercadoRepository.findByNombre(nombreSupermercado)).thenReturn(supermercadoExistente);

        // Llamada al método
        Set<Supermercado> supermercados = SupermercadoService.convertirNombresASupermercados(List.of(nombreSupermercado), supermercadoRepository);

        // Verificación
        assertEquals(1, supermercados.size());
        assertTrue(supermercados.contains(supermercadoExistente));

        // Verifica que el repositorio no intente guardar el supermercado ya existente
        verify(supermercadoRepository, never()).save(any(Supermercado.class));
    }


    @Test
    void testObtenerTodosSupermercados() {
        // Preparación de datos
        Supermercado supermercado1 = new Supermercado();
        supermercado1.setNombre("Supermercado1");
        Supermercado supermercado2 = new Supermercado();
        supermercado2.setNombre("Supermercado2");

        List<Supermercado> listaSupermercados = List.of(supermercado1, supermercado2);

        // Simulación del comportamiento del repositorio
        when(supermercadoRepository.findAll()).thenReturn(listaSupermercados);

        // Llamada al método
        List<Supermercado> supermercados = supermercadoService.obtenerTodosSupermercados();

        // Verificación
        assertNotNull(supermercados);
        assertEquals(2, supermercados.size());
        assertTrue(supermercados.contains(supermercado1));
        assertTrue(supermercados.contains(supermercado2));
    }

    @Test
    void testGuardarSupermercados() {
        // Preparación de datos
        Supermercado supermercado1 = new Supermercado();
        supermercado1.setNombre("Supermercado1");
        Supermercado supermercado2 = new Supermercado();
        supermercado2.setNombre("Supermercado2");

        Set<Supermercado> supermercados = Set.of(supermercado1, supermercado2);

        // Llamada al método
        supermercadoService.guardarSupermercados(supermercados);

        // Verificación
        verify(supermercadoRepository, times(1)).saveAll(supermercados);
    }
}

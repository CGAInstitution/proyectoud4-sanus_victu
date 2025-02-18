package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class NutricionistaWebTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ManagerUserSession managerUserSession;

    @Mock
    private Model model;

    @InjectMocks
    private NutricionistaController nutricionistaController;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostrarPanel_NoSesion() {

        when(managerUserSession.personaLogeado()).thenReturn(null);

        String result = nutricionistaController.mostrarPanel(1L, model);

        assertEquals("redirect:/login", result);
        verify(managerUserSession).personaLogeado();
    }

    @Test
    void testMostrarPanel_IdSesionNoCoincide() {

        when(managerUserSession.personaLogeado()).thenReturn(2L);

        String result = nutricionistaController.mostrarPanel(1L, model);

        assertEquals("redirect:/login", result);
        verify(managerUserSession).personaLogeado();
    }

    @Test
    void testMostrarPanel_Exito() {

        when(managerUserSession.personaLogeado()).thenReturn(1L);

        List<Usuario> pacientes = List.of(new Usuario(), new Usuario());
        when(usuarioService.obtenerUsuariosPorNutricionista(1L)).thenReturn(pacientes);

        String result = nutricionistaController.mostrarPanel(1L, model);

        assertEquals("nutricionista", result);

        verify(model).addAttribute("nutricionistaId", 1L);
        verify(model).addAttribute("pacientes", pacientes);
    }
}

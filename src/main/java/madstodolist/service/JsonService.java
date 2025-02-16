package madstodolist.service;

import madstodolist.dto.ProductoData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class JsonService {

    // Método para leer el JSON y convertirlo en una lista de ProductoData
    public List<ProductoData> cargarProductosDesdeJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductoData> productos = null;

        try {
            // Leer el archivo JSON desde la carpeta de recursos
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/productos.json");

            if (inputStream == null) {
                throw new RuntimeException("No se pudo encontrar el archivo JSON en la ruta especificada.");
            }

            // Convertir el JSON a una lista de ProductoData
            productos = objectMapper.readValue(inputStream, new TypeReference<List<ProductoData>>() {});

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer o convertir el JSON: " + e.getMessage());
        }

        return productos;
    }
}
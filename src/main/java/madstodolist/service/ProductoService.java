package madstodolist.service;

import madstodolist.dto.ProductoData;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.repository.productoRepository;
import madstodolist.repository.supermercadoRepository; // Importar el repositorio
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private productoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private supermercadoRepository supermercadoRepository; // Inyectar el repositorio

    // Método para cargar productos desde JSON y mapearlos a la entidad Producto
    @Transactional
    public List<Producto> cargarYConvertirProductos() {
        // Obtener la lista de ProductoData desde el JSON
        List<ProductoData> productosData = jsonService.cargarProductosDesdeJson();

        // Mapear cada ProductoData a Producto usando ModelMapper
        List<Producto> productos = productosData.stream()
                .map(this::convertirProductoDataAProducto) // Llamar al nuevo método
                .collect(Collectors.toList());

        return productos;
    }

    // Método para convertir ProductoData a Producto
    private Producto convertirProductoDataAProducto(ProductoData productoData) {
        // Mapear los campos básicos de ProductoData a Producto
        Producto producto = modelMapper.map(productoData, Producto.class);

        // Convertir la lista de supermercados (String) a un Set<Supermercado>
        Set<Supermercado> supermercados = SupermercadoService.convertirNombresASupermercados(productoData.getSupermercado(), supermercadoRepository);
        producto.setSupermercados(supermercados);

        return producto;
    }

    @Transactional
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    @Transactional
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public void guardarProductos(List<Producto> productos) {
       productoRepository.saveAll(productos);
    }
}
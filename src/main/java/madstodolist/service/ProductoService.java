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
import java.util.Optional;
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

    @Transactional
    public List<Producto> obtenerProductosPorIds(List<Long> ids) {
        return productoRepository.findAllById(ids);
    }

    @Transactional
    public Producto mapearProducto(ProductoData productoData) {
        return modelMapper.map(productoData, Producto.class);
    }

    @Transactional
    public Optional<Producto> obtenerProductoPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Transactional
    public void borrarProducto(Long id) {
        productoRepository.delete(productoRepository.findById(id).get());
    }

    @Transactional
    public Optional<Producto> obtenerProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public void actualizarProducto(Long id, ProductoData productoData) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        producto.setNombre(productoData.getNombre());
        producto.setValorEnergetico(productoData.getValorEnergetico());
        producto.setGrasas(productoData.getGrasas());
        producto.setHidratosCarbono(productoData.getHidratosCarbono());
        producto.setFibraAlimentaria(productoData.getFibraAlimentaria());
        producto.setProteinas(productoData.getProteinas());
        producto.setSal(productoData.getSal());

        productoRepository.save(producto);
    }

    @Transactional
    public ProductoData obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return convertirAProductoData(producto);
    }

    private ProductoData convertirAProductoData(Producto producto) {
        return new ProductoData(
                producto.getId(),
                producto.getNombre(),
                producto.getValorEnergetico(),
                producto.getGrasas(),
                producto.getHidratosCarbono(),
                producto.getFibraAlimentaria(),
                producto.getProteinas(),
                producto.getSal(),
                producto.getSupermercados().stream()
                        .map(Supermercado::getNombre) // Extrae solo los nombres de los supermercados
                        .collect(Collectors.toList())
        );
    }
}
package madstodolist.service;

import madstodolist.dto.ProductoData;
import madstodolist.model.Producto;
import madstodolist.model.Supermercado;
import madstodolist.repository.supermercadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Producto_SupermercadoService {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private supermercadoRepository supermercadoRepository;

    // Método para cargar productos desde JSON y mapearlos a la entidad Producto
    public List<Producto> cargarYConvertirProductos() {
        // Obtener la lista de ProductoData desde el JSON
        List<ProductoData> productosData = jsonService.cargarProductosDesdeJson();

        // Mapear cada ProductoData a Producto usando ModelMapper
        List<Producto> productos = productosData.stream()
                .map(productoData -> {
                    // Mapear los campos básicos de ProductoData a Producto
                    Producto producto = modelMapper.map(productoData, Producto.class);

                    // Convertir la lista de supermercados (String) a un Set<Supermercado>
                    Set<Supermercado> supermercados = productoData.getSupermercado().stream()
                            .map(nombreSupermercado -> {
                                // Buscar el supermercado por nombre
                                Supermercado supermercado = supermercadoRepository.findByNombre(nombreSupermercado);
                                if (supermercado == null) {
                                    // Si no existe, crear y guardar un nuevo supermercado
                                    supermercado = new Supermercado();
                                    supermercado.setNombre(nombreSupermercado);
                                    supermercadoRepository.save(supermercado);
                                }
                                return supermercado;
                            })
                            .collect(Collectors.toSet());
                    producto.setSupermercados(supermercados);
                    return producto;
                })
                .collect(Collectors.toList());

        return productos;
    }
}
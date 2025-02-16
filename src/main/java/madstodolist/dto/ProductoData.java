package madstodolist.dto;

import java.util.List;

public class ProductoData {
    private long id;
    private String nombre;
    private int valorEnergetico;
    private double grasas;
    private double hidratosCarbono;
    private double fibraAlimentaria;
    private double proteinas;
    private double sal;
    private List<String> supermercado;

    // Constructor sin argumentos (necesario para Jackson)
    public ProductoData() {}

    // Constructor con argumentos (opcional, si lo necesitas)
    public ProductoData(long id, String nombre, int valorEnergetico, double grasas, double hidratosCarbono, double fibraAlimentaria, double proteinas, double sal, List<String> supermercado) {
        this.id = id;
        this.nombre = nombre;
        this.valorEnergetico = valorEnergetico;
        this.grasas = grasas;
        this.hidratosCarbono = hidratosCarbono;
        this.fibraAlimentaria = fibraAlimentaria;
        this.proteinas = proteinas;
        this.sal = sal;
        this.supermercado = supermercado;
    }

    // Getters y Setters (necesarios para Jackson)
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getValorEnergetico() { return valorEnergetico; }
    public void setValorEnergetico(int valorEnergetico) { this.valorEnergetico = valorEnergetico; }

    public double getGrasas() { return grasas; }
    public void setGrasas(double grasas) { this.grasas = grasas; }

    public double getHidratosCarbono() { return hidratosCarbono; }
    public void setHidratosCarbono(double hidratosCarbono) { this.hidratosCarbono = hidratosCarbono; }

    public double getFibraAlimentaria() { return fibraAlimentaria; }
    public void setFibraAlimentaria(double fibraAlimentaria) { this.fibraAlimentaria = fibraAlimentaria; }

    public double getProteinas() { return proteinas; }
    public void setProteinas(double proteinas) { this.proteinas = proteinas; }

    public double getSal() { return sal; }
    public void setSal(double sal) { this.sal = sal; }

    public List<String> getSupermercado() { return supermercado; }
    public void setSupermercado(List<String> supermercado) { this.supermercado = supermercado; }
}
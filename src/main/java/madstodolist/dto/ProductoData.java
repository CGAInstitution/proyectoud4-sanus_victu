package madstodolist.dto;

import java.util.List;

public class ProductoData {
    private long id;
    private String nombre;
    private int valorEnergetico;
    private float grasas;
    private float hidratosCarbono;
    private float fibraAlimentaria;
    private float proteinas;
    private float sal;
    private List<String> supermercado;

    // Constructor sin argumentos (necesario para Jackson)
    public ProductoData() {}

    // Constructor con argumentos (opcional, si lo necesitas)


    public ProductoData(long id, String nombre, int valorEnergetico, float grasas, float hidratosCarbono, float fibraAlimentaria, float proteinas, float sal, List<String> supermercado) {
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


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getValorEnergetico() {return valorEnergetico;}
    public void setValorEnergetico(int valorEnergetico) {this.valorEnergetico = valorEnergetico;}

    public float getGrasas() {return grasas;}
    public void setGrasas(float grasas) {this.grasas = grasas;}

    public float getHidratosCarbono() {return hidratosCarbono;}
    public void setHidratosCarbono(float hidratosCarbono) {this.hidratosCarbono = hidratosCarbono;}

    public float getFibraAlimentaria() {return fibraAlimentaria;}
    public void setFibraAlimentaria(float fibraAlimentaria) {this.fibraAlimentaria = fibraAlimentaria;}

    public float getSal() {return sal;}
    public void setSal(float sal) {this.sal = sal;}

    public float getProteinas() {return proteinas;}
    public void setProteinas(float proteinas) {this.proteinas = proteinas;}

    public List<String> getSupermercado() {return supermercado;}
    public void setSupermercado(List<String> supermercado) {this.supermercado = supermercado;}
}
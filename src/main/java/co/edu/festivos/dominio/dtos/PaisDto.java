package co.edu.festivos.dominio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PaisDto {
    private Integer id;

    @NotBlank(message = "El nombre del país no puede estar vacío")
    @Size(max = 100, message = "El nombre del país no puede superar 100 caracteres")
    private String nombre;

    public PaisDto() {}

    public PaisDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

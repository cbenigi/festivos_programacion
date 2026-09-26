package co.edu.festivos.dominio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoFestivoDto {
    private Integer id;

    @NotBlank(message = "El tipo de festivo no puede estar vacío")
    @Size(max = 100, message = "El tipo no puede superar 100 caracteres")
    private String tipo;

    public TipoFestivoDto() {}

    public TipoFestivoDto(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

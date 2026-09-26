package co.edu.festivos.dominio.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FestivoSolicitudDto {

    @NotBlank(message = "El nombre del festivo no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;

    @NotNull(message = "El día es obligatorio (0 si depende únicamente de Pascua)")
    @Min(value = 0, message = "El día no puede ser menor a 0")
    @Max(value = 31, message = "El día no puede ser mayor a 31")
    private Integer dia;

    @NotNull(message = "El mes es obligatorio (0 si depende únicamente de Pascua)")
    @Min(value = 0, message = "El mes no puede ser menor a 0")
    @Max(value = 12, message = "El mes no puede ser mayor a 12")
    private Integer mes;

    @NotNull(message = "Los días de pascua son obligatorios (0 por defecto)")
    private Integer diasPascua;

    @NotNull(message = "El identificador del país es obligatorio")
    private Integer idPais;

    @NotNull(message = "El identificador del tipo de festivo es obligatorio")
    private Integer idTipo;

    public FestivoSolicitudDto() {}

    public FestivoSolicitudDto(String nombre, Integer dia, Integer mes, Integer diasPascua, Integer idPais, Integer idTipo) {
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.idPais = idPais;
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(Integer diasPascua) {
        this.diasPascua = diasPascua;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
}

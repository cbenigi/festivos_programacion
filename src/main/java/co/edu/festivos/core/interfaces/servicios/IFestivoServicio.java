package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.dtos.FestivoRespuestaDto;
import java.time.LocalDate;
import java.util.List;

public interface IFestivoServicio {
    String verificarFecha(Integer idPais, int anio, int mes, int dia);
    List<FestivoRespuestaDto> listarPorAnio(Integer idPais, int anio);
    LocalDate calcularFechaFestivo(int anio, int tipo, int dia, int mes, int diasPascua);
    LocalDate calcularDomingoPascua(int anio);
}

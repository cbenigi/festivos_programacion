package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.dtos.FestivoDetalleDto;
import co.edu.festivos.dominio.dtos.FestivoSolicitudDto;

import java.util.List;

public interface IFestivoCrudServicio {
    List<FestivoDetalleDto> listar();
    FestivoDetalleDto buscarPorId(Integer id);
    FestivoDetalleDto crear(FestivoSolicitudDto solicitud);
    FestivoDetalleDto actualizar(Integer id, FestivoSolicitudDto solicitud);
    void eliminar(Integer id);
}

package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.dtos.PaisDto;
import java.util.List;

public interface IPaisServicio {
    List<PaisDto> listar();
    PaisDto buscarPorId(Integer id);
    PaisDto crear(PaisDto paisDto);
    PaisDto actualizar(Integer id, PaisDto paisDto);
    void eliminar(Integer id);
}

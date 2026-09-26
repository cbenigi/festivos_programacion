package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.dtos.TipoFestivoDto;
import java.util.List;

public interface ITipoFestivoServicio {
    List<TipoFestivoDto> listar();
    TipoFestivoDto buscarPorId(Integer id);
    TipoFestivoDto crear(TipoFestivoDto tipoDto);
    TipoFestivoDto actualizar(Integer id, TipoFestivoDto tipoDto);
    void eliminar(Integer id);
}

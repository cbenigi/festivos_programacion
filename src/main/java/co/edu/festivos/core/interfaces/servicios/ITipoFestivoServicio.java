package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.entidades.TipoFestivo;
import java.util.List;

public interface ITipoFestivoServicio {
    List<TipoFestivo> listar();
    TipoFestivo buscarPorId(Integer id);
    TipoFestivo crear(TipoFestivo tipo);
    TipoFestivo actualizar(Integer id, TipoFestivo tipo);
    void eliminar(Integer id);
}

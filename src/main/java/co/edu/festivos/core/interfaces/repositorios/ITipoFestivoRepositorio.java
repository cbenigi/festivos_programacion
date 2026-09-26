package co.edu.festivos.core.interfaces.repositorios;

import co.edu.festivos.dominio.entidades.TipoFestivo;
import java.util.List;
import java.util.Optional;

public interface ITipoFestivoRepositorio {
    List<TipoFestivo> listarTodos();
    Optional<TipoFestivo> buscarPorId(Integer id);
    TipoFestivo guardar(TipoFestivo tipo);
    void eliminar(Integer id);
}

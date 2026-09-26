package co.edu.festivos.core.interfaces.repositorios;

import co.edu.festivos.dominio.entidades.Festivo;
import java.util.List;
import java.util.Optional;

public interface IFestivoRepositorio {
    List<Festivo> listarTodos();
    List<Festivo> listarPorPais(Integer idPais);
    Optional<Festivo> buscarPorId(Integer id);
    Festivo guardar(Festivo festivo);
    void eliminar(Integer id);
}

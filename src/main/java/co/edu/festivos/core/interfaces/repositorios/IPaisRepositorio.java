package co.edu.festivos.core.interfaces.repositorios;

import co.edu.festivos.dominio.entidades.Pais;
import java.util.List;
import java.util.Optional;

public interface IPaisRepositorio {
    List<Pais> listarTodos();
    Optional<Pais> buscarPorId(Integer id);
    Pais guardar(Pais pais);
    void eliminar(Integer id);
}

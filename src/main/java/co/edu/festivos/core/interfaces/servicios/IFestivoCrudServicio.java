package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.entidades.Festivo;
import java.util.List;

public interface IFestivoCrudServicio {
    List<Festivo> listar();
    Festivo buscarPorId(Integer id);
    Festivo crear(Festivo festivo);
    Festivo actualizar(Integer id, Festivo festivo);
    void eliminar(Integer id);
}

package co.edu.festivos.core.interfaces.servicios;

import co.edu.festivos.dominio.entidades.Pais;
import java.util.List;

public interface IPaisServicio {
    List<Pais> listar();
    Pais buscarPorId(Integer id);
    Pais crear(Pais pais);
    Pais actualizar(Integer id, Pais pais);
    void eliminar(Integer id);
}

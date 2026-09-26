package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.IPaisRepositorio;
import co.edu.festivos.core.interfaces.servicios.IPaisServicio;
import co.edu.festivos.dominio.entidades.Pais;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaisServicioImpl implements IPaisServicio {

    private final IPaisRepositorio paisRepositorio;

    public PaisServicioImpl(IPaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @Override
    public List<Pais> listar() {
        return paisRepositorio.listarTodos();
    }

    @Override
    public Pais buscarPorId(Integer id) {
        return paisRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("País no encontrado con id: " + id));
    }

    @Override
    public Pais crear(Pais pais) {
        pais.setId(null);
        return paisRepositorio.guardar(pais);
    }

    @Override
    public Pais actualizar(Integer id, Pais pais) {
        Pais existente = buscarPorId(id);
        existente.setNombre(pais.getNombre());
        return paisRepositorio.guardar(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        paisRepositorio.eliminar(id);
    }
}

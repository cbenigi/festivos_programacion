package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.ITipoFestivoRepositorio;
import co.edu.festivos.core.interfaces.servicios.ITipoFestivoServicio;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TipoFestivoServicioImpl implements ITipoFestivoServicio {

    private final ITipoFestivoRepositorio tipoRepositorio;

    public TipoFestivoServicioImpl(ITipoFestivoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }

    @Override
    public List<TipoFestivo> listar() {
        return tipoRepositorio.listarTodos();
    }

    @Override
    public TipoFestivo buscarPorId(Integer id) {
        return tipoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de festivo no encontrado con id: " + id));
    }

    @Override
    public TipoFestivo crear(TipoFestivo tipo) {
        tipo.setId(null);
        return tipoRepositorio.guardar(tipo);
    }

    @Override
    public TipoFestivo actualizar(Integer id, TipoFestivo tipo) {
        TipoFestivo existente = buscarPorId(id);
        existente.setTipo(tipo.getTipo());
        return tipoRepositorio.guardar(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        tipoRepositorio.eliminar(id);
    }
}

package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.IFestivoRepositorio;
import co.edu.festivos.core.interfaces.repositorios.IPaisRepositorio;
import co.edu.festivos.core.interfaces.repositorios.ITipoFestivoRepositorio;
import co.edu.festivos.core.interfaces.servicios.IFestivoCrudServicio;
import co.edu.festivos.dominio.entidades.Festivo;
import co.edu.festivos.dominio.entidades.Pais;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FestivoCrudServicioImpl implements IFestivoCrudServicio {

    private final IFestivoRepositorio festivoRepositorio;
    private final IPaisRepositorio paisRepositorio;
    private final ITipoFestivoRepositorio tipoRepositorio;

    public FestivoCrudServicioImpl(
            IFestivoRepositorio festivoRepositorio,
            IPaisRepositorio paisRepositorio,
            ITipoFestivoRepositorio tipoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
        this.paisRepositorio = paisRepositorio;
        this.tipoRepositorio = tipoRepositorio;
    }

    @Override
    public List<Festivo> listar() {
        return festivoRepositorio.listarTodos();
    }

    @Override
    public Festivo buscarPorId(Integer id) {
        return festivoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Festivo no encontrado con id: " + id));
    }

    @Override
    public Festivo crear(Festivo festivo) {
        festivo.setId(null);
        validarRelaciones(festivo);
        return festivoRepositorio.guardar(festivo);
    }

    @Override
    public Festivo actualizar(Integer id, Festivo festivo) {
        Festivo existente = buscarPorId(id);
        validarRelaciones(festivo);
        existente.setNombre(festivo.getNombre());
        existente.setDia(festivo.getDia());
        existente.setMes(festivo.getMes());
        existente.setDiasPascua(festivo.getDiasPascua() != null ? festivo.getDiasPascua() : 0);
        existente.setPais(festivo.getPais());
        existente.setTipo(festivo.getTipo());
        return festivoRepositorio.guardar(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        festivoRepositorio.eliminar(id);
    }

    private void validarRelaciones(Festivo festivo) {
        if (festivo.getPais() != null && festivo.getPais().getId() != null) {
            Pais pais = paisRepositorio.buscarPorId(festivo.getPais().getId())
                    .orElseThrow(() -> new NoSuchElementException("País no encontrado"));
            festivo.setPais(pais);
        }
        if (festivo.getTipo() != null && festivo.getTipo().getId() != null) {
            TipoFestivo tipo = tipoRepositorio.buscarPorId(festivo.getTipo().getId())
                    .orElseThrow(() -> new NoSuchElementException("Tipo no encontrado"));
            festivo.setTipo(tipo);
        }
    }
}

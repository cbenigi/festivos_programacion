package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.IFestivoRepositorio;
import co.edu.festivos.core.interfaces.repositorios.IPaisRepositorio;
import co.edu.festivos.core.interfaces.repositorios.ITipoFestivoRepositorio;
import co.edu.festivos.core.interfaces.servicios.IFestivoCrudServicio;
import co.edu.festivos.dominio.dtos.FestivoDetalleDto;
import co.edu.festivos.dominio.dtos.FestivoSolicitudDto;
import co.edu.festivos.dominio.entidades.Festivo;
import co.edu.festivos.dominio.entidades.Pais;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public List<FestivoDetalleDto> listar() {
        return festivoRepositorio.listarTodos().stream()
                .map(this::aDetalleDto)
                .collect(Collectors.toList());
    }

    @Override
    public FestivoDetalleDto buscarPorId(Integer id) {
        Festivo festivo = festivoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Festivo no encontrado con id: " + id));
        return aDetalleDto(festivo);
    }

    @Override
    public FestivoDetalleDto crear(FestivoSolicitudDto solicitud) {
        Pais pais = paisRepositorio.buscarPorId(solicitud.getIdPais())
                .orElseThrow(() -> new NoSuchElementException("País no encontrado con id: " + solicitud.getIdPais()));
        TipoFestivo tipo = tipoRepositorio.buscarPorId(solicitud.getIdTipo())
                .orElseThrow(() -> new NoSuchElementException("Tipo no encontrado con id: " + solicitud.getIdTipo()));

        Festivo festivo = new Festivo(
                null,
                pais,
                tipo,
                solicitud.getNombre().trim(),
                solicitud.getDia(),
                solicitud.getMes(),
                solicitud.getDiasPascua() != null ? solicitud.getDiasPascua() : 0
        );

        Festivo guardado = festivoRepositorio.guardar(festivo);
        return aDetalleDto(guardado);
    }

    @Override
    public FestivoDetalleDto actualizar(Integer id, FestivoSolicitudDto solicitud) {
        Festivo existente = festivoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Festivo no encontrado con id: " + id));

        Pais pais = paisRepositorio.buscarPorId(solicitud.getIdPais())
                .orElseThrow(() -> new NoSuchElementException("País no encontrado con id: " + solicitud.getIdPais()));
        TipoFestivo tipo = tipoRepositorio.buscarPorId(solicitud.getIdTipo())
                .orElseThrow(() -> new NoSuchElementException("Tipo no encontrado con id: " + solicitud.getIdTipo()));

        existente.setNombre(solicitud.getNombre().trim());
        existente.setDia(solicitud.getDia());
        existente.setMes(solicitud.getMes());
        existente.setDiasPascua(solicitud.getDiasPascua() != null ? solicitud.getDiasPascua() : 0);
        existente.setPais(pais);
        existente.setTipo(tipo);

        Festivo guardado = festivoRepositorio.guardar(existente);
        return aDetalleDto(guardado);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        festivoRepositorio.eliminar(id);
    }

    private FestivoDetalleDto aDetalleDto(Festivo festivo) {
        return new FestivoDetalleDto(
                festivo.getId(),
                festivo.getNombre(),
                festivo.getDia(),
                festivo.getMes(),
                festivo.getDiasPascua(),
                festivo.getPais() != null ? festivo.getPais().getId() : null,
                festivo.getPais() != null ? festivo.getPais().getNombre() : null,
                festivo.getTipo() != null ? festivo.getTipo().getId() : null,
                festivo.getTipo() != null ? festivo.getTipo().getTipo() : null
        );
    }
}

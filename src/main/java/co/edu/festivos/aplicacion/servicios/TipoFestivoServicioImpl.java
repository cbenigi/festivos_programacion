package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.ITipoFestivoRepositorio;
import co.edu.festivos.core.interfaces.servicios.ITipoFestivoServicio;
import co.edu.festivos.dominio.dtos.TipoFestivoDto;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TipoFestivoServicioImpl implements ITipoFestivoServicio {

    private final ITipoFestivoRepositorio tipoRepositorio;

    public TipoFestivoServicioImpl(ITipoFestivoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }

    @Override
    public List<TipoFestivoDto> listar() {
        return tipoRepositorio.listarTodos().stream()
                .map(t -> new TipoFestivoDto(t.getId(), t.getTipo()))
                .collect(Collectors.toList());
    }

    @Override
    public TipoFestivoDto buscarPorId(Integer id) {
        TipoFestivo tipo = tipoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de festivo no encontrado con id: " + id));
        return new TipoFestivoDto(tipo.getId(), tipo.getTipo());
    }

    @Override
    public TipoFestivoDto crear(TipoFestivoDto tipoDto) {
        TipoFestivo nuevo = new TipoFestivo(null, tipoDto.getTipo().trim());
        TipoFestivo guardado = tipoRepositorio.guardar(nuevo);
        return new TipoFestivoDto(guardado.getId(), guardado.getTipo());
    }

    @Override
    public TipoFestivoDto actualizar(Integer id, TipoFestivoDto tipoDto) {
        TipoFestivo existente = tipoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de festivo no encontrado con id: " + id));
        existente.setTipo(tipoDto.getTipo().trim());
        TipoFestivo guardado = tipoRepositorio.guardar(existente);
        return new TipoFestivoDto(guardado.getId(), guardado.getTipo());
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        tipoRepositorio.eliminar(id);
    }
}

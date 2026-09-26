package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.IPaisRepositorio;
import co.edu.festivos.core.interfaces.servicios.IPaisServicio;
import co.edu.festivos.dominio.dtos.PaisDto;
import co.edu.festivos.dominio.entidades.Pais;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PaisServicioImpl implements IPaisServicio {

    private final IPaisRepositorio paisRepositorio;

    public PaisServicioImpl(IPaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @Override
    public List<PaisDto> listar() {
        return paisRepositorio.listarTodos().stream()
                .map(p -> new PaisDto(p.getId(), p.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public PaisDto buscarPorId(Integer id) {
        Pais pais = paisRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("País no encontrado con id: " + id));
        return new PaisDto(pais.getId(), pais.getNombre());
    }

    @Override
    public PaisDto crear(PaisDto paisDto) {
        Pais nuevo = new Pais(null, paisDto.getNombre().trim());
        Pais guardado = paisRepositorio.guardar(nuevo);
        return new PaisDto(guardado.getId(), guardado.getNombre());
    }

    @Override
    public PaisDto actualizar(Integer id, PaisDto paisDto) {
        Pais existente = paisRepositorio.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("País no encontrado con id: " + id));
        existente.setNombre(paisDto.getNombre().trim());
        Pais guardado = paisRepositorio.guardar(existente);
        return new PaisDto(guardado.getId(), guardado.getNombre());
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        paisRepositorio.eliminar(id);
    }
}

package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.ITipoFestivoServicio;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoControlador {

    private final ITipoFestivoServicio tipoServicio;

    public TipoControlador(ITipoFestivoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @GetMapping
    public List<TipoFestivo> listar() {
        return tipoServicio.listar();
    }

    @GetMapping("/{id}")
    public TipoFestivo buscar(@PathVariable Integer id) {
        return tipoServicio.buscarPorId(id);
    }

    @PostMapping
    public TipoFestivo crear(@RequestBody TipoFestivo tipo) {
        return tipoServicio.crear(tipo);
    }

    @PutMapping("/{id}")
    public TipoFestivo actualizar(@PathVariable Integer id, @RequestBody TipoFestivo tipo) {
        return tipoServicio.actualizar(id, tipo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        tipoServicio.eliminar(id);
    }
}

package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IFestivoCrudServicio;
import co.edu.festivos.dominio.entidades.Festivo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoCrudServicio festivoCrudServicio;

    public FestivoControlador(IFestivoCrudServicio festivoCrudServicio) {
        this.festivoCrudServicio = festivoCrudServicio;
    }

    @GetMapping
    public List<Festivo> listar() {
        return festivoCrudServicio.listar();
    }

    @GetMapping("/{id}")
    public Festivo buscar(@PathVariable Integer id) {
        return festivoCrudServicio.buscarPorId(id);
    }

    @PostMapping
    public Festivo crear(@RequestBody Festivo festivo) {
        return festivoCrudServicio.crear(festivo);
    }

    @PutMapping("/{id}")
    public Festivo actualizar(@PathVariable Integer id, @RequestBody Festivo festivo) {
        return festivoCrudServicio.actualizar(id, festivo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        festivoCrudServicio.eliminar(id);
    }
}

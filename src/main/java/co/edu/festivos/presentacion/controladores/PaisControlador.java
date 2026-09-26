package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IPaisServicio;
import co.edu.festivos.dominio.entidades.Pais;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    private final IPaisServicio paisServicio;

    public PaisControlador(IPaisServicio paisServicio) {
        this.paisServicio = paisServicio;
    }

    @GetMapping
    public List<Pais> listar() {
        return paisServicio.listar();
    }

    @GetMapping("/{id}")
    public Pais buscar(@PathVariable Integer id) {
        return paisServicio.buscarPorId(id);
    }

    @PostMapping
    public Pais crear(@RequestBody Pais pais) {
        return paisServicio.crear(pais);
    }

    @PutMapping("/{id}")
    public Pais actualizar(@PathVariable Integer id, @RequestBody Pais pais) {
        return paisServicio.actualizar(id, pais);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        paisServicio.eliminar(id);
    }
}

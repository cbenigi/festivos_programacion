package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IPaisServicio;
import co.edu.festivos.dominio.dtos.PaisDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
@Tag(name = "Países", description = "Operaciones CRUD para la gestión de países")
public class PaisControlador {

    private final IPaisServicio paisServicio;

    public PaisControlador(IPaisServicio paisServicio) {
        this.paisServicio = paisServicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los países")
    public List<PaisDto> listar() {
        return paisServicio.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar país por identificador")
    public PaisDto buscar(@PathVariable Integer id) {
        return paisServicio.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo país")
    public PaisDto crear(@Valid @RequestBody PaisDto paisDto) {
        return paisServicio.crear(paisDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar país existente")
    public PaisDto actualizar(@PathVariable Integer id, @Valid @RequestBody PaisDto paisDto) {
        return paisServicio.actualizar(id, paisDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un país")
    public void eliminar(@PathVariable Integer id) {
        paisServicio.eliminar(id);
    }
}

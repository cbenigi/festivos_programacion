package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IFestivoCrudServicio;
import co.edu.festivos.dominio.dtos.FestivoDetalleDto;
import co.edu.festivos.dominio.dtos.FestivoSolicitudDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivos")
@Tag(name = "Festivos (CRUD)", description = "Operaciones CRUD para la administración de días festivos")
public class FestivoControlador {

    private final IFestivoCrudServicio festivoCrudServicio;

    public FestivoControlador(IFestivoCrudServicio festivoCrudServicio) {
        this.festivoCrudServicio = festivoCrudServicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los festivos configurados")
    public List<FestivoDetalleDto> listar() {
        return festivoCrudServicio.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar festivo por identificador")
    public FestivoDetalleDto buscar(@PathVariable Integer id) {
        return festivoCrudServicio.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo festivo")
    public FestivoDetalleDto crear(@Valid @RequestBody FestivoSolicitudDto solicitud) {
        return festivoCrudServicio.crear(solicitud);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar festivo existente")
    public FestivoDetalleDto actualizar(@PathVariable Integer id, @Valid @RequestBody FestivoSolicitudDto solicitud) {
        return festivoCrudServicio.actualizar(id, solicitud);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un festivo")
    public void eliminar(@PathVariable Integer id) {
        festivoCrudServicio.eliminar(id);
    }
}

package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.ITipoFestivoServicio;
import co.edu.festivos.dominio.dtos.TipoFestivoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "Tipos de Festivo", description = "Operaciones CRUD para los modos de cálculo de festivos")
public class TipoControlador {

    private final ITipoFestivoServicio tipoServicio;

    public TipoControlador(ITipoFestivoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los tipos de festivo")
    public List<TipoFestivoDto> listar() {
        return tipoServicio.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de festivo por identificador")
    public TipoFestivoDto buscar(@PathVariable Integer id) {
        return tipoServicio.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo tipo de festivo")
    public TipoFestivoDto crear(@Valid @RequestBody TipoFestivoDto tipoDto) {
        return tipoServicio.crear(tipoDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de festivo existente")
    public TipoFestivoDto actualizar(@PathVariable Integer id, @Valid @RequestBody TipoFestivoDto tipoDto) {
        return tipoServicio.actualizar(id, tipoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un tipo de festivo")
    public void eliminar(@PathVariable Integer id) {
        tipoServicio.eliminar(id);
    }
}

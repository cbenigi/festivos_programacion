package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IFestivoServicio;
import co.edu.festivos.dominio.dtos.FestivoRespuestaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendario")
@Tag(name = "Calendario y Festivos", description = "Servicios de negocio para verificación y cálculo de días festivos")
public class CalendarioControlador {

    private final IFestivoServicio festivoServicio;

    public CalendarioControlador(IFestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    /**
     * Endpoint solicitado en la guía (Páginas 3 y 4):
     * GET /api/calendario/verificar/{idPais}/{anio}/{mes}/{dia}
     * Respuestas:
     * - "Es Festivo"
     * - "No es festivo"
     * - "Fecha No valida"
     */
    @GetMapping(value = "/verificar/{idPais}/{anio}/{mes}/{dia}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            summary = "Verificar si una fecha es festivo en un país",
            description = "Devuelve texto plano: 'Es Festivo', 'No es festivo' o 'Fecha No valida'."
    )
    @ApiResponse(responseCode = "200", description = "Resultado de la verificación")
    public String verificarFecha(
            @Parameter(description = "ID del país (ej. 1 para Colombia)") @PathVariable Integer idPais,
            @Parameter(description = "Año a consultar (ej. 2023)") @PathVariable int anio,
            @Parameter(description = "Mes a consultar (1 a 12)") @PathVariable int mes,
            @Parameter(description = "Día del mes") @PathVariable int dia) {
        return festivoServicio.verificarFecha(idPais, anio, mes, dia);
    }

    /**
     * Endpoint para listar festivos de un año para un país (Página 4):
     * GET /api/calendario/festivos/{idPais}/{anio}
     */
    @GetMapping("/festivos/{idPais}/{anio}")
    @Operation(summary = "Listar los días festivos de un país en un año específico")
    public List<FestivoRespuestaDto> listarFestivosPorPais(
            @Parameter(description = "ID del país") @PathVariable Integer idPais,
            @Parameter(description = "Año a calcular") @PathVariable int anio) {
        return festivoServicio.listarPorAnio(idPais, anio);
    }

    /**
     * Sobrecarga para Colombia por defecto (idPais = 1) según la URL del ejemplo del PDF:
     * GET /api/calendario/festivos/{anio}
     */
    @GetMapping("/festivos/{anio}")
    @Operation(summary = "Listar los días festivos de Colombia en un año específico")
    public List<FestivoRespuestaDto> listarFestivosColombia(
            @Parameter(description = "Año a calcular") @PathVariable int anio) {
        return festivoServicio.listarPorAnio(1, anio);
    }
}

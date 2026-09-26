package co.edu.festivos.presentacion.controladores;

import co.edu.festivos.core.interfaces.servicios.IFestivoServicio;
import co.edu.festivos.dominio.dtos.FestivoRespuestaDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    private final IFestivoServicio festivoServicio;

    public CalendarioControlador(IFestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    /**
     * Endpoint solicitado en la guía (Página 3 y 4):
     * GET /api/calendario/verificar/{idPais}/{anio}/{mes}/{dia}
     * Respuestas:
     * - "Es Festivo"
     * - "No es festivo"
     * - "Fecha No valida"
     */
    @GetMapping(value = "/verificar/{idPais}/{anio}/{mes}/{dia}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String verificarFecha(
            @PathVariable Integer idPais,
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {
        return festivoServicio.verificarFecha(idPais, anio, mes, dia);
    }

    /**
     * Endpoint para listar festivos de un año para un país (Página 4):
     * GET /api/calendario/festivos/{idPais}/{anio}
     */
    @GetMapping("/festivos/{idPais}/{anio}")
    public List<FestivoRespuestaDto> listarFestivosPorPais(
            @PathVariable Integer idPais,
            @PathVariable int anio) {
        return festivoServicio.listarPorAnio(idPais, anio);
    }

    /**
     * Sobrecarga para Colombia por defecto (idPais = 1) según el URL del PDF:
     * GET /api/calendario/festivos/{anio}
     */
    @GetMapping("/festivos/{anio}")
    public List<FestivoRespuestaDto> listarFestivosColombia(
            @PathVariable int anio) {
        return festivoServicio.listarPorAnio(1, anio);
    }
}

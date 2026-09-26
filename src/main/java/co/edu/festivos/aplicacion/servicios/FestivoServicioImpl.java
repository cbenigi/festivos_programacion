package co.edu.festivos.aplicacion.servicios;

import co.edu.festivos.core.interfaces.repositorios.IFestivoRepositorio;
import co.edu.festivos.core.interfaces.servicios.IFestivoServicio;
import co.edu.festivos.dominio.dtos.FestivoRespuestaDto;
import co.edu.festivos.dominio.entidades.Festivo;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FestivoServicioImpl implements IFestivoServicio {

    private final IFestivoRepositorio festivoRepositorio;

    public FestivoServicioImpl(IFestivoRepositorio festivoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
    }

    @Override
    public String verificarFecha(Integer idPais, int anio, int mes, int dia) {
        LocalDate fechaConsultada;
        try {
            fechaConsultada = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            return "Fecha No valida";
        }

        List<Festivo> festivos = festivoRepositorio.listarPorPais(idPais);

        boolean esFestivo = festivos.stream().anyMatch(f -> {
            LocalDate fechaCalculada = calcularFechaFestivo(
                    anio,
                    f.getTipo().getId(),
                    f.getDia(),
                    f.getMes(),
                    f.getDiasPascua()
            );
            return fechaCalculada.equals(fechaConsultada);
        });

        return esFestivo ? "Es Festivo" : "No es festivo";
    }

    @Override
    public List<FestivoRespuestaDto> listarPorAnio(Integer idPais, int anio) {
        List<Festivo> festivos = festivoRepositorio.listarPorPais(idPais);

        return festivos.stream()
                .map(f -> {
                    LocalDate fecha = calcularFechaFestivo(
                            anio,
                            f.getTipo().getId(),
                            f.getDia(),
                            f.getMes(),
                            f.getDiasPascua()
                    );
                    return new FestivoRespuestaDto(f.getNombre(), fecha.toString());
                })
                .sorted(Comparator.comparing(FestivoRespuestaDto::getFecha))
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate calcularFechaFestivo(int anio, int tipo, int dia, int mes, int diasPascua) {
        LocalDate fecha;

        switch (tipo) {
            case 1:
                // Tipo 1: Fijo
                fecha = LocalDate.of(anio, mes, dia);
                break;

            case 2:
                // Tipo 2: Ley de "Puente festivo" (se traslada al siguiente lunes si no cae lunes)
                fecha = trasladarAlSiguienteLunes(LocalDate.of(anio, mes, dia));
                break;

            case 3:
                // Tipo 3: Basado en el domingo de pascua
                fecha = calcularDomingoPascua(anio).plusDays(diasPascua);
                break;

            case 4:
                // Tipo 4: Basado en pascua y Ley de "Puente festivo"
                fecha = trasladarAlSiguienteLunes(calcularDomingoPascua(anio).plusDays(diasPascua));
                break;

            default:
                fecha = LocalDate.of(anio, mes == 0 ? 1 : mes, dia == 0 ? 1 : dia);
                break;
        }

        return fecha;
    }

    @Override
    public LocalDate calcularDomingoPascua(int anio) {
        /*
         * Fórmula exacta de la guía Pascual Bravo (Página 2):
         * a = Año MOD 19
         * b = Año MOD 4
         * c = Año MOD 7
         * d = (19*a + 24) MOD 30
         * dias = d + (2*b + 4*c + 6*d + 5) MOD 7
         * Domingo de Ramos = 15 de marzo + dias
         * Domingo de Pascua = Domingo de Ramos + 7 días
         */
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        LocalDate domingoRamos = LocalDate.of(anio, 3, 15).plusDays(dias);
        return domingoRamos.plusDays(7);
    }

    private LocalDate trasladarAlSiguienteLunes(LocalDate fecha) {
        if (fecha.getDayOfWeek() == DayOfWeek.MONDAY) {
            return fecha;
        }
        while (fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
            fecha = fecha.plusDays(1);
        }
        return fecha;
    }
}

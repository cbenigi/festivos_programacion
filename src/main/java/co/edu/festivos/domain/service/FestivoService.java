package co.edu.festivos.domain.service;

import co.edu.festivos.infrastructure.persistence.entity.FestivoEntity;
import co.edu.festivos.infrastructure.persistence.repository.FestivoJpaRepository;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FestivoService {
    private final FestivoJpaRepository repository;
    public FestivoService(FestivoJpaRepository repository) { this.repository = repository; }

    public Map<String, Object> validar(Integer idPais, String textoFecha) {
        LocalDate fecha = LocalDate.parse(textoFecha);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", fecha.toString());
        Optional<FestivoEntity> festivo = repository.findByPaisId(idPais).stream()
                .filter(item -> calcular(item, fecha.getYear()).equals(fecha)).findFirst();
        respuesta.put("esFestivo", festivo.isPresent());
        festivo.ifPresent(item -> respuesta.put("nombre", item.getNombre()));
        return respuesta;
    }

    public List<Map<String, Object>> listarPorAnio(Integer idPais, Integer anio) {
        return repository.findByPaisId(idPais).stream().map(item -> {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre", item.getNombre());
            respuesta.put("fecha", calcular(item, anio).toString());
            return respuesta;
        }).sorted(Comparator.comparing(item -> item.get("fecha").toString())).collect(Collectors.toList());
    }

    private LocalDate calcular(FestivoEntity item, int anio) {
        int tipo = item.getTipo().getId();
        LocalDate fecha = LocalDate.of(anio, item.getMes() == 0 ? 3 : item.getMes(), item.getDia() == 0 ? 1 : item.getDia());
        if (tipo == 3 || tipo == 4) fecha = pascua(anio).plusDays(item.getDiasPascua());
        if (tipo == 2 || tipo == 4) while (fecha.getDayOfWeek() != DayOfWeek.MONDAY) fecha = fecha.plusDays(1);
        if (tipo == 5) while (fecha.getDayOfWeek() != DayOfWeek.FRIDAY) fecha = fecha.plusDays(1);
        return fecha;
    }

    private LocalDate pascua(int anio) {
        int a = anio % 19, b = anio % 4, c = anio % 7, d = (19 * a + 24) % 30;
        return LocalDate.of(anio, 3, 15).plusDays(d + (2 * b + 4 * c + 6 * d + 5) % 7 + 7);
    }
}

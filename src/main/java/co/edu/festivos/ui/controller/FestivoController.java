package co.edu.festivos.ui.controller;
import co.edu.festivos.infrastructure.persistence.entity.*;
import co.edu.festivos.infrastructure.persistence.repository.*;
import co.edu.festivos.domain.service.FestivoService;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;
@RestController @RequestMapping("/api/festivos")
public class FestivoController {
    private final FestivoJpaRepository festivos; private final PaisJpaRepository paises; private final TipoJpaRepository tipos; private final FestivoService service;
    public FestivoController(FestivoJpaRepository f,PaisJpaRepository p,TipoJpaRepository t,FestivoService s){festivos=f;paises=p;tipos=t;service=s;}
    @GetMapping public List<FestivoEntity> listar(){return festivos.findAll();}
    @GetMapping("/{id}") public FestivoEntity buscar(@PathVariable Integer id){return festivos.findById(id).orElseThrow();}
    @PostMapping public FestivoEntity crear(@RequestBody Map<String,Object> d){return guardar(new FestivoEntity(),d);}
    @PutMapping("/{id}") public FestivoEntity actualizar(@PathVariable Integer id,@RequestBody Map<String,Object> d){return guardar(buscar(id),d);}
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Integer id){festivos.deleteById(id);}
    @GetMapping("/{idPais}/fecha/{fecha}") public Map<String,Object> validar(@PathVariable Integer idPais,@PathVariable String fecha){return service.validar(idPais,fecha);}
    @GetMapping("/{idPais}/anio/{anio}") public List<Map<String,Object>> anio(@PathVariable Integer idPais,@PathVariable Integer anio){return service.listarPorAnio(idPais,anio);}
    private FestivoEntity guardar(FestivoEntity f,Map<String,Object>d){f.setNombre((String)d.get("nombre"));f.setDia((Integer)d.get("dia"));f.setMes((Integer)d.get("mes"));f.setDiasPascua((Integer)d.getOrDefault("diasPascua",0));f.setPais(paises.findById((Integer)d.get("idPais")).orElseThrow());f.setTipo(tipos.findById((Integer)d.get("idTipo")).orElseThrow());return festivos.save(f);}
}

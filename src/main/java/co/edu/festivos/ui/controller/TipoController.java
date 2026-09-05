package co.edu.festivos.ui.controller;
import co.edu.festivos.infrastructure.persistence.entity.TipoEntity;
import co.edu.festivos.infrastructure.persistence.repository.TipoJpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/tipos")
public class TipoController {
    private final TipoJpaRepository repo;
    public TipoController(TipoJpaRepository repo){this.repo=repo;}
    @GetMapping public List<TipoEntity> listar(){return repo.findAll();}
    @GetMapping("/{id}") public TipoEntity buscar(@PathVariable Integer id){return repo.findById(id).orElseThrow();}
    @PostMapping public TipoEntity crear(@RequestBody TipoEntity tipo){tipo.setId(null);return repo.save(tipo);}
    @PutMapping("/{id}") public TipoEntity actualizar(@PathVariable Integer id,@RequestBody TipoEntity datos){TipoEntity t=buscar(id);t.setNombre(datos.getNombre());return repo.save(t);}
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Integer id){repo.deleteById(id);}
}

package co.edu.festivos.ui.controller;
import co.edu.festivos.infrastructure.persistence.entity.PaisEntity;
import co.edu.festivos.infrastructure.persistence.repository.PaisJpaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/paises")
public class PaisController {
    private final PaisJpaRepository repo;
    public PaisController(PaisJpaRepository repo){this.repo=repo;}
    @GetMapping public List<PaisEntity> listar(){return repo.findAll();}
    @GetMapping("/{id}") public PaisEntity buscar(@PathVariable Integer id){return repo.findById(id).orElseThrow();}
    @PostMapping public PaisEntity crear(@RequestBody PaisEntity pais){pais.setId(null);return repo.save(pais);}
    @PutMapping("/{id}") public PaisEntity actualizar(@PathVariable Integer id,@RequestBody PaisEntity datos){PaisEntity p=buscar(id);p.setNombre(datos.getNombre());return repo.save(p);}
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Integer id){repo.deleteById(id);}
}

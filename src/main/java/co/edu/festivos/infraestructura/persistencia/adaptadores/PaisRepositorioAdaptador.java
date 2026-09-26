package co.edu.festivos.infraestructura.persistencia.adaptadores;

import co.edu.festivos.core.interfaces.repositorios.IPaisRepositorio;
import co.edu.festivos.dominio.entidades.Pais;
import co.edu.festivos.infraestructura.persistencia.mapeadores.EntidadMapeador;
import co.edu.festivos.infrastructure.persistence.entity.PaisEntity;
import co.edu.festivos.infrastructure.persistence.repository.PaisJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PaisRepositorioAdaptador implements IPaisRepositorio {

    private final PaisJpaRepository jpaRepository;

    public PaisRepositorioAdaptador(PaisJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Pais> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(EntidadMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pais> buscarPorId(Integer id) {
        return jpaRepository.findById(id).map(EntidadMapeador::aDominio);
    }

    @Override
    public Pais guardar(Pais pais) {
        PaisEntity entity = EntidadMapeador.aEntidad(pais);
        return EntidadMapeador.aDominio(jpaRepository.save(entity));
    }

    @Override
    public void eliminar(Integer id) {
        jpaRepository.deleteById(id);
    }
}

package co.edu.festivos.infraestructura.persistencia.adaptadores;

import co.edu.festivos.core.interfaces.repositorios.ITipoFestivoRepositorio;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import co.edu.festivos.infraestructura.persistencia.mapeadores.EntidadMapeador;
import co.edu.festivos.infrastructure.persistence.entity.TipoEntity;
import co.edu.festivos.infrastructure.persistence.repository.TipoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TipoFestivoRepositorioAdaptador implements ITipoFestivoRepositorio {

    private final TipoJpaRepository jpaRepository;

    public TipoFestivoRepositorioAdaptador(TipoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<TipoFestivo> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(EntidadMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoFestivo> buscarPorId(Integer id) {
        return jpaRepository.findById(id).map(EntidadMapeador::aDominio);
    }

    @Override
    public TipoFestivo guardar(TipoFestivo tipo) {
        TipoEntity entity = EntidadMapeador.aEntidad(tipo);
        return EntidadMapeador.aDominio(jpaRepository.save(entity));
    }

    @Override
    public void eliminar(Integer id) {
        jpaRepository.deleteById(id);
    }
}

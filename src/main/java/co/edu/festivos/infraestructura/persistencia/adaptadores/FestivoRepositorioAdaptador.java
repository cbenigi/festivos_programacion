package co.edu.festivos.infraestructura.persistencia.adaptadores;

import co.edu.festivos.core.interfaces.repositorios.IFestivoRepositorio;
import co.edu.festivos.dominio.entidades.Festivo;
import co.edu.festivos.infraestructura.persistencia.mapeadores.EntidadMapeador;
import co.edu.festivos.infrastructure.persistence.entity.FestivoEntity;
import co.edu.festivos.infrastructure.persistence.repository.FestivoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FestivoRepositorioAdaptador implements IFestivoRepositorio {

    private final FestivoJpaRepository jpaRepository;

    public FestivoRepositorioAdaptador(FestivoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Festivo> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(EntidadMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Festivo> listarPorPais(Integer idPais) {
        return jpaRepository.findByPaisId(idPais).stream()
                .map(EntidadMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Festivo> buscarPorId(Integer id) {
        return jpaRepository.findById(id).map(EntidadMapeador::aDominio);
    }

    @Override
    public Festivo guardar(Festivo festivo) {
        FestivoEntity entity = EntidadMapeador.aEntidad(festivo);
        return EntidadMapeador.aDominio(jpaRepository.save(entity));
    }

    @Override
    public void eliminar(Integer id) {
        jpaRepository.deleteById(id);
    }
}

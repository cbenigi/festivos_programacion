package co.edu.festivos.infraestructura.persistencia.mapeadores;

import co.edu.festivos.dominio.entidades.Festivo;
import co.edu.festivos.dominio.entidades.Pais;
import co.edu.festivos.dominio.entidades.TipoFestivo;
import co.edu.festivos.infrastructure.persistence.entity.FestivoEntity;
import co.edu.festivos.infrastructure.persistence.entity.PaisEntity;
import co.edu.festivos.infrastructure.persistence.entity.TipoEntity;

public final class EntidadMapeador {

    private EntidadMapeador() {}

    public static Pais aDominio(PaisEntity entity) {
        if (entity == null) return null;
        return new Pais(entity.getId(), entity.getNombre());
    }

    public static PaisEntity aEntidad(Pais dominio) {
        if (dominio == null) return null;
        PaisEntity entity = new PaisEntity();
        entity.setId(dominio.getId());
        entity.setNombre(dominio.getNombre());
        return entity;
    }

    public static TipoFestivo aDominio(TipoEntity entity) {
        if (entity == null) return null;
        return new TipoFestivo(entity.getId(), entity.getNombre());
    }

    public static TipoEntity aEntidad(TipoFestivo dominio) {
        if (dominio == null) return null;
        TipoEntity entity = new TipoEntity();
        entity.setId(dominio.getId());
        entity.setNombre(dominio.getTipo());
        return entity;
    }

    public static Festivo aDominio(FestivoEntity entity) {
        if (entity == null) return null;
        return new Festivo(
                entity.getId(),
                aDominio(entity.getPais()),
                aDominio(entity.getTipo()),
                entity.getNombre(),
                entity.getDia(),
                entity.getMes(),
                entity.getDiasPascua()
        );
    }

    public static FestivoEntity aEntidad(Festivo dominio) {
        if (dominio == null) return null;
        FestivoEntity entity = new FestivoEntity();
        entity.setId(dominio.getId());
        entity.setNombre(dominio.getNombre());
        entity.setDia(dominio.getDia());
        entity.setMes(dominio.getMes());
        entity.setDiasPascua(dominio.getDiasPascua() != null ? dominio.getDiasPascua() : 0);
        entity.setPais(aEntidad(dominio.getPais()));
        entity.setTipo(aEntidad(dominio.getTipo()));
        return entity;
    }
}

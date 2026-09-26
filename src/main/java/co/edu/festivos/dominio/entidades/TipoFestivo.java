package co.edu.festivos.dominio.entidades;

public class TipoFestivo {
    private Integer id;
    private String tipo;

    public TipoFestivo() {}

    public TipoFestivo(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

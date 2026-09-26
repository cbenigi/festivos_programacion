package co.edu.festivos.dominio.entidades;

public class Festivo {
    private Integer id;
    private Pais pais;
    private TipoFestivo tipo;
    private String nombre;
    private Integer dia;
    private Integer mes;
    private Integer diasPascua;

    public Festivo() {}

    public Festivo(Integer id, Pais pais, TipoFestivo tipo, String nombre, Integer dia, Integer mes, Integer diasPascua) {
        this.id = id;
        this.pais = pais;
        this.tipo = tipo;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public TipoFestivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoFestivo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(Integer diasPascua) {
        this.diasPascua = diasPascua;
    }
}

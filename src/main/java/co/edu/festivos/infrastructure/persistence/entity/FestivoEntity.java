package co.edu.festivos.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity @Table(name = "festivo")
public class FestivoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "idpais")
    private PaisEntity pais;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "idtipo")
    private TipoEntity tipo;
    @Column(nullable = false, length = 100) private String nombre;
    @Column(nullable = false) private Integer dia;
    @Column(nullable = false) private Integer mes;
    @Column(name = "diaspascua", nullable = false) private Integer diasPascua;
    public Integer getId(){return id;} public void setId(Integer v){id=v;}
    public PaisEntity getPais(){return pais;} public void setPais(PaisEntity v){pais=v;}
    public TipoEntity getTipo(){return tipo;} public void setTipo(TipoEntity v){tipo=v;}
    public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
    public Integer getDia(){return dia;} public void setDia(Integer v){dia=v;}
    public Integer getMes(){return mes;} public void setMes(Integer v){mes=v;}
    public Integer getDiasPascua(){return diasPascua;} public void setDiasPascua(Integer v){diasPascua=v;}
}

package co.edu.festivos.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity @Table(name = "tipo")
public class TipoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo", nullable = false, length = 100)
    private String nombre;
    public TipoEntity() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

package co.edu.festivos.dominio.dtos;

public class FestivoRespuestaDto {
    private String festivo;
    private String fecha;

    public FestivoRespuestaDto() {
    }

    public FestivoRespuestaDto(String festivo, String fecha) {
        this.festivo = festivo;
        this.fecha = fecha;
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

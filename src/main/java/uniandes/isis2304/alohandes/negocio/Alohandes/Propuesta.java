package uniandes.isis2304.alohandes.negocio.Alohandes;

public class Propuesta implements VOPropuesta {
    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private long id;
    private String titulo;
    private String descripcion;
    private long idAlojamiento;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    public Propuesta() {
        this.id = 0;
        this.titulo = "";
        this.descripcion = "";
        this.idAlojamiento = 0;
    }

    public Propuesta(long id, String titulo, String descripcion, long idAlojamiento) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idAlojamiento = idAlojamiento;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public long getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    @Override
    public String toString() {
        return "Propuesta [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion
                + ", idAlojamiento=" + idAlojamiento + "]";
    }
}


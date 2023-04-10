package uniandes.isis2304.alohandes.negocio.Alohandes;

public class Servicio implements VOServicio {
    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private long id;
    private String nombreServicio;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    public Servicio() {
        this.id = 0;
        this.nombreServicio = "";
    }

    public Servicio(long id, String nombreServicio) {
        this.id = id;
        this.nombreServicio = nombreServicio;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    @Override
    public String toString() {
        return "Servicio [id=" + id + ", nombreServicio=" + nombreServicio + "]";
    }
}

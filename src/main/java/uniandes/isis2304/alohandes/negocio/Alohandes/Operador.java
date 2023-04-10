package uniandes.isis2304.alohandes.negocio.Alohandes;

public class Operador implements VOOperador {
    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private long id;
    private String nombre;
    private int codigo_camara_comercio;
    private int registro_superintendencia;
    private int cedula;
    private String tipo;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    public Operador(long idOperador, String nombre2, String string, String codigoCamaraComercio, String registroSuperintendencia, Object object) {
        this.id = 0;
        this.nombre = "";
        this.codigo_camara_comercio = 0;
        this.registro_superintendencia = 0;
        this.cedula = 0;
        this.tipo = "";
    }

    public Operador(long id, String nombre, int codigo_camara_comercio, int registro_superintendencia, int cedula, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo_camara_comercio = codigo_camara_comercio;
        this.registro_superintendencia = registro_superintendencia;
        this.cedula = cedula;
        this.tipo = tipo;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int getCodigoCamaraComercio() {
        return codigo_camara_comercio;
    }

    public void setCodigoCamaraComercio(int codigo_camara_comercio) {
        this.codigo_camara_comercio = codigo_camara_comercio;
    }

    @Override
    public int getRegistroSuperintendencia() {
        return registro_superintendencia;
    }

    public void setRegistroSuperintendencia(int registro_superintendencia) {
        this.registro_superintendencia = registro_superintendencia;
    }

    @Override
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Operador [id=" + id + ", nombre=" + nombre + ", codigo_camara_comercio=" + codigo_camara_comercio
                + ", registro_superintendencia=" + registro_superintendencia + ", cedula=" + cedula + ", tipo=" + tipo + "]";
    }
}

package uniandes.isis2304.alohandes.negocio.Alohandes;

public class Usuario implements VOUsuario {
    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private int id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private int telefono;
    private String tipo;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    public Usuario() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.correoElectronico = "";
        this.telefono = 0;
        this.tipo = "";
    }

    public Usuario(int idUsuario, String nombre, String apellido, String correoElectronico, int telefono, String tipo) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correoElectronico=" + correoElectronico
                + ", telefono=" + telefono + ", tipo=" + tipo + "]";
    }
}

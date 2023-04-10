package uniandes.isis2304.alohandes.negocio.Alohandes;

public class Alojamiento implements VOAlojamiento {
    /* ****************************************************************
     *          Atributos
     *****************************************************************/
    private long id;
    private long id_operador;
    private String tipo;
    private int capacidad;
    private String ubicacion;
    private int tamano;
    private Long precio;
    private char internet;
    private char sala;
    private char cocina;
    private char compartida;
    private char bano_privado;

    /* ****************************************************************
     *          Métodos
     *****************************************************************/
    public Alojamiento() {
        this.id = 0;
        this.id_operador = 0;
        this.tipo = "";
        this.capacidad = 0;
        this.ubicacion = "";
        this.tamano = 0;
        this.precio = 0L;
        this.internet = 'N';
        this.sala = 'N';
        this.cocina = 'N';
        this.compartida = 'N';
        this.bano_privado = 'N';
    }

    public Alojamiento(long id, long id_operador, String tipo, int capacidad, String ubicacion, int tamano, Long precio,
                       char internet, char sala, char cocina, char compartida, char bano_privado) {
        this.id = id;
        this.id_operador = id_operador;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.tamano = tamano;
        this.precio = precio;
        this.internet = internet;
        this.sala = sala;
        this.cocina = cocina;
        this.compartida = compartida;
        this.bano_privado = bano_privado;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getIdOperador() {
        return id_operador;
    }

    public void setIdOperador(long id_operador) {
        this.id_operador = id_operador;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    @Override
    public char tieneInternet() {
        return internet;
    }

    public void setInternet(char internet) {
        this.internet = internet;
    }

    @Override
    public char tieneSala() {
        return sala;
    }

    public void setSala(char sala) {
        this.sala = sala;
    }

    @Override
    public char tieneCocina() {
        return cocina;
    }

    public void setCocina(char cocina) {
        this.cocina = cocina;
    }

    @Override
    public char esCompartido() {
        return compartida;
    }
    
    public void setCompartida(char compartida) {
        this.compartida = compartida;
    }
    
    @Override
    public char tieneBanoPrivado() {
        return bano_privado;
    }
    
    public void setBanoPrivado(char bano_privado) {
        this.bano_privado = bano_privado;
    }
    
    @Override
    public String toString() {
        return "Alojamiento [id=" + id + ", id_operador=" + id_operador + ", tipo=" + tipo + ", capacidad=" + capacidad
                + ", ubicacion=" + ubicacion + ", tamano=" + tamano + ", precio=" + precio + ", internet=" + internet
                + ", sala=" + sala + ", cocina=" + cocina + ", compartida=" + compartida + ", bano_privado=" + bano_privado
                + "]";
    }
}
    
package uniandes.isis2304.alohandes.negocio.Alohandes;

public interface VOOperador {

    /**
     * @return El id del operador
     */
    public long getId();

    /**
     * @return el nombre del operador
     */
    public String getNombre();

    /**
     * @return el código de la cámara de comercio del operador
     */
    public int getCodigoCamaraComercio();

    /**
     * @return el registro de la superintendencia del operador
     */
    public int getRegistroSuperintendencia();

    /**
     * @return la cédula del operador
     */
    public int getCedula();

    /**
     * @return el tipo del operador (hotel, hostal, aloha)
     */
    public String getTipo();

    /**
     * @return Una cadena de caracteres con todos los atributos del operador
     */
    public String toString();
}


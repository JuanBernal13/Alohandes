package uniandes.isis2304.alohandes.negocio.Alohandes;

public interface VOUsuario {
    
    /**
     * @return El id del usuario
     */
    public int getId();
    
    /**
     * @return El nombre del usuario
     */
    public String getNombre();
    
    /**
     * @return El apellido del usuario
     */
    public String getApellido();
    
    /**
     * @return El correo electrónico del usuario
     */
    public String getCorreoElectronico();
    
    /**
     * @return El teléfono del usuario
     */
    public int getTelefono();
    
    /**
     * @return El tipo de usuario (estudiante, egresado, empleado, profesor, padre de estudiante, profesor invitado, persona registrada)
     */
    public String getTipo();
    
    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del usuario
     */
    public String toString();
}

package uniandes.isis2304.alohandes.negocio.Alohandes;

public interface VOPropuesta {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id de la propuesta
	 */
	public long getId();
	
	/**
	 * @return El titulo de la propuesta
	 */
	public String getTitulo();
	
	/**
	 * @return La descripcion de la propuesta
	 */
	public String getDescripcion();
	
	/**
	 * @return El id del alojamiento asociado a la propuesta
	 */
	public long getIdAlojamiento();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la propuesta
	 */
	public String toString();
	
}


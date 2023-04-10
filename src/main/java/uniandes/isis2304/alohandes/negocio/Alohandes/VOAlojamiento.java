package uniandes.isis2304.alohandes.negocio.Alohandes;

public interface VOAlojamiento {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id del alojamiento
	 */
	public long getId();
	
	/**
	 * @return El id del operador del alojamiento
	 */
	public long getIdOperador();
	
	/**
	 * @return El tipo de alojamiento (hotel, hostal, aloha o alojamiento)
	 */
	public String getTipo();
	
	/**
	 * @return La capacidad del alojamiento
	 */
	public int getCapacidad();
	
	/**
	 * @return La ubicación del alojamiento
	 */
	public String getUbicacion();
	
	/**
	 * @return El tamaño del alojamiento
	 */
	public int getTamano();
	
	/**
	 * @return El precio del alojamiento
	 */
	public Long getPrecio();
	
	/**
	 * @return Si el alojamiento tiene servicio de internet
	 */
	public char tieneInternet();
	
	/**
	 * @return Si el alojamiento tiene sala de estar
	 */
	public char tieneSala();
	
	/**
	 * @return Si el alojamiento tiene cocina
	 */
	public char tieneCocina();
	
	/**
	 * @return Si el alojamiento es compartido
	 */
	public char esCompartido();
	
	/**
	 * @return Si el alojamiento tiene baño privado
	 */
	public char tieneBanoPrivado();

}


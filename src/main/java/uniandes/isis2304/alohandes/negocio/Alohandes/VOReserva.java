package uniandes.isis2304.alohandes.negocio.Alohandes;
import java.util.Date;

public interface VOReserva {
    /**
     * @return El id de la reserva
     */
    public long getId();

    /**
     * @return El id del usuario que hizo la reserva
     */
    public long getUserId();

    /**
     * @return El id del alojamiento reservado
     */
    public long getAlojamientoId();

    /**
     * @return La fecha de inicio de la reserva
     */
    public Date getFechaInicial();

    /**
     * @return La fecha de finalización de la reserva
     */
    public Date getFechaFinal();

    /**
     * @return El costo total de la reserva
     */
    public int getCosto();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la reserva
     */
    public String toString();
}

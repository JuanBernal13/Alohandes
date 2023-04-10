package uniandes.isis2304.alohandes.persistencia.Alohandes;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Aloha
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLUtil {
    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaAlohandes.SQL;

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia general de la aplicación
     */
    private PersistenciaAlohandes pa;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/

    /**
     * Constructor
     * @param pa - El Manejador de persistencia de la aplicación
     */
    public SQLUtil(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
     * @param pm - El manejador de persistencia
     * @return El número de secuencia generado
     */
    public long nextval(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT " + pa.darSeqAlohandes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
    }

    /**
     * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
     * @param pm - El manejador de persistencia
     * @return Un arreglo con números que indican el número de tuplas borradas en las tablas de Alohandes
     */
    /**
 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
 * @param pm - El manejador de persistencia
 * @return Un arreglo con números que indican el número de tuplas borradas en las tablas de Alohandes
 */
public long[] limpiarAlohandes(PersistenceManager pm) {
    Query qReservas = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva());
    Query qPropuestas = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaPropuesta());
    Query qServicios = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicio());
    Query qAlojamiento = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamiento());
    Query qOperador = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador());
    Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaUsuario());

    long reservasEliminadas = (long) qReservas.executeUnique();
    long propuestasEliminadas = (long) qPropuestas.executeUnique();
    long serviciosEliminados = (long) qServicios.executeUnique();
    long alojamientosEliminados = (long) qAlojamiento.executeUnique();
    long operadoresEliminados = (long) qOperador.executeUnique();
    long usuariosEliminados = (long) qUsuario.executeUnique();
    
    return new long[] {reservasEliminadas, propuestasEliminadas, serviciosEliminados, alojamientosEliminados, operadoresEliminados, usuariosEliminados};
}

}

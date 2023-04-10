package uniandes.isis2304.alohandes.persistencia.Alohandes;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;


import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.negocio.Alohandes.Alojamiento;
import uniandes.isis2304.alohandes.negocio.Alohandes.Operador;
import uniandes.isis2304.alohandes.negocio.Alohandes.Propuesta;
import uniandes.isis2304.alohandes.negocio.Alohandes.Reserva;
import uniandes.isis2304.alohandes.negocio.Alohandes.Servicio;
import uniandes.isis2304.alohandes.negocio.Alohandes.Usuario;



public class PersistenciaAlohandes {
    /* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;

    private SQLUtil sqlUtil;

    private SQLAlojamiento sqlAlojamiento;

    private SQLOperador sqlOperador;

    private SQLPropuesta sqlPropuesta;

    private SQLReserva sqlReserva;

    private SQLServicio sqlServicio;

    private SQLUsuario sqlUsuario;

    private PersistenciaAlohandes() {
        pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");
        crearClasesSQL();
    
        // Define los nombres por defecto de las tablas de la base de datos
        tablas = new LinkedList<String>();
        tablas.add("Alohandes_sequence");
        tablas.add("Alojamiento");
        tablas.add("Operador");
        tablas.add("Propuestas");
        tablas.add("Reservas");
        tablas.add("Servicios");
        tablas.add("Usuario");
    }
    
    private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

    public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}

    public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}

    public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

    private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}

    private void crearClasesSQL() {
        sqlAlojamiento = new SQLAlojamiento(this);
        sqlOperador = new SQLOperador(this);
        sqlPropuesta = new SQLPropuesta(this);
        sqlReserva = new SQLReserva(this);
        sqlServicio = new SQLServicio(this);
        sqlUsuario = new SQLUsuario(this);
        sqlUtil = new SQLUtil(this);
    }
    
    public String darSeqAlohandes() {
        return tablas.get(0);
    }
    
    public String darTablaAlojamiento() {
        return tablas.get(1);
    }
    
    public String darTablaOperador() {
        return tablas.get(2);
    }
    
    public String darTablaPropuesta() {
        return tablas.get(3);
    }
    
    public String darTablaReserva() {
        return tablas.get(4);
    }
    
    public String darTablaServicio() {
        return tablas.get(5);
    }
    
    public String darTablaUsuario() {
        return tablas.get(6);
    }
    
    private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
    private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

    public Usuario adicionarUsuario(String nombre, String apellido, String correoElectronico, int telefono, String tipo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long idUsuario = nextval();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, (int) idUsuario, nombre, apellido, correoElectronico, telefono, tipo);
            tx.commit();
    
            log.trace("Inserción de usuario: " + nombre + " " + apellido + ": " + tuplasInsertadas + " tuplas insertadas");
    
            return new Usuario((int) idUsuario, nombre, apellido, correoElectronico, telefono, tipo);
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long eliminarUsuarioPorId(long idUsuario) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorId(pm, (int) idUsuario);
            tx.commit();
            return resp;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public List<Usuario> darUsuarios() {
        return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
    }
    
    public long actualizarNombreUsuario(long idUsuario, String nuevoNombre) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarNombreUsuario(pm, (int) idUsuario, nuevoNombre);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarApellidoUsuario(long idUsuario, String nuevoApellido) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarApellidoUsuario(pm, (int) idUsuario, nuevoApellido);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarCorreoElectronicoUsuario(long idUsuario, String nuevoCorreoElectronico) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarCorreoElectronicoUsuario(pm, (int) idUsuario, nuevoCorreoElectronico);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarTelefonoUsuario(long idUsuario, int nuevoTelefono) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarTelefonoUsuario(pm, (int) idUsuario, nuevoTelefono);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarTipoUsuario(long idUsuario, String nuevoTipo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarTipoUsuario(pm, (int) idUsuario, nuevoTipo);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public Operador adicionarOperadorHotel(long idOperador, String nombre, String codigoCamaraComercio, String registroSuperintendencia) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlOperador.adicionarOperadorHotel(pm, idOperador, nombre, codigoCamaraComercio, registroSuperintendencia);
            tx.commit();

            if (tuplasInsertadas == 1) {
                return new Operador(idOperador, nombre, "hotel", codigoCamaraComercio, registroSuperintendencia, null);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Operador adicionarOperadorHostal(long idOperador, String nombre, String codigoCamaraComercio, String registroSuperintendencia) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlOperador.adicionarOperadorHostal(pm, idOperador, nombre, codigoCamaraComercio, registroSuperintendencia);
            tx.commit();

            if (tuplasInsertadas == 1) {
                return new Operador(idOperador, nombre, "hostal", codigoCamaraComercio, registroSuperintendencia, null);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Operador adicionarOperadorAloha(long idOperador, String nombre, String cedula) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlOperador.adicionarOperadorAloha(pm, idOperador, nombre, cedula);
            tx.commit();

            if (tuplasInsertadas == 1) {
                return new Operador(idOperador, nombre, "aloha", null, null, cedula);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public long eliminarOperadorPorId(long idOperador) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasEliminadas = sqlOperador.eliminarOperadorPorId(pm, idOperador);
            tx.commit();
            return tuplasEliminadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Reserva adicionarReserva(long id, long userId, long alojamientoId, Date fechaInicial, Date fechaFinal, int costo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, userId, alojamientoId, fechaInicial, fechaFinal, costo);
            tx.commit();
    
            if (tuplasInsertadas == 1) {
                return new Reserva(id, userId, alojamientoId, fechaInicial, fechaFinal, costo);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long eliminarReservaPorId(long id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasEliminadas = sqlReserva.eliminarReservaPorId(pm, id);
            tx.commit();
            return tuplasEliminadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public List<Reserva> darReservas() {
        return sqlReserva.darReservas(pmf.getPersistenceManager());
    }
    
    public List<Propuesta> darPropuestas() {
        return sqlPropuesta.darPropuestas(pmf.getPersistenceManager());
    }
    
    public Propuesta adicionarPropuesta(long id, String titulo, String descripcion, long idAlojamiento) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlPropuesta.adicionarPropuesta(pm, id, titulo, descripcion, idAlojamiento);
            tx.commit();
    
            if (tuplasInsertadas == 1) {
                return new Propuesta(id, titulo, descripcion, idAlojamiento);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long eliminarPropuestaPorId(long id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasEliminadas = sqlPropuesta.eliminarPropuestaPorId(pm, id);
            tx.commit();
            return tuplasEliminadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    public Usuario adicionarUsuario(int idUsuario, String nombre, String apellido, String correoElectronico, int telefono, String tipo) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, idUsuario, nombre, apellido, correoElectronico, telefono, tipo);
            tx.commit();
    
            if (tuplasInsertadas == 1) {
                return new Usuario(idUsuario, nombre, apellido, correoElectronico, telefono, tipo);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return null;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long eliminarUsuarioPorId(int idUsuario) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasEliminadas = sqlUsuario.eliminarUsuarioPorId(pm, idUsuario);
            tx.commit();
            return tuplasEliminadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarNombreUsuario(int idUsuario, String nuevoNombre) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarNombreUsuario(pm, idUsuario, nuevoNombre);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarApellidoUsuario(int idUsuario, String nuevoApellido) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarApellidoUsuario(pm, idUsuario, nuevoApellido);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarCorreoElectronicoUsuario(int idUsuario, String nuevoCorreoElectronico) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarCorreoElectronicoUsuario(pm, idUsuario, nuevoCorreoElectronico);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long actualizarTelefonoUsuario(int idUsuario, int nuevoTelefono) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long tuplasActualizadas = sqlUsuario.actualizarTelefonoUsuario(pm, idUsuario, nuevoTelefono);
            tx.commit();
            return tuplasActualizadas;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
            return -1;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    public long[] limpiarAlohandes() {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            long[] resp = sqlUtil.limpiarAlohandes(pm);
            tx.commit();
            log.info("Borrada la base de datos");
            return resp;
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return new long[]{-1, -1, -1, -1, -1, -1, -1};
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    
} 

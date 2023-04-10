package uniandes.isis2304.alohandes.persistencia.Alohandes;

import java.util.List;
import uniandes.isis2304.alohandes.negocio.Alohandes.Operador;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLOperador {
    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    private final static String SQL = PersistenciaAlohandes.SQL;

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private PersistenciaAlohandes pa;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/

    public SQLOperador(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarOperadorHotel(PersistenceManager pm, long idOperador, String nombre, String codigoCamaraComercio, String registroSuperintendencia) {
        String tipo = "hotel";
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaOperador()
                        + "(id, nombre, tipo, codigo_camara_comercio, registro_superintendencia) values (?, ?, ?, ?, ?)");
        q.setParameters(idOperador, nombre, tipo, codigoCamaraComercio, registroSuperintendencia);
        return (long) q.executeUnique();
    }

    public long adicionarOperadorHostal(PersistenceManager pm, long idOperador, String nombre, String codigoCamaraComercio, String registroSuperintendencia) {
        String tipo = "hostal";
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaOperador()
                        + "(id, nombre, tipo, codigo_camara_comercio, registro_superintendencia) values (?, ?, ?, ?, ?)");
        q.setParameters(idOperador, nombre, tipo, codigoCamaraComercio, registroSuperintendencia);
        return (long) q.executeUnique();
    }

    public long adicionarOperadorAloha(PersistenceManager pm, long idOperador, String nombre, String cedula) {
        String tipo = "aloha";
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaOperador()
                        + "(id, nombre, tipo, cedula) values (?, ?, ?, ?)");
        q.setParameters(idOperador, nombre, tipo, cedula);
        return (long) q.executeUnique();
    }

    public long eliminarOperadorPorId(PersistenceManager pm, long idOperador) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador() + " WHERE id = ?");
        q.setParameters(idOperador);
        return (long) q.executeUnique();
    }

    public Operador darOperadorPorId(PersistenceManager pm, long idOperador) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador() + " WHERE id = ?");
        q.setResultClass(Operador.class);
        q.setParameters(idOperador);
        return (Operador) q.executeUnique();
    }

    public List<Operador> darOperadores(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador());
        q.setResultClass(Operador.class);
        return (List<Operador>) q.executeList();
    }

    public List<Operador> darOperadoresPorTipo(PersistenceManager pm, String tipoOperador) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador() + " WHERE tipo = ?");
        q.setResultClass(Operador.class);
        q.setParameters(tipoOperador);
        return (List<Operador>) q.executeList();
    }

    public long modificarNombreOperadorPorId(PersistenceManager pm, long idOperador, String nuevoNombre) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOperador() + " SET nombre = ? WHERE id = ?");
        q.setParameters(nuevoNombre, idOperador);
        return (long) q.executeUnique();
    }
    
}


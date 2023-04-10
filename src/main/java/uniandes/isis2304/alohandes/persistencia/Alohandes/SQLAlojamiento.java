package uniandes.isis2304.alohandes.persistencia.Alohandes;
import java.util.List;
import uniandes.isis2304.alohandes.negocio.Alohandes.Alojamiento;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLAlojamiento {
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

    public SQLAlojamiento(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarAlojamiento(PersistenceManager pm, long idAlojamiento, long idOperador, String tipo,
            int capacidad, String ubicacion, int tamano, Long precio, char tieneInternet, char tieneSala, char tieneCocina,
            char esCompartido, char tieneBanoPrivado) {
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaAlojamiento()
                        + "(id, id_operador, tipo, capacidad, ubicacion, tamano, precio, tiene_internet, tiene_sala, tiene_cocina, es_compartido, tiene_bano_privado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idAlojamiento, idOperador, tipo, capacidad, ubicacion, tamano, precio, tieneInternet, tieneSala,
                tieneCocina, esCompartido, tieneBanoPrivado);
        return (long) q.executeUnique();
    }

    public long eliminarAlojamientoPorId(PersistenceManager pm, long idAlojamiento) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamiento() + " WHERE id = ?");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
    }

    public Alojamiento darAlojamientoPorId(PersistenceManager pm, long idAlojamiento) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE id = ?");
        q.setResultClass(Alojamiento.class);
        q.setParameters(idAlojamiento);
        return (Alojamiento) q.executeUnique();
    }

    public List<Alojamiento> darAlojamientos(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento());
        q.setResultClass(Alojamiento.class);
        return (List<Alojamiento>) q.executeList();
    }

    public List<Alojamiento> darAlojamientosPorTipo(PersistenceManager pm, String tipoAlojamiento) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE tipo = ?");
        q.setResultClass(Alojamiento.class);
        q.setParameters(tipoAlojamiento);
        return (List<Alojamiento>) q.executeList();
    }
    public List<Alojamiento> darAlojamientoDePropuestaPorCaracteristicas(PersistenceManager pm, char internet, char sala, char cocina, char compartida, char banoPrivado) {
        String query = "SELECT A.* FROM " + pa.darTablaAlojamiento() + " A, " + pa.darTablaPropuesta() + " P WHERE "
                + "A.id = P.id_alojamiento AND A.tiene_internet = ? AND A.tiene_sala = ? AND A.tiene_cocina = ? "
                + "AND A.es_compartido = ? AND A.tiene_bano_privado = ?";
        Query q = pm.newQuery(SQL, query);
        q.setResultClass(Alojamiento.class);
        q.setParameters(internet, sala, cocina, compartida, banoPrivado);
        return (List<Alojamiento>) q.executeList();
    }

    

    
    
}


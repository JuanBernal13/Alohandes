package uniandes.isis2304.alohandes.persistencia.Alohandes;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;
import uniandes.isis2304.alohandes.negocio.Alohandes.Usuario;

public class SQLUsuario {
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

    public SQLUsuario(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarUsuario(PersistenceManager pm, int id, String nombre, String apellido, String correoElectronico, int telefono, String tipo) {
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaUsuario()
                        + "(id, nombre, apellido, correo_electronico, telefono, tipo) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, apellido, correoElectronico, telefono, tipo);
        return (long) q.executeUnique();
    }

    public long eliminarUsuarioPorId(PersistenceManager pm, int id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaUsuario() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Usuario darUsuarioPorId(PersistenceManager pm, int id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaUsuario() + " WHERE id = ?");
        q.setResultClass(Usuario.class);
        q.setParameters(id);
        return (Usuario) q.executeUnique();
    }

    public List<Usuario> darUsuarios(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaUsuario());
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();
    }

    public long actualizarNombreUsuario(PersistenceManager pm, int id, String nuevoNombre) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaUsuario() + " SET nombre = ? WHERE id = ?");
        q.setParameters(nuevoNombre, id);
        return (long) q.executeUnique();
    }

    public long actualizarApellidoUsuario(PersistenceManager pm, int id, String nuevoApellido) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaUsuario() + " SET apellido = ? WHERE id = ?");
        q.setParameters(nuevoApellido, id);
        return (long) q.executeUnique();
    }

    public long actualizarCorreoElectronicoUsuario(PersistenceManager pm, int id, String nuevoCorreoElectronico) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaUsuario() + " SET correo_electronico = ? WHERE id = ?");
        q.setParameters(nuevoCorreoElectronico, id);
        return (long) q.executeUnique();
    }

    public long actualizarTelefonoUsuario(PersistenceManager pm, int id, int nuevoTelefono) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaUsuario() + " SET telefono = ? WHERE id = ?");
        q.setParameters(nuevoTelefono, id);
        return (long) q.executeUnique();
    }

    public long actualizarTipoUsuario(PersistenceManager pm, int id, String nuevoTipo) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaUsuario() + " SET tipo = ? WHERE id = ?");
        q.setParameters(nuevoTipo, id);
        return (long) q.executeUnique();
        }
        }


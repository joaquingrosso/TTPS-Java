package ttps.clasesDAOImplJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ttps.clasesDAO.MensajeDAO;
import ttps.clasesDeObjetosDelSistema.Mensaje;

public class MensajeDAOJPA implements MensajeDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUP");
	EntityManager em = emf.createEntityManager();
	
	@Override
	public Mensaje recuperar(Long id) {
		Mensaje mensaje = null;
		
		try {
            em.getTransaction().begin();
            mensaje = em.find(Mensaje.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
		
		return mensaje;
	}

	@Override
	public List<Mensaje> recuperarTodos() {
		List<Mensaje> mensajes = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Mensaje> query = em.createQuery("SELECT m FROM Mensaje m", Mensaje.class);
            mensajes = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return mensajes;
	}

	@Override
	public void guardar(Mensaje m) {		
		try {
            em.getTransaction().begin();
            System.out.println("pre persists");
            System.out.println(m.getId() + " / " + m.toString());
            em.persist(m);
            System.out.println("precommit");
            em.getTransaction().commit();
            
            System.out.println("post");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
	}

	@Override
	public void actualizar(Mensaje m) {
		try {
            em.getTransaction().begin();
            
            Mensaje mensajeExistente = em.find(Mensaje.class, m.getId());
            
            if (mensajeExistente != null) {
                
            	mensajeExistente.setMensaje(m.getMensaje());
            	mensajeExistente.setUsuario(m.getUsuario());
                
                em.merge(mensajeExistente);
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
		
	}

	@Override
	public void borrar(Mensaje m) {
		try {
            em.getTransaction().begin();
            
            Mensaje mensajeExistente = em.find(Mensaje.class, m.getId());
            
            if (mensajeExistente != null) {
                em.remove(mensajeExistente);
            }
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.utils;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import neo.table.Dataset;
import neo.utils.exceptions.NonexistentEntityException;

/**
 *
 * @author SEED
 */
public class DatasetJpaController implements Serializable {

    public DatasetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dataset dataset) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataset);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dataset dataset) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataset = em.merge(dataset);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dataset.getId();
                if (findDataset(id) == null) {
                    throw new NonexistentEntityException("The dataset with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dataset dataset;
            try {
                dataset = em.getReference(Dataset.class, id);
                dataset.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataset with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataset);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dataset> findDatasetEntities() {
        return findDatasetEntities(true, -1, -1);
    }

    public List<Dataset> findDatasetEntities(int maxResults, int firstResult) {
        return findDatasetEntities(false, maxResults, firstResult);
    }

    private List<Dataset> findDatasetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dataset.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dataset findDataset(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dataset.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatasetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dataset> rt = cq.from(Dataset.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

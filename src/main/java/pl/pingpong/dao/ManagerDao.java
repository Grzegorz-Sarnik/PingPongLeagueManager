package pl.pingpong.dao;

import org.springframework.stereotype.Repository;
import pl.pingpong.entity.Manager;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ManagerDao {
    private EntityManager entityManager;

    public ManagerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addManager(Manager manager) {
        entityManager.persist(manager);
    }

    public Manager findById(int id) {
        return entityManager.find(Manager.class, id);
    }

    public void updateManager(Manager manager) {
        entityManager.merge(manager);
    }

    public void removeManager(Manager manager) {
        entityManager.remove(entityManager.contains(manager) ? manager : entityManager.merge(manager));
    }

    public List<Manager> findAll() {
        return entityManager.createQuery("SELECT m FROM Manager m").getResultList();
    }
}

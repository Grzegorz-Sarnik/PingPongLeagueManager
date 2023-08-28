package pl.pingpong.dao;

import org.springframework.stereotype.Repository;
import pl.pingpong.entity.Trainer;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TrainerDao {

    private EntityManager entityManager;

    public TrainerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addTrainer(Trainer trainer) {
        entityManager.persist(trainer);
    }

    public Trainer findById(int id) {
        return entityManager.find(Trainer.class, id);
    }

    public void updateTrainer(Trainer trainer) {
        entityManager.merge(trainer);
    }

    public void removeTrainer(Trainer trainer) {
        entityManager.remove(entityManager.contains(trainer) ? trainer : entityManager.merge(trainer));
    }

    public List<Trainer> findAll() {
        return entityManager.createQuery("SELECT t FROM Trainer t").getResultList();
    }
}

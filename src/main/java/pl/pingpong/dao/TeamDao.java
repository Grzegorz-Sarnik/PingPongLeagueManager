package pl.pingpong.dao;

import org.springframework.stereotype.Repository;
import pl.pingpong.entity.Team;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TeamDao {

    private EntityManager entityManager;

    public TeamDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addTeam(Team team) {
        entityManager.persist(team);
    }

    public Team findById(int id) {
        return entityManager.find(Team.class, id);
    }

    public void updateTeam(Team team) {
        entityManager.merge(team);
    }

    public void removeTeam(Team team) {
        entityManager.remove(entityManager.contains(team) ? team : entityManager.merge(team));
    }

    public List<Team> findAll() {
        return entityManager.createQuery("SELECT t FROM Team t").getResultList();
    }
}

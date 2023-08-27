package pl.pingpong.dao;

import org.springframework.stereotype.Repository;
import pl.pingpong.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PlayerDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void addPlayer(Player player) {
        entityManager.persist(player);
    }

    public Player findById(int id){
         return entityManager.find(Player.class,id);
    }

    public void updatePlayer(Player player){
        entityManager.merge(player);
    }

    public void removePlayer(Player player){
        entityManager.remove(entityManager.contains(player) ? player : entityManager.merge(player));
    }

    public List<Player> findAll(){
        return entityManager.createQuery("SELECT p FROM Player p").getResultList();
    }


}

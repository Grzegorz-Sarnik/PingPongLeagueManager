package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.Trainer;
import pl.pingpong.repository.TrainerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return (List<Trainer>) trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Integer id) {
        return trainerRepository.findById(id);
    }

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainer(Integer id) {
        trainerRepository.deleteById(id);
    }
}

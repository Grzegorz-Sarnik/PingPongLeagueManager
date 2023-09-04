package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.Manager;
import pl.pingpong.repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<Manager> getAllManagers() {
        return (List<Manager>) managerRepository.findAll();
    }

    public Optional<Manager> getManagerById(Integer id) {
        return managerRepository.findById(id);
    }

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteManager(Integer id) {
        managerRepository.deleteById(id);
    }
}

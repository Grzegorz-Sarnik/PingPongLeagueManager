package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.Club;
import pl.pingpong.repository.ClubRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> getAllClubs() {
        return (List<Club>) clubRepository.findAll();
    }

    public Optional<Club> getClubById(Integer id) {
        return clubRepository.findById(id);
    }

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    public void deleteClub(Integer id) {
        clubRepository.deleteById(id);
    }
}

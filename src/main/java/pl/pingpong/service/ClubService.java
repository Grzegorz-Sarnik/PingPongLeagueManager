package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.Club;
import pl.pingpong.repository.ClubRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

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

    public boolean isClubNameUnique(String clubName) {
        Club existingClub = clubRepository.findByName(clubName);
        return existingClub == null;
    }
}

package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.Team;
import pl.pingpong.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }
}

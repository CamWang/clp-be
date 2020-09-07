package com.starlink.clp.service;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.Team;
import com.starlink.clp.entity.User;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.team.TeamDetail;
import com.starlink.clp.repository.TeamRepository;
import com.starlink.clp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author CamWang
 * @since 2020/9/7 9:27
 */
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamService(
            TeamRepository teamRepository,
            UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Page<TeamDetail> getTeamDetail(Pageable pageable) {
        return teamRepository.findTeamsBy(pageable);
    }

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    @Transactional
    public void addUserToTeam(Integer userId, Integer teamId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new ClpException(ExceptionEnum.TEAM_NOT_EXIST);
        }

    }

}

package com.starlink.clp.controller;

import com.starlink.clp.entity.Team;
import com.starlink.clp.projection.team.TeamDetail;
import com.starlink.clp.service.TeamService;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.view.TeamRegisterView;
import com.starlink.clp.view.TeamSecurityView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author CamWang
 * @since 2020/9/7 9:27
 */
@RestController
@Validated
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team")
    public Page<TeamDetail> getTeamList(
            @ValidPage(message = "页面请求错误") Pageable pageable
    ) {
        return teamService.getTeamDetail(pageable);
    }

    @PostMapping("/team")
    public String createTeam(
            @Validated({TeamRegisterView.class, TeamSecurityView.class}) Team team
    ) {
        // 检查是否已经是队员，是的话先退队
        // 获取用户id来
        return "队伍创建成功";
    }

    @PostMapping("/team/user")
    public String addCurrentUserToTeam(
        @Range(min = 0, max = 2097152, message = "加入队伍ID范围超限") Integer id
    ) {
        /**
         * 获取当前用户ID
         */

        return "入队成功";
    }
}

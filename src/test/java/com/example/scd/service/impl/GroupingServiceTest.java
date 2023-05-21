package com.example.scd.service.impl;

import com.example.scd.dao.CharacterDao;
import com.example.scd.dao.TeamDao;
import com.example.scd.dao.impl.TeamDaoImpl;
import com.example.scd.entity.Team;
import com.example.scd.entity.User;
import com.example.scd.service.GroupingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;

/**
 * https://www.jianshu.com/p/9e7dc7d4fc29?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 * http://www.taodudu.cc/news/show-774699.html?action=onClick
 * https://blog.csdn.net/icarusliu/article/details/78860351
 * https://blog.csdn.net/xiao__jia__jia/article/details/115252780
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupingServiceTest {
    @InjectMocks
    private GroupingServiceImpl groupingService;
    @Mock
    private TeamDaoImpl teamDao;
    @Mock
    private CharacterDao characterDao;
    private static User user;
    private static Team team;
    private static List<Team> teamList;

    @Before
    public void init(){
        user = new User(1,"2011110101","123456","张","https://cos-for-scd-1312783961.cos.ap-shanghai.myqcloud.com/defaultAvator.png",
                0,null,1,null);
        team = new Team(1,1,1,1,null,null);
        teamList = new ArrayList<>();
        teamList.add(new Team(1, 1, 1, 1, null, null));
        teamList.add(new Team(2, 1, 3, 2, null, null));
    }
    @Test
    public void checkWhetherLeader(){
//        given(teamDao.getStudentTeam(user.getId())).willReturn(team);
//        given(characterDao.getCharacterByCharacterID(team.getStudentCharacter())).willReturn("组长");
//        Boolean isLeader = groupingService.checkWhetherLeader(user.getId());
//
//        Assert.assertEquals(isLeader,false);
    }

    @Test
    public void checkWeatherSelected(){

    }

    @Test
    public void readTeamList(){

    }

}

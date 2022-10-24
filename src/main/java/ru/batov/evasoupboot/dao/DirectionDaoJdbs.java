package ru.batov.evasoupboot.dao;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.batov.evasoupboot.domain.Direction;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DirectionDaoJdbs implements DirectionDao {

    private final NamedParameterJdbcOperations jdbs;


    public DirectionDaoJdbs(NamedParameterJdbcOperations jdbs) {
        this.jdbs = jdbs;
    }

    @Override
    public void insertDirection(Direction direction){
        Map<String, Object> params = new HashMap<>();
        params.put("url",direction.getUrl());
        params.put("remdId",direction.getRemdId());
        params.put("regNum",direction.getRegNum());
        params.put("regDate",direction.getRegDate());
        params.put("Stage",direction.getStage());
        params.put("createTime",direction.getCreateTime());
        params.put("refIssDate",direction.getRefIssDate());
        params.put("refOrgName",direction.getRefOrgName());
        params.put("refOrgOgrn",direction.getRefOrgOgrn());
        params.put("fio",direction.getFio());
        params.put("birthDate",direction.getBirthDate());
        params.put("repKind",direction.getRepKind());
        params.put("recDate",direction.getRecDate());
        try {
            jdbs.update("insert into Direction " +
                    "(url, remdId, regNum, regDate, Stage, createTime, refIssDate, refOrgName, refOrgOgrn, fio, birthDate, repKind, recDate)" +
                    " values " +
                    "(:url, :remdId, :regNum, :regDate, :Stage, :createTime, :refIssDate, :refOrgName, :refOrgOgrn, :fio, :birthDate, :repKind, :recDate)", params);

           }catch (UncategorizedSQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getCountDirectionByUrl(String url){
        return jdbs.getJdbcOperations().queryForObject("select count(*) from Direction where url='"+url + "'", Integer.class);
    }

    @Override
    public void updateDirection(Direction direction){
        Map<String, Object> params = new HashMap<>();
        params.put("url",direction.getUrl());
        params.put("remdId",direction.getRemdId());
        params.put("regNum",direction.getRegNum());
        params.put("regDate",direction.getRegDate());
        params.put("Stage",direction.getStage());
        params.put("createTime",direction.getCreateTime());
        params.put("refIssDate",direction.getRefIssDate());
        params.put("refOrgName",direction.getRefOrgName());
        params.put("refOrgOgrn",direction.getRefOrgOgrn());
        params.put("fio",direction.getFio());
        params.put("birthDate",direction.getBirthDate());
        params.put("repKind",direction.getRepKind());
        params.put("recDate",direction.getRecDate());
        try {
            jdbs.update("update Direction set " +
                    "remdId=:remdId, regNum=:regNum, regDate=:regDate, Stage=:Stage, " +
                    " createTime=:createTime, refIssDate=:refIssDate, refOrgName=:refOrgName, refOrgOgrn=:refOrgOgrn," +
                    " fio=:fio, birthDate=:birthDate, repKind=:repKind, recDate=:recDate" +
                    " where url=:url", params);
        }catch (UncategorizedSQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Direction getDirectionByUrl(String url){
        Map<String, Object> params = new HashMap<>();
        params.put("url",url);
        return jdbs.queryForObject("select * from Direction where url=:url", params, directionMapper);
    }

    RowMapper<Direction> directionMapper = (rs, rowNum) -> Direction.builder()
            .url(rs.getString("url"))
            .remdId(rs.getString("remdId"))
            .regNum(rs.getString("regNum"))
            .regDate(rs.getString("regDate"))
            .Stage(rs.getString("Stage"))
            .createTime(rs.getString("createTime"))
            .refIssDate(rs.getString("refIssDate"))
            .refOrgName(rs.getString("refOrgName"))
            .refOrgOgrn(rs.getString("refOrgOgrn"))
            .fio(rs.getString("fio"))
            .birthDate(rs.getString("birthDate"))
            .repKind(rs.getString("repKind"))
            .recDate(rs.getString("recDate"))
            .build();
}


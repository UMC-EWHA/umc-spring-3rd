package umc.crud.info.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import umc.crud.info.model.City;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CityRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CityRowMapper cityRowMapper;

    public CityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, CityRowMapper cityRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.cityRowMapper = new CityRowMapper();
    }

    public List<City> findList(){
        log.debug("findList query = {}", CitySql.SELECT);

        return namedParameterJdbcTemplate.query(CitySql.SELECT
                , EmptySqlParameterSource.INSTANCE // 쿼리에 파라미터 넘길 필요 X
                , this.cityRowMapper);
    }
}
package umc.crud.info.repository;
import umc.crud.info.model.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("ID"));
        city.setName(rs.getString("Name"));
        city.setCountryCode(rs.getString("countrycode"));
        city.setDistrict(rs.getString("district"));
        city.setPopulation(rs.getInt("population"));
        return city;
    }
}
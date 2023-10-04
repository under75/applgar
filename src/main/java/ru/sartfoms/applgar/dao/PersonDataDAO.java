package ru.sartfoms.applgar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.sartfoms.applgar.model.PersonDataForRequestValidation;

@Component
public class PersonDataDAO {
	protected final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private BeanPropertyRowMapper<PersonDataForRequestValidation> dataForValidationMapper = new BeanPropertyRowMapper<PersonDataForRequestValidation>(
			PersonDataForRequestValidation.class) {
		@Override
		public PersonDataForRequestValidation mapRow(ResultSet rs, int rowNum) throws SQLException {
			PersonDataForRequestValidation data = new PersonDataForRequestValidation();
			data.setRid(rs.getLong(1));
			data.setBirthDay(rs.getDate(2).toLocalDate());
			data.setLastName(rs.getString(3));
			data.setFirstName(rs.getString(4));
			data.setPatronymic(rs.getString(5));

			return data;
		}
	};
	
	public PersonDataForRequestValidation getPersonDataForRequestValidation(Long rid) {
		String sql = "SELECT pd.rid, pd.dr, pd.fam, pd.im, pd.ot FROM APPLGAR.person_data pd WHERE pd.rid = :rid";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("rid", rid);

		return jdbcTemplate.query(sql, namedParams, dataForValidationMapper).stream().findAny().orElse(null);
	}
	
	public int save(Long rid, String lastName, String firstName, String patronymic, LocalDate birthDay) {
		String sql = "insert into APPLGAR.person_data (rid, dr, fam, im, ot) values (:rid, :dr, :fam, :im, :ot)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("rid", rid);
		params.addValue("dr", birthDay);
		params.addValue("fam", lastName);
		params.addValue("im", firstName);
		params.addValue("ot", patronymic);

		return jdbcTemplate.update(sql, params);

	}
}

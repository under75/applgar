package ru.sartfoms.applgar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.sartfoms.applgar.model.Address;
import ru.sartfoms.applgar.model.House;
import ru.sartfoms.applgar.model.Okato;

@Component
public class AddressDAO {

	protected final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public AddressDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private BeanPropertyRowMapper<Address> addressMapper = new BeanPropertyRowMapper<Address>(Address.class) {
		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();
			address.setObjectId(rs.getInt(1));
			address.setObjectguid(rs.getString(2));
			address.setName(rs.getString(3));
			address.setTypeName(rs.getString(4));
			address.setId(rs.getInt(5));
			address.setLevel(rs.getInt(6));

			return address;
		}
	};

	private BeanPropertyRowMapper<House> houseMapper = new BeanPropertyRowMapper<House>(House.class) {
		@Override
		public House mapRow(ResultSet rs, int rowNum) throws SQLException {
			House address = new House();
			address.setObjectId(rs.getInt(1));
			address.setObjectguid(rs.getString(2));
			address.setHouseNum(rs.getString(3));
			address.setAddNum1(rs.getString(4));
			address.setAddNum2(rs.getString(5));
			address.setId(rs.getInt(6));

			return address;
		}
	};

	public Collection<Address> level1() {
		String sql = "SELECT a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ FROM fiasowner.as_addr_obj a"
				+ " where a.LEVEL_ = 1 order by a.NAME_";

		return jdbcTemplate.query(sql, addressMapper);
	}

	public Collection<Address> level1(String filter) {
		String sql = "SELECT a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ FROM fiasowner.as_addr_obj a"
				+ " where a.LEVEL_ = 1 and lower(a.NAME_) like lower(:filter) order by a.NAME_";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("filter", "%" + filter + "%");
		return jdbcTemplate.query(sql, namedParams, addressMapper);
	}

	public Collection<Address> levelByParentId(Integer parentid) {
		String sql = "SELECT a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ FROM fiasowner.as_addr_obj a "
				+ "where a.OBJECTID in (select h.OBJECTID from fiasowner.as_adm_hierarchy h where h.PARENTOBJID = :parentid) order by a.NAME_";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("parentid", parentid);

		return jdbcTemplate.query(sql, namedParams, addressMapper);
	}

	public Collection<Address> levelByParentIdAndFilter(Integer parentid, String filter) {
		String sql = "SELECT a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ FROM fiasowner.as_addr_obj a "
				+ "where lower(a.NAME_) like lower(:filter) and a.OBJECTID in (select h.OBJECTID from fiasowner.as_adm_hierarchy h where h.PARENTOBJID = :parentid) order by a.NAME_";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("filter", "%" + filter + "%");
		namedParams.addValue("parentid", parentid);

		return jdbcTemplate.query(sql, namedParams, addressMapper);
	}

	public Collection<House> level4(Integer parentid) {
		String sql = "SELECT hous.OBJECTID, hous.OBJECTGUID, hous.HOUSENUM, hous.ADDNUM1, hous.ADDNUM2, hous.ID FROM fiasowner.as_houses hous"
				+ " where hous.ISACTUAL=1 and hous.ISACTIVE=1 and hous.OBJECTID in (select h.OBJECTID from fiasowner.as_adm_hierarchy h where h.PARENTOBJID = :parentid) order by hous.HOUSENUM";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("parentid", parentid);

		return jdbcTemplate.query(sql, namedParams, houseMapper);
	}

	public Collection<House> level4(Integer parentid, String filter) {
		String sql = "SELECT hous.OBJECTID, hous.OBJECTGUID, hous.HOUSENUM, hous.ADDNUM1, hous.ADDNUM2, hous.ID FROM fiasowner.as_houses hous"
				+ " where hous.ISACTUAL=1 and hous.ISACTIVE=1 and lower(hous.HOUSENUM) like lower(:filter) and hous.OBJECTID in (select h.OBJECTID from fiasowner.as_adm_hierarchy h where h.PARENTOBJID = :parentid) order by hous.HOUSENUM";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("filter", "%" + filter + "%");
		namedParams.addValue("parentid", parentid);

		return jdbcTemplate.query(sql, namedParams, houseMapper);
	}
//	@Transactional
//	public int save(Result result) {
//		String sql;
//		if (!isRecordExist(result.getId_appl())) {
//			sql = "insert into OMCOWNER.address_gar (id_appl, rguidreg, aoguidreg, hsguidreg, rguidpr, aoguidpr, hsguidpr) values (:id_appl, :rguidreg, :aoguidreg, :hsguidreg, :rguidpr, :aoguidpr, :hsguidpr)";
//		} else {
//			sql = "update OMCOWNER.address_gar set rguidreg = :rguidreg, aoguidreg = :aoguidreg, hsguidreg = :hsguidreg, rguidpr = :rguidpr, aoguidpr = :aoguidpr, hsguidpr = :hsguidpr where  id_appl = :id_appl";
//		}
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("id_appl", result.getId_appl());
//		params.addValue("rguidreg", result.getRguidreg());
//		params.addValue("aoguidreg", result.getAoguidreg());
//		params.addValue("hsguidreg", result.getHsguidreg());
//		params.addValue("rguidpr", result.getRguidpr());
//		params.addValue("aoguidpr", result.getAoguidpr());
//		params.addValue("hsguidpr", result.getHsguidpr());
//
//		return jdbcTemplate.update(sql, params);
//	}

	public boolean isRecordExist(Long id_appl) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id_appl", id_appl);
		return jdbcTemplate.queryForObject("select count(*) from OMCOWNER.address_gar where id_appl = :id_appl", params,
				Integer.class) > 0;
	}

	public Address findByChildId(Integer childId, Integer parentId) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("childId", childId);
		namedParams.addValue("parentId", parentId);
		return jdbcTemplate.query(
				"select a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ from fiasowner.as_addr_obj a inner join fiasowner.as_adm_hierarchy h on a.objectid = h.parentobjid where a.id = :parentId and a.isactual = 1 and h.objectid in (select ad.objectid from fiasowner.as_addr_obj ad where ad.id = :childId)",
				namedParams, addressMapper).stream().findAny().orElse(null);
	}

	public Integer findIdByChildId(Integer childId) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("childId", childId);
		return jdbcTemplate.queryForList(
				"select a.ID from fiasowner.as_addr_obj a inner join fiasowner.as_adm_hierarchy h on a.objectid = h.parentobjid where a.isactual = 1 and h.objectid in (select ad.objectid from fiasowner.as_addr_obj ad where ad.id = :childId)",
				namedParams, Integer.class).stream().findAny().orElse(findAddressById(childId).getId());
	}

	public Integer findIdLev1ByChildId(Integer childId) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("childId", childId);
		return jdbcTemplate.queryForList(
				"select a.ID from fiasowner.as_addr_obj a inner join fiasowner.as_adm_hierarchy h on a.objectid = h.parentobjid where a.isactual = 1 and a.level_ = 1 and h.objectid in (select ad.objectid from fiasowner.as_addr_obj ad where ad.id = :childId)",
				namedParams, Integer.class).stream().findAny().orElse(findAddressById(childId).getId());
	}

	public Integer findIdLev2Reg(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		return jdbcTemplate.queryForList(
				"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.rguidreg where a.isactual = 1 and g.id_appl = :id_appl",
				namedParams, Integer.class).stream().findAny().orElse(null);
	}

	public Integer findIdLev3Reg(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		return jdbcTemplate.queryForList(
				"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.aoguidreg where a.isactual = 1 and g.id_appl = :id_appl",
				namedParams, Integer.class).stream().findAny().orElse(null);
	}

	public Integer findIdLev4Reg(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		try {
			return jdbcTemplate.queryForObject(
					"select a.id from fiasowner.as_houses a inner join omcowner.address_gar g on a.objectguid = g.hsguidreg where a.isactual = 1 and g.id_appl = :id_appl",
					namedParams, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return jdbcTemplate.queryForList(
					"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.hsguidreg where a.isactual = 1 and g.id_appl = :id_appl",
					namedParams, Integer.class).stream().findAny().orElse(null);
		}
	}

	public Integer findIdLev2Pr(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		return jdbcTemplate.queryForList(
				"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.rguidpr where a.isactual = 1 and g.id_appl = :id_appl",
				namedParams, Integer.class).stream().findAny().orElse(null);
	}

	public Integer findIdLev3Pr(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		return jdbcTemplate.queryForList(
				"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.aoguidpr where a.isactual = 1 and g.id_appl = :id_appl",
				namedParams, Integer.class).stream().findAny().orElse(null);
	}

	public Integer findIdLev4Pr(Long id_appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id_appl", id_appl);
		try {
			return jdbcTemplate.queryForObject(
					"select a.id from fiasowner.as_houses a inner join omcowner.address_gar g on a.objectguid = g.hsguidpr where a.isactual = 1 and g.id_appl = :id_appl",
					namedParams, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return jdbcTemplate.queryForList(
					"select a.id from fiasowner.as_addr_obj a inner join omcowner.address_gar g on a.objectguid = g.hsguidpr where a.isactual = 1 and g.id_appl = :id_appl",
					namedParams, Integer.class).stream().findAny().orElse(null);
		}
	}

	public Address findAddressById(Integer id) {
		String sql = "SELECT a.OBJECTID, a.OBJECTGUID, a.NAME_, a.TYPENAME, a.ID, a.LEVEL_ FROM fiasowner.as_addr_obj a "
				+ "where a.ID = :id";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("id", id);

		return jdbcTemplate.query(sql, namedParams, addressMapper).stream().findAny().orElse(null);
	}

	public Okato findOkato(Long addrRegId) {
		String sql = "SELECT ter.naim, obl.naim, addr.raion, addr.settl, addr.street, addr.house, addr.bldn, addr.flat, addr.city, obl.cod"
				+ " FROM omcowner.address addr LEFT JOIN OMCOWNER.okatoter ter ON addr.ter = ter.cod"
				+ " LEFT JOIN OMCOWNER.okatoobl obl ON addr.okato = obl.cod WHERE addr.id_addr = :addrRegId";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("addrRegId", addrRegId);

		return jdbcTemplate.query(sql, namedParams, new BeanPropertyRowMapper<Okato>(Okato.class) {
			@Override
			public Okato mapRow(ResultSet rs, int rowNum) throws SQLException {
				Okato address = new Okato();
				address.setTer(rs.getString(1));
				address.setOblLev2(rs.getString(2));
				address.setRaion(rs.getString(3));
				address.setSettl(rs.getString(4));
				address.setStreet(rs.getString(5));
				address.setHouse(rs.getString(6));
				address.setBldn(rs.getString(7));
				address.setFlat(rs.getString(8));
				address.setCity(rs.getString(9));
				address.setOblLev2Cod(rs.getString(10));

				return address;
			}
		}).stream().findAny().orElse(null);
	}

	public String findOkatoOblLev1(String lev1Cod) {
		String sql = "SELECT o.naim FROM OMCOWNER.okatoobl o WHERE o.cod = :lev1";

		MapSqlParameterSource namedParams = new MapSqlParameterSource();
		namedParams.addValue("lev1", lev1Cod);

		return jdbcTemplate.queryForList(sql, namedParams, String.class).stream().findAny().orElse(null);
	}

	public void updateForOkatoByReg(Long id_appl, String rguidreg, String rguidpr) {
		String selectForReg = "with QQ as (select aao.objectguid,aaop.VALUE_ from FIASOWNER.AS_ADDR_OBJ_PARAMS aaop "
				+ "join FIASOWNER.AS_ADDR_OBJ aao ON aaop.OBJECTID = aao.OBJECTID "
				+ "where aao.ISACTIVE = 1 AND aao.ISACTUAL = 1 and aaop.TYPEID_ = 6 and TO_DATE(aaop.ENDDATE, 'yyyy-MM-dd') > CURRENT_DATE) "
				+ "select distinct QQ.value_ from QQ "
				+ "join OMCOWNER.ADDRESS_GAR ag on QQ.OBJECTGUID = ag.RGUIDREG where ag.RGUIDREG=:rguidreg";
		String selectForPr = "with QQ as (select aao.objectguid,aaop.VALUE_ from FIASOWNER.AS_ADDR_OBJ_PARAMS aaop "
				+ "join FIASOWNER.AS_ADDR_OBJ aao ON aaop.OBJECTID = aao.OBJECTID "
				+ "where aao.ISACTIVE = 1 AND aao.ISACTUAL = 1 and aaop.TYPEID_ = 6 and TO_DATE(aaop.ENDDATE, 'yyyy-MM-dd') > CURRENT_DATE) "
				+ "select distinct QQ.value_ from QQ "
				+ "join OMCOWNER.ADDRESS_GAR ag on QQ.OBJECTGUID = ag.RGUIDPR where ag.RGUIDPR=:rguidpr";

		String updateForReg = "update OMCOWNER.address_gar set okatoreg = (" + selectForReg
				+ ") where  id_appl = :id_appl";
		String updateForPr = "update OMCOWNER.address_gar set okatopr = (" + selectForPr
				+ ") where  id_appl = :id_appl";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id_appl", id_appl);
		params.addValue("rguidreg", rguidreg);
		params.addValue("rguidpr", rguidpr);

		jdbcTemplate.update(updateForReg, params);
		jdbcTemplate.update(updateForPr, params);
	}

	public void updateForOkatoByStreet(Long id_appl, String aoguidreg, String aoguidpr) {
		String selectForReg = "with QQ as (select aao.objectguid,aaop.VALUE_ from FIASOWNER.AS_ADDR_OBJ_PARAMS aaop "
				+ "join FIASOWNER.AS_ADDR_OBJ aao ON aaop.OBJECTID = aao.OBJECTID "
				+ "where aao.ISACTIVE = 1 AND aao.ISACTUAL = 1 and aaop.TYPEID_ = 6 and TO_DATE(aaop.ENDDATE, 'yyyy-MM-dd') > CURRENT_DATE) "
				+ "select distinct QQ.value_ from QQ "
				+ "join OMCOWNER.ADDRESS_GAR ag on QQ.OBJECTGUID = ag.AOGUIDREG where ag.AOGUIDREG=:aoguidreg";
		String selectForPr = "with QQ as (select aao.objectguid,aaop.VALUE_ from FIASOWNER.AS_ADDR_OBJ_PARAMS aaop "
				+ "join FIASOWNER.AS_ADDR_OBJ aao ON aaop.OBJECTID = aao.OBJECTID "
				+ "where aao.ISACTIVE = 1 AND aao.ISACTUAL = 1 and aaop.TYPEID_ = 6 and TO_DATE(aaop.ENDDATE, 'yyyy-MM-dd') > CURRENT_DATE) "
				+ "select distinct QQ.value_ from QQ "
				+ "join OMCOWNER.ADDRESS_GAR ag on QQ.OBJECTGUID = ag.AOGUIDPR where ag.AOGUIDPR=:aoguidpr";

		String updateForReg = "update OMCOWNER.address_gar set okatoreg = (" + selectForReg
				+ ") where  id_appl = :id_appl";
		String updateForPr = "update OMCOWNER.address_gar set okatopr = (" + selectForPr
				+ ") where  id_appl = :id_appl";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id_appl", id_appl);
		params.addValue("aoguidreg", aoguidreg);
		params.addValue("aoguidpr", aoguidpr);

		jdbcTemplate.update(updateForReg, params);
		jdbcTemplate.update(updateForPr, params);
	}

}

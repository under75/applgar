package ru.sartfoms.applgar.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sartfoms.applgar.dao.AddressDAO;
import ru.sartfoms.applgar.entity.AddrResult;
import ru.sartfoms.applgar.entity.Appl;
import ru.sartfoms.applgar.model.Address;
import ru.sartfoms.applgar.model.FilterWord;
import ru.sartfoms.applgar.model.Gar;
import ru.sartfoms.applgar.model.House;
import ru.sartfoms.applgar.model.Okato;
import ru.sartfoms.applgar.model.SelectedAddress;
import ru.sartfoms.applgar.repository.AddrResultRepository;

@Service
public class AddressService<T> {
	private final AddressDAO addressDAO;
	private final AddrResultRepository addrResultRepository;
	public static final String CITY = "г";
	public static final Integer STREET_LEVEL = 8;

	@Autowired
	public AddressService(AddressDAO addressDAO, AddrResultRepository addrResultRepository) {
		super();
		this.addressDAO = addressDAO;
		this.addrResultRepository = addrResultRepository;
	}

	public void setLevel1Reg(Gar gar, FilterWord filter) {
		if (filter.getLev1Reg() != null && !filter.getLev1Reg().trim().isEmpty()) {
			gar.setLevel1Reg(addressDAO.level1(filter.getLev1Reg()));
		} else {
			gar.setLevel1Reg(addressDAO.level1());
		}
	}

	public void setLevel1Pr(Gar gar, FilterWord filter) {
		if (filter.getLev1Pr() != null && !filter.getLev1Pr().trim().isEmpty()) {
			gar.setLevel1Pr(addressDAO.level1(filter.getLev1Pr()));
		} else {
			gar.setLevel1Pr(addressDAO.level1());
		}
	}

	public void setLevel2Reg(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev2Reg() != null && !filter.getLev2Reg().trim().isEmpty()) {
			gar.setLevel2Reg(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev2Reg()));
		} else {
			gar.setLevel2Reg(addressDAO.levelByParentId(objectId));
		}
	}

	public void setLevel2Pr(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev2Pr() != null && !filter.getLev2Pr().trim().isEmpty()) {
			gar.setLevel2Pr(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev2Pr()));
		} else {
			gar.setLevel2Pr(addressDAO.levelByParentId(objectId));
		}
	}

	public void setLevel3Reg(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev3Reg() != null && !filter.getLev3Reg().trim().isEmpty()) {
			gar.setLevel3Reg(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev3Reg()));
		} else {
			gar.setLevel3Reg(addressDAO.levelByParentId(objectId));
		}
	}

	public void setLevel3Pr(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev3Pr() != null && !filter.getLev3Pr().trim().isEmpty()) {
			gar.setLevel3Pr(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev3Pr()));
		} else {
			gar.setLevel3Pr(addressDAO.levelByParentId(objectId));
		}
	}

	public void setLevel4Reg(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev4Reg() != null && !filter.getLev4Reg().trim().isEmpty()) {
			gar.setLevel4Reg(addressDAO.level4(objectId, filter.getLev4Reg()));
		} else {
			gar.setLevel4Reg(addressDAO.level4(objectId));
		}
	}

	public void setLevel4Pr(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev4Pr() != null && !filter.getLev4Pr().trim().isEmpty()) {
			gar.setLevel4Pr(addressDAO.level4(objectId, filter.getLev4Pr()));
		} else {
			gar.setLevel4Pr(addressDAO.level4(objectId));
		}
	}

	public String findObjectGuid(Integer id, Collection<?> addresses) {
		if (id == null || addresses == null || addresses.size() == 0)
			return "";

		String result;
		if (addresses.iterator().next() instanceof Address) {
			Address address = (Address) addresses.stream()
					.filter(t -> ((Address) t).getId().intValue() == id.intValue()).findAny().orElse(null);
			result = address != null ? address.getObjectguid() : "";
		} else {
			House address = (House) addresses.stream().filter(t -> ((House) t).getId().intValue() == id.intValue())
					.findAny().orElse(null);
			result = address != null ? address.getObjectguid() : "";
		}

		return result;
	}

	@Transactional
	public Long save(AddrResult result) {
		Long res = addrResultRepository.save(result).getId_appl();
		try {
			addressDAO.updateForOkatoByStreet(result.getId_appl(), result.getAoguidreg(), result.getAoguidpr());
		} catch (Exception e) {
			addressDAO.updateForOkatoByReg(result.getId_appl(), result.getRguidreg(), result.getRguidpr());
		}

		return res;
	}

	public void fillPrIfSameWithReg(Appl appl, SelectedAddress address) {
		if (appl.getId_adrreg() != null && appl.getId_adrpr() != null
				&& appl.getId_adrreg().intValue() == appl.getId_adrpr().intValue()) {
			address.setIdlev1Pr(address.getIdlev1Reg());
			address.setIdlev2Pr(address.getIdlev2Reg());
			address.setIdlev3Pr(address.getIdlev3Reg());
			address.setIdlev31Pr(address.getIdlev31Reg());
			address.setIdlev4Pr(address.getIdlev4Reg());
		}
	}

	public void initFromDB(SelectedAddress selAddress, Long id_appl) {
		/** REG **/
		selAddress.setIdlev2Reg(addressDAO.findIdLev2Reg(id_appl));
		/* test lev31 or level3 */
		Integer idlev3or31 = addressDAO.findIdLev3Reg(id_appl);
		if (idlev3or31 != null) {
			Address lev3or2 = addressDAO.findByChildId(idlev3or31, selAddress.getIdlev2Reg());
			if (lev3or2 != null && lev3or2.getId().intValue() == selAddress.getIdlev2Reg().intValue()) {
				selAddress.setIdlev3Reg(idlev3or31);
			} else {
				selAddress.setIdlev31Reg(idlev3or31);
				selAddress.setIdlev3Reg(addressDAO.findIdByChildId(idlev3or31));
			}
		}
		selAddress.setIdlev4Reg(addressDAO.findIdLev4Reg(id_appl));
		if (selAddress.getIdlev2Reg() != null)
			selAddress.setIdlev1Reg(addressDAO.findIdLev1ByChildId(selAddress.getIdlev2Reg()));

		/** PR **/
		selAddress.setIdlev2Pr(addressDAO.findIdLev2Pr(id_appl));
		/* test lev31 or level3 */
		idlev3or31 = addressDAO.findIdLev3Pr(id_appl);
		if (idlev3or31 != null) {
			Address lev3or2 = addressDAO.findByChildId(idlev3or31, selAddress.getIdlev2Pr());
			if (lev3or2 != null && lev3or2.getId().intValue() == selAddress.getIdlev2Pr().intValue()) {
				selAddress.setIdlev3Pr(idlev3or31);
			} else {
				selAddress.setIdlev31Pr(idlev3or31);
				selAddress.setIdlev3Pr(addressDAO.findIdByChildId(idlev3or31));
			}
		}
		selAddress.setIdlev4Pr(addressDAO.findIdLev4Pr(id_appl));
		if (selAddress.getIdlev2Pr() != null)
			selAddress.setIdlev1Pr(addressDAO.findIdLev1ByChildId(selAddress.getIdlev2Pr()));
	}

	public void setLevel4Reg(Gar gar, FilterWord filter, Address lev3Reg) {
		House house = new House();
		house.setId(lev3Reg.getId());
		house.setObjectId(lev3Reg.getObjectId());
		house.setObjectguid(lev3Reg.getObjectguid());
		house.setHouseNum(lev3Reg.getName());
		house.setAddNum1(lev3Reg.getTypeName());
		Collection<House> lev4Reg = new ArrayList<>();
		lev4Reg.add(house);
		gar.setLevel4Reg(lev4Reg);
	}

	public void setLevel4Pr(Gar gar, FilterWord filter, Address lev3Pr) {
		House house = new House();
		house.setId(lev3Pr.getId());
		house.setObjectId(lev3Pr.getObjectId());
		house.setObjectguid(lev3Pr.getObjectguid());
		house.setHouseNum(lev3Pr.getName());
		house.setAddNum1(lev3Pr.getTypeName());
		Collection<House> lev4Pr = new ArrayList<>();
		lev4Pr.add(house);
		gar.setLevel4Pr(lev4Pr);
	}

	public boolean isRecordExist(Long id_appl) {
		return addressDAO.isRecordExist(id_appl);
	}

	public Address findAddressById(Integer id) {
		return addressDAO.findAddressById(id);
	}

	public void setLevel31Reg(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev31Reg() != null && !filter.getLev31Reg().trim().isEmpty()) {
			gar.setLevel31Reg(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev31Reg()));
		} else {
			gar.setLevel31Reg(addressDAO.levelByParentId(objectId));
		}

	}

	public void setLevel31Pr(Gar gar, FilterWord filter, Integer objectId) {
		if (filter.getLev31Pr() != null && !filter.getLev31Pr().trim().isEmpty()) {
			gar.setLevel31Pr(addressDAO.levelByParentIdAndFilter(objectId, filter.getLev31Pr()));
		} else {
			gar.setLevel31Pr(addressDAO.levelByParentId(objectId));
		}
	}

	public void setGar(Appl appl, Gar gar, FilterWord filter, SelectedAddress selAddress) {
		Address lev1Reg = null;
		Address lev2Reg = null;
		Address lev3Reg = null;
		Address lev31Reg = null;
		Address lev1Pr = null;
		Address lev2Pr = null;
		Address lev3Pr = null;
		Address lev31Pr = null;

		if (appl.getId_adrreg() != null) {
			/** Level1 **/
			setLevel1Reg(gar, filter);
			/** Level2 **/
			if (selAddress.getIdlev1Reg() != null && gar.getLevel1Reg() != null) {
				lev1Reg = gar.getLevel1Reg().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev1Reg().intValue()).findAny()
						.orElse(null);
				if (lev1Reg != null && lev1Reg.getTypeName().trim().equals(CITY)) {
					Collection<Address> lev2RegCollection = new ArrayList<>();
					lev2RegCollection.add(lev1Reg);
					gar.setLevel2Reg(lev2RegCollection);
				} else if (lev1Reg != null) {
					setLevel2Reg(gar, filter, lev1Reg.getObjectId());
				}
			}
			/** Level3 **/
			if (selAddress.getIdlev2Reg() != null && gar.getLevel2Reg() != null) {
				lev2Reg = gar.getLevel2Reg().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev2Reg().intValue()).findAny()
						.orElse(null);
				if (lev2Reg != null) {
					setLevel3Reg(gar, filter, lev2Reg.getObjectId());
				}
			}
			/** Level4 **/
			if (selAddress.getIdlev3Reg() != null && gar.getLevel3Reg() != null) {
				lev3Reg = gar.getLevel3Reg().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev3Reg().intValue()).findAny()
						.orElse(null);
				if (lev3Reg != null) {
					if (lev3Reg.getLevel() < STREET_LEVEL && lev2Reg != null) {
						setLevel31Reg(gar, filter, lev3Reg.getObjectId());
						if (selAddress.getIdlev31Reg() != null) {
							lev31Reg = gar.getLevel31Reg().stream()
									.filter(t -> t.getId().intValue() == selAddress.getIdlev31Reg().intValue())
									.findAny().orElse(null);
							if (lev31Reg != null)
								setLevel4Reg(gar, filter, lev31Reg.getObjectId());
						} else {
							setLevel4Reg(gar, filter, lev3Reg.getObjectId());
						}
					} else {
						setLevel4Reg(gar, filter, lev3Reg.getObjectId());
					}
				}
			} else if (lev2Reg != null) {
				setLevel4Reg(gar, filter, lev2Reg.getObjectId());
			}
		}
		if (appl.getId_adrpr() != null) {
			/** Level1 **/
			setLevel1Pr(gar, filter);
			/** Level2 **/
			if (selAddress.getIdlev1Pr() != null && gar.getLevel1Pr() != null) {
				lev1Pr = gar.getLevel1Pr().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev1Pr().intValue()).findAny()
						.orElse(null);
				if (lev1Pr != null && lev1Pr.getTypeName().trim().equals(CITY)) {
					Collection<Address> lev2PrCollection = new ArrayList<>();
					lev2PrCollection.add(lev1Pr);
					gar.setLevel2Pr(lev2PrCollection);
				} else if (lev1Pr != null) {
					setLevel2Pr(gar, filter, lev1Pr.getObjectId());
				}
			}
			/** Level3 **/
			if (selAddress.getIdlev2Pr() != null && gar.getLevel2Pr() != null) {
				lev2Pr = gar.getLevel2Pr().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev2Pr().intValue()).findAny()
						.orElse(null);
				if (lev2Pr != null) {
					setLevel3Pr(gar, filter, lev2Pr.getObjectId());
				}
			}
			/** Level4 **/
			if (selAddress.getIdlev3Pr() != null && gar.getLevel3Pr() != null) {
				lev3Pr = gar.getLevel3Pr().stream()
						.filter(t -> t.getId().intValue() == selAddress.getIdlev3Pr().intValue()).findAny()
						.orElse(null);
				if (lev3Pr != null) {
					if (lev3Pr.getLevel() < STREET_LEVEL && lev2Pr != null) {
						setLevel31Pr(gar, filter, lev3Pr.getObjectId());
						if (selAddress.getIdlev31Pr() != null) {
							lev31Pr = gar.getLevel31Pr().stream()
									.filter(t -> t.getId().intValue() == selAddress.getIdlev31Pr().intValue()).findAny()
									.orElse(null);
							if (lev31Pr != null)
								setLevel4Pr(gar, filter, lev31Pr.getObjectId());
						} else {
							setLevel4Pr(gar, filter, lev3Pr.getObjectId());
						}
					} else {
						setLevel4Pr(gar, filter, lev3Pr.getObjectId());
					}
				}
			} else if (lev2Pr != null) {
				setLevel4Pr(gar, filter, lev2Pr.getObjectId());
			}
		}
	}

	public Long saveResult(Appl appl, SelectedAddress selAddress, Gar gar) {
		AddrResult result = new AddrResult();
		result.setId_appl(appl.getId_appl());
		if (appl.getId_adrreg() != null) {
			result.setRguidreg(findObjectGuid(selAddress.getIdlev2Reg(), gar.getLevel2Reg()));
			if (selAddress.getIdlev31Reg() != null) {
				result.setAoguidreg(findObjectGuid(selAddress.getIdlev31Reg(), gar.getLevel31Reg()));
			} else {
				result.setAoguidreg(findObjectGuid(selAddress.getIdlev3Reg(), gar.getLevel3Reg()));
			}
			result.setHsguidreg(findObjectGuid(selAddress.getIdlev4Reg(), gar.getLevel4Reg()));
		}
		result.setRguidpr(findObjectGuid(selAddress.getIdlev2Pr(), gar.getLevel2Pr()));
		if (selAddress.getIdlev31Pr() != null) {
			result.setAoguidpr(findObjectGuid(selAddress.getIdlev31Pr(), gar.getLevel31Pr()));
		} else {
			result.setAoguidpr(findObjectGuid(selAddress.getIdlev3Pr(), gar.getLevel3Pr()));
		}
		result.setHsguidpr(findObjectGuid(selAddress.getIdlev4Pr(), gar.getLevel4Pr()));

		return save(result);
	}

	public String findOkato(Long addrRegId) {
		Okato ocato = addressDAO.findOkato(addrRegId);
		String lev1Cod;
		String oblLev1 = null;
		if (ocato.getOblLev2Cod() != null) {
			lev1Cod = ocato.getOblLev2Cod().substring(0, 5) + "0000001";
			oblLev1 = addressDAO.findOkatoOblLev1(lev1Cod);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(ocato.getTer() != null ? ocato.getTer() : "Саратовская обл").append(", ");
		sb.append(oblLev1 != null ? oblLev1 + ", " : "");
		sb.append(ocato.getOblLev2() != null && !ocato.getOblLev2().equals(oblLev1) ? ocato.getOblLev2() + ", " : "");
		sb.append(ocato.getCity() != null ? ocato.getCity() + ", " : "");
		sb.append(ocato.getRaion() != null ? ocato.getRaion() + ", " : "");
		sb.append(ocato.getSettl() != null ? ocato.getSettl() + ", " : "");
		sb.append(ocato.getStreet() != null ? ocato.getStreet() + ", " : "");
		sb.append(ocato.getHouse() != null ? ocato.getHouse() + ", " : "");
		sb.append(ocato.getBldn() != null ? ocato.getBldn() + ", " : "");
		sb.append(ocato.getFlat() != null ? ocato.getFlat() + ", " : "");

		return sb.substring(0, sb.lastIndexOf(","));
	}
}

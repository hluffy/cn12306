package entity;

import java.sql.Timestamp;

public class MapWatchData {
	private String LatitudeName;
	private String LongitudeName;
//	private Integer DateCreated;
	private String IsPosition;
	private String Address;
	private Integer Id;
	private String type;
	private Integer Elec;
	private Long Imei;
	private Timestamp DateCreated;
	public String getLatitudeName() {
		return LatitudeName;
	}
	public void setLatitudeName(String latitudeName) {
		LatitudeName = latitudeName;
	}
	public String getLongitudeName() {
		return LongitudeName;
	}
	public void setLongitudeName(String longitudeName) {
		LongitudeName = longitudeName;
	}
	public Timestamp getDateCreated() {
		return DateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		DateCreated = dateCreated;
	}
	public String getIsPosition() {
		return IsPosition;
	}
	public void setIsPosition(String isPosition) {
		IsPosition = isPosition;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getElec() {
		return Elec;
	}
	public void setElec(Integer elec) {
		Elec = elec;
	}
	public Long getImei() {
		return Imei;
	}
	public void setImei(Long imei) {
		Imei = imei;
	}
	

}

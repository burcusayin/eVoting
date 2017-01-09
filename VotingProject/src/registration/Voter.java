package registration;

import java.util.Date;




/*
 * Author :Arzum
 * Date : November 26, 2016
 */
public class Voter {

	/*instance variables*/
	private int vid;
	private String vname;
	private String email;
	private Date birthdate;
	private String certificateName;
	
	/*constructor*/
	public Voter(int vid, String vname, String email, Date birthdate,
			String certificateName) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.email = email;
		this.birthdate = birthdate;
		this.certificateName = certificateName;
	}

	public Voter(String vname, String email, Date birthdate,
			String certificateName) {
		super();
		this.vname = vname;
		this.email = email;
		this.birthdate = birthdate;
		this.certificateName = certificateName;
	}

	/*setters and getters*/
	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	@Override
	public String toString() {
		return "Voter [vid=" + vid + ", vname=" + vname + ", email=" + email
				+ ", birthdate=" + birthdate + ", certificateName="
				+ certificateName + "]";
	}
	

}

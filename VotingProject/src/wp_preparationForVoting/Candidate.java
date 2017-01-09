package wp_preparationForVoting;

import java.util.Date;

/*
 * Author :Arzum
 * Date : November 26, 2016
 */
public class Candidate {
	/*attributes*/
	private int cid;
	private String name;
	private String job;
	private Date birthdate;
	
	//constructors
	public Candidate(int cid, String name, String job, Date birthdate) {
		
		this.cid = cid;
		this.name = name;
		this.job = job;
		this.birthdate = birthdate;
	}


	public Candidate(String name, String job, Date birthdate) {
		super();
		this.name = name;
		this.job = job;
		this.birthdate = birthdate;
	}

	/*getters*/
	public int getCid() {
		return cid;
	}


	public String getName() {
		return name;
	}


	public String getJob() {
		return job;
	}


	public Date getBirthdate() {
		return birthdate;
	}

	/*setters*/		
	public void setCid(int cid) {
		this.cid = cid;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/*toString*/	
	@Override
	public String toString() {
		return "Candidate [cid=" + cid + ", name=" + name + ", job=" + job
				+ ", birthdate=" + birthdate + "]";
	}
	
  
	
}

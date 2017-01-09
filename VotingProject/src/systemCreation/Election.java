package systemCreation;

import java.util.Date;

public class Election {

	private Date regStartTime;
	private Date regEndTime;
	private Date electionStartTime;
	private Date electionEndTime;
	private boolean status;
	
	public Election(Date regStartTime, Date regEndTime, Date electionStartTime, Date electionEndTime, boolean status) {
		this.regStartTime = regStartTime;
		this.regEndTime = regEndTime;
		this.electionStartTime = electionStartTime;
		this.electionEndTime = electionEndTime;
		this.status = status;
	}
	
	public Date getRegStartTime() {
		return regStartTime;
	}
	public void setRegStartTime(Date regStartTime) {
		this.regStartTime = regStartTime;
	}
	public Date getRegEndTime() {
		return regEndTime;
	}
	public void setRegEndTime(Date regEndTime) {
		this.regEndTime = regEndTime;
	}
	public Date getElectionStartTime() {
		return electionStartTime;
	}
	public void setElectionStartTime(Date electionStartTime) {
		this.electionStartTime = electionStartTime;
	}
	public Date getElectionEndTime() {
		return electionEndTime;
	}
	public void setElectionEndTime(Date electionEndTime) {
		this.electionEndTime = electionEndTime;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}

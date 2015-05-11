package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */

public class NewId implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sname;

	// Constructors

	/** default constructor */
	public NewId() {
	}

	/** full constructor */
	public NewId(Integer sid, String sname) {
		this.sid = sid;
		this.sname = sname;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NewId))
			return false;
		NewId castOther = (NewId) other;

		return ((this.getSid() == castOther.getSid()) || (this.getSid() != null
				&& castOther.getSid() != null && this.getSid().equals(
				castOther.getSid())))
				&& ((this.getSname() == castOther.getSname()) || (this
						.getSname() != null && castOther.getSname() != null && this
						.getSname().equals(castOther.getSname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSid() == null ? 0 : this.getSid().hashCode());
		result = 37 * result
				+ (getSname() == null ? 0 : this.getSname().hashCode());
		return result;
	}

}
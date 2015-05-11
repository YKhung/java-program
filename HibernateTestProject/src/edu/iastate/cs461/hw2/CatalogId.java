package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */

public class CatalogId implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Integer pid;

	// Constructors

	/** default constructor */
	public CatalogId() {
	}

	/** full constructor */
	public CatalogId(Integer sid, Integer pid) {
		this.sid = sid;
		this.pid = pid;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatalogId))
			return false;
		CatalogId castOther = (CatalogId) other;

		return ((this.getSid() == castOther.getSid()) || (this.getSid() != null
				&& castOther.getSid() != null && this.getSid().equals(
				castOther.getSid())))
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSid() == null ? 0 : this.getSid().hashCode());
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		return result;
	}

}
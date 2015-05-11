package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */

public class FriendsId implements java.io.Serializable {

	// Fields

	private String person;
	private String friend;

	// Constructors

	/** default constructor */
	public FriendsId() {
	}

	/** full constructor */
	public FriendsId(String person, String friend) {
		this.person = person;
		this.friend = friend;
	}

	// Property accessors

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getFriend() {
		return this.friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FriendsId))
			return false;
		FriendsId castOther = (FriendsId) other;

		return ((this.getPerson() == castOther.getPerson()) || (this
				.getPerson() != null && castOther.getPerson() != null && this
				.getPerson().equals(castOther.getPerson())))
				&& ((this.getFriend() == castOther.getFriend()) || (this
						.getFriend() != null && castOther.getFriend() != null && this
						.getFriend().equals(castOther.getFriend())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPerson() == null ? 0 : this.getPerson().hashCode());
		result = 37 * result
				+ (getFriend() == null ? 0 : this.getFriend().hashCode());
		return result;
	}

}
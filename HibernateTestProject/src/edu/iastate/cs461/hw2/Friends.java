package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */

public class Friends implements java.io.Serializable {

	// Fields

	private FriendsId id;

	// Constructors

	/** default constructor */
	public Friends() {
	}

	/** full constructor */
	public Friends(FriendsId id) {
		this.id = id;
	}

	// Property accessors

	public FriendsId getId() {
		return this.id;
	}

	public void setId(FriendsId id) {
		this.id = id;
	}

}
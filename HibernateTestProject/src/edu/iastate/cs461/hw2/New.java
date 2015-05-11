package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */

public class New implements java.io.Serializable {

	// Fields

	private NewId id;

	// Constructors

	/** default constructor */
	public New() {
	}

	/** full constructor */
	public New(NewId id) {
		this.id = id;
	}

	// Property accessors

	public NewId getId() {
		return this.id;
	}

	public void setId(NewId id) {
		this.id = id;
	}

}
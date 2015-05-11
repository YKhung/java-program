package edu.iastate.cs461.hw2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yin Kam Hung
 *
 */

public class Parts implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String pname;
	private String color;
	private Set catalogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Parts() {
	}

	/** full constructor */
	public Parts(String pname, String color, Set catalogs) {
		this.pname = pname;
		this.color = color;
		this.catalogs = catalogs;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(Set catalogs) {
		this.catalogs = catalogs;
	}

}
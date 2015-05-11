package edu.iastate.cs461.hw2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yin Kam Hung
 *
 */

public class Suppliers implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sname;
	private String address;
	private Set catalogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Suppliers() {
	}

	/** full constructor */
	public Suppliers(String sname, String address, Set catalogs) {
		this.sname = sname;
		this.address = address;
		this.catalogs = catalogs;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(Set catalogs) {
		this.catalogs = catalogs;
	}

}
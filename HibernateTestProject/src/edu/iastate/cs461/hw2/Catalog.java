package edu.iastate.cs461.hw2;

/**
 * @author Yin Kam Hung
 *
 */
public class Catalog implements java.io.Serializable {

	// Fields

	private CatalogId id;
	private Suppliers suppliers;
	private Parts parts;
	private Double cost;

	// Constructors

	/** default constructor */
	public Catalog() {
	}

	/** minimal constructor */
	public Catalog(CatalogId id, Suppliers suppliers, Parts parts) {
		this.id = id;
		this.suppliers = suppliers;
		this.parts = parts;
	}

	/** full constructor */
	public Catalog(CatalogId id, Suppliers suppliers, Parts parts, Double cost) {
		this.id = id;
		this.suppliers = suppliers;
		this.parts = parts;
		this.cost = cost;
	}

	// Property accessors

	public CatalogId getId() {
		return this.id;
	}

	public void setId(CatalogId id) {
		this.id = id;
	}

	public Suppliers getSuppliers() {
		return this.suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	public Parts getParts() {
		return this.parts;
	}

	public void setParts(Parts parts) {
		this.parts = parts;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
package com.test;

import java.sql.Timestamp;

import com.org.annotations.Entity;

@Entity(tableName = "a_test")
public class ATest {
	private Long id;
	private String name;
	private Timestamp date;
	private Long px;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the px
	 */
	public Long getPx() {
		return px;
	}

	/**
	 * @param px
	 *            the px to set
	 */
	public void setPx(Long px) {
		this.px = px;
	}

}

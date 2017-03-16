package com.global.dto;

import java.util.List;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
import com.datastax.driver.core.DataType;

/**
 * The Class User.
 */
@Table
public class User {

	/** The id. */
	@PrimaryKey
	private String id;
	
	/** The name. */
	private String name;
	
	/** The key. */
	private String key;
	
	/** The web services. */
	@CassandraType(type = DataType.Name.UDT, userTypeName = "WebService")
	private List<WebService> webServices;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the web services.
	 *
	 * @return the web services
	 */
	public List<WebService> getWebServices() {
		return webServices;
	}

	/**
	 * Sets the web services.
	 *
	 * @param webServices the new web services
	 */
	public void setWebServices(List<WebService> webServices) {
		this.webServices = webServices;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
}

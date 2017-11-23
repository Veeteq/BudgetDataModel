package com.budget.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.budget.data.utils.DataTransformationStage;
import com.budget.data.utils.DataTransformationSubtype;
import com.budget.data.utils.DataTransformationType;

@Entity
@Table(name="data_transformations")
public class DataTransformation {

	@Id
	@Column(name="transform_key", unique=true, nullable=false, columnDefinition="VARCHAR2", length=30)
	private String key;
	
	@Column(name="description")
	private String description;

	@Column(name="type", nullable=false, columnDefinition="VARCHAR2", length=10)
	@Enumerated(EnumType.STRING)
	private DataTransformationType type;
	
	@Column(name="subtype", nullable=false, columnDefinition="VARCHAR2", length=10)
	@Enumerated(EnumType.STRING)
	private DataTransformationSubtype subtype;
	
	@Column(name="stage", nullable=false, columnDefinition="VARCHAR2", length=10)
	@Enumerated(EnumType.STRING)
	private DataTransformationStage stage;
	
	@JoinColumn(name="feed_key")
	@ManyToOne(fetch=FetchType.EAGER)
	private DataFeed feedKey;
	
	@Column(name="is_active")
	private Boolean active;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DataTransformationType getType() {
		return type;
	}

	public void setType(DataTransformationType type) {
		this.type = type;
	}

	public DataTransformationSubtype getSubtype() {
		return subtype;
	}

	public void setSubtype(DataTransformationSubtype subtype) {
		this.subtype = subtype;
	}

	public DataTransformationStage getStage() {
		return stage;
	}

	public void setStage(DataTransformationStage stage) {
		this.stage = stage;
	}

	public DataFeed getFeedKey() {
		return feedKey;
	}

	public void setFeedKey(DataFeed feedKey) {
		this.feedKey = feedKey;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DataTransformation [key=" + key + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTransformation other = (DataTransformation) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}

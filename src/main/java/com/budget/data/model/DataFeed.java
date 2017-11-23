package com.budget.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.budget.data.utils.DataFeedStatus;

@Entity
@Table(name="data_feed")
public class DataFeed {

	@Id
	@Column(name="feed_key", unique=true, nullable=false, columnDefinition="VARCHAR2", length=30)
	private String key;
	
	@Column(name="feed_name", nullable=false)
	private String name;
	
	@Column(name="feed_description")
	private String description;
	
	@Column(name="feed_status", nullable=false, columnDefinition="VARCHAR2", length=10)
	@Enumerated(EnumType.STRING)
	private DataFeedStatus dataFeedStatus;
	
	@Column(name="feed_type")
	private String type;
	
	@Column(name="feed_provider")
	private String provider;
	
	@Column(name="feed_category")
	private String category;

	@Column(name="instance")
	private String instance;
	
	@Column(name="content_type")
	private String contentType;
	
	@Column(name="file_template")
	private String fileTemplate;
	
	@Column(name="src_subpart_cnt")
	private Byte srcSubpartitions;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DataFeedStatus getDataFeedStatus() {
		return dataFeedStatus;
	}
	public void setDataFeedStatus(DataFeedStatus dataFeedStatus) {
		this.dataFeedStatus = dataFeedStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileTemplate() {
		return fileTemplate;
	}
	public void setFileTemplate(String fileTemplate) {
		this.fileTemplate = fileTemplate;
	}
	public Byte getSrcSubpartitions() {
		return srcSubpartitions;
	}
	public void setSrcSubpartitions(Byte srcSubpartitions) {
		this.srcSubpartitions = srcSubpartitions;
	}
	@Override
	public String toString() {
		return "DataFeed [name=" + name + "]";
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
		DataFeed other = (DataFeed) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}

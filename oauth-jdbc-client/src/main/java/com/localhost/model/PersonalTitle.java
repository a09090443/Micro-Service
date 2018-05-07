package com.localhost.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "personal_title")
public class PersonalTitle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name = "title_id", nullable = false, length = 2, unique=true)
	private String titleId;
	
	@Column(name = "title_name", length = 10)
	private String titleName;

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public PersonalTitle setTitleName(String titleName) {
		this.titleName = titleName;
        return this;
	}

	public PersonalTitle setTitleName(Role titleName) {
		this.titleName = titleName.toString();
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titleId == null) ? 0 : titleId.hashCode());
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		PersonalTitle other = (PersonalTitle) obj;
		if (titleId == null) {
			if (other.titleId != null)
				return false;
		} else if (!titleId.equals(other.titleId))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonalTitle [titleId=" + titleId + ", titleName=" + titleName + "]";
	}

}

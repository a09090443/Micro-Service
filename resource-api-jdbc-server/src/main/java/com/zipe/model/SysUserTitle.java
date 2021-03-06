package com.zipe.model;

import com.zipe.enums.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_title")
public class SysUserTitle implements Serializable {
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

	public SysUserTitle setTitleName(String titleName) {
		this.titleName = titleName;
        return this;
	}

	public SysUserTitle setTitleName(Role titleName) {
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
		SysUserTitle other = (SysUserTitle) obj;
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

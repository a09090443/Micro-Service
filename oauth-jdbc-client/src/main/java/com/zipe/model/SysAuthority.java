package com.zipe.model;

import com.zipe.enums.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Authority entity. Used by OAuth2 workflow and security annotations.
 * User can have a few authorities.
 *
 */
@Entity
@Table(name = "sys_authority")
public class SysAuthority implements Serializable{
	static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name = "authority_id", nullable = false, length = 2, unique=true)
	private String authorityId;

    @NotNull
	@Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
        return name;
    }

    public SysAuthority setName(String name) {
        this.name = name;
        return this;
    }

    public SysAuthority setName(Role name) {
        this.name = name.toString();
        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SysAuthority other = (SysAuthority) obj;
		if (authorityId == null) {
			if (other.authorityId != null)
				return false;
		} else if (!authorityId.equals(other.authorityId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Authority [authorityId=" + authorityId + ", name=" + name + "]";
	}

}

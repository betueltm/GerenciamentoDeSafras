package br.edu.unoesc.common.entity.util;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Transient;

public abstract class AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	private EnumEntityState entityState;
	
	public abstract Long getId();

	public AbstractEntity() {
		this.entityState = EnumEntityState.UNMODIFIED;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(Objects.isNull(obj)) return false;
		if(this == obj) return true;
		if(!(obj instanceof AbstractEntity)) return false;
		
		AbstractEntity me = (AbstractEntity) this;
		AbstractEntity other = (AbstractEntity) obj;
		
		Long meId = me.getId();
		Long otherId = other.getId();
		
		if(Objects.nonNull(meId) && Objects.nonNull(otherId)) {
			return meId.equals(otherId);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.isNull(getId()) ? super.hashCode() : getId().intValue();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + getId();
	}
	
	public EnumEntityState getEntityState() {
		return entityState;
	}

	public void setEntityState(EnumEntityState entityState) {
		this.entityState = entityState;
	}
	
	public boolean isNew() {
		return EnumEntityState.NEW.equals(entityState);
	}
	
	public boolean isModified() {
		return EnumEntityState.MODIFIED.equals(entityState);
	}
	
	public boolean isDeleted() {
		return EnumEntityState.DELETED.equals(entityState);
	}
	
	public boolean isUnmodified() {
		return EnumEntityState.UNMODIFIED.equals(entityState);
	}
}

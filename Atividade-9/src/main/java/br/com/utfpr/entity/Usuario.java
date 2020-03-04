package br.com.utfpr.entity;

import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NamedStoredProcedureQuery(
	name = "Usuario.soma",
	procedureName = "procedure_soma",
	parameters = {
		@StoredProcedureParameter(
			mode = ParameterMode.IN,
			name = "arg",
			type = Integer.class),
		@StoredProcedureParameter(
			mode = ParameterMode.OUT,
			name = "res",
			type = Integer.class)
	})
public class Usuario extends AbstractPersistable<Long> {

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}
}

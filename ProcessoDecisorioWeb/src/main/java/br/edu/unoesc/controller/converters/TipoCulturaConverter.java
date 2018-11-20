package br.edu.unoesc.controller.converters;

import java.util.Objects;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.TipoCultura;

@ManagedBean
@FacesConverter(value="tipoCulturaConverter")
public class TipoCulturaConverter implements Converter{

	@EJB
	private TipoCulturaService tipoCulturaService;

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.out.println("getAsString " + value );
		if(Objects.nonNull(value)) {
			TipoCultura tipoCultura = (TipoCultura)value;
			return tipoCultura.getId().toString();
		}
		return null;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("getAsObject " + value);
		if(Objects.nonNull(value) && !value.isEmpty()) {
			Long idTipoCultura = Long.valueOf(value);
			return tipoCulturaService.buscar(idTipoCultura);
		}
		return null;
	}
	
	
}

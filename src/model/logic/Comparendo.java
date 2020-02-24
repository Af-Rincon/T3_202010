package model.logic;

import java.util.Date;

public class Comparendo implements Comparable<Comparendo> {

	private int objectId;
	private Date fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;
	
	public Comparendo(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}
	
	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	
	public int darObjectId()
	{
		return objectId;
	}
	public String darInfr()
	{
		return infraccion;
	}
	
	public String datosCluster()
	{
		return "INFRACCION: "+infraccion+" OBJECTID: "+objectId+" FECHA_HORA: "+fecha_hora+" CLASE_VEHI:"+clase_vehi+" TIPO_SERVI:"+tipo_servi+" LOCALIDAD: "+localidad;	
	}
	
	public Date darFecha()
	{
		return fecha_hora;
	}
	
	public int darObjId()
	{
		return objectId;
	}
	
	@Override
	public int compareTo(Comparendo o) {
		// TODO Auto-generated method stub
		int num = 0;
		if(fecha_hora.before(o.darFecha()))
		{
			num = -1;
		}
		if(fecha_hora.after(o.darFecha()))
		{
			num = 1;
		}
		if(fecha_hora.equals(o.darFecha()))
		{
			num = 0;
		}
		if(num==0)
		{
			if(objectId<o.darObjId())
			{
				num = -1;
			}
			if(objectId>o.darObjId())
			{
				num = 1;
			}
			if(objectId==o.darObjectId())
			{
				num = 0;
			}
		}
		return num;
	}

}

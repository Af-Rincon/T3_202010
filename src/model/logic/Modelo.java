package model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.Queue;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
private Queue<Comparendo> datos;
	
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
	private static Comparable[] aux;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new Queue<Comparendo>();
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}
	
	public Queue<Comparendo> repetidos()
	{
		if(datos==null)
		{
			cargar();
		}

		Queue<Comparendo> mayor = new Queue<Comparendo>();
		Queue<Comparendo> temp = new Queue<Comparendo>();
		String tipo = datos.darPrimerElemento().darInfr();

		for(Comparendo c: datos)
		{
			datos.dequeue();
			if(tipo.equals(c.darInfr()))
			{	
				temp.enqueue(c);
			}
			else
			{
				tipo = c.darInfr();
				temp = new Queue<Comparendo>() ;
				temp.enqueue(c);
			}
			if(temp.darTamano()>mayor.darTamano())
			{
				mayor = temp;
			}
		}

		return mayor;
	}

	public Comparendo darPrimeroCola()
	{
		return datos.darPrimerElemento();
	}
	
	public void cargar()
	{
		JsonReader reader;
		
		try
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			
			
			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				
				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 
				
				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();
				
				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				datos.enqueue(c);
		}
	}
		catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public Comparendo[] crearArreglo()
	{
		Comparendo[] comparendos = new Comparendo[datos.darTamano()];
		int i = 0;
		for(Comparendo e:datos)
		{
			comparendos[i] = datos.dequeue();
			i++;
		}
		return comparendos;
	}
	
	public void mergeSort()
	{
		sort(crearArreglo());	
	}
	
	// auxiliary array for merges
	public static void sort(Comparable[] a) {
	aux = new Comparable[a.length];
	sort(a, 0, a.length - 1); // Allocate space just once.
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
	if (hi <= lo) return; // Sort a[lo..hi].
	int mid = lo + (hi - lo)/2;
	sort(a, lo, mid);
	// Sort left half.
	sort(a, mid+1, hi);
	// Sort right half.
	merge(a, lo, mid, hi);
	}
	
	public static void merge(Comparable[] a, int lo, int mid, int hi)
	{
	// Merge a[lo..mid] with a[mid+1..hi].
	int i = lo, j = mid+1;
	for (int k = lo; k <= hi; k++)
	// Copy a[lo..hi] to aux[lo..hi].
	aux[k] = a[k];
	for (int k = lo; k <= hi; k++)
	// Merge back to a[lo..hi].
	if (i > mid) a[k] = aux[j++];
	else if (j > hi ) a[k] = aux[i++];
	else if (less(aux[j], aux[i])) a[k] = aux[j++];
	else a[k] = aux[i++];
	}
	
	public static  boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b)<0;
	}
	
}

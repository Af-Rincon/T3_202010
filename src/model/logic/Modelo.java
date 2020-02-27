package model.logic;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

	public static String PATH = "./data/comparendos_dei_2018.geojson";
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

	public Comparendo darUltimoCola()
	{
		return datos.darUltimoElemento();
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

	public Comparendo[] copiarArreglo()
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

	//Merge
	public void mergeSort()
	{
		sort(copiarArreglo());	
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

	//Quick


	public static void quickSort(Comparable[] a)
	{
		shuffle(a);
		sortTwo(a, 0, a.length - 1);
	}

	private static void sortTwo(Comparable[] a, int lo, int hi)
	{
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi)
	{
		int i = lo, j = hi+1;
		while (true) 
		{
			while (less(a[++i], a[lo]))
				if (i == hi) break;
			while (less(a[lo], a[--j]))
				if (j == lo) break;

			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	public static void exch(Comparable[] a, int i, int j)
	{
		Comparable temporal = a[i];
		a[i] = a[j];
		a[j] = temporal;		
	}

	private static void shuffle(Comparable[] a)
	{
		Random rd = new Random();

		for(int i = a.length -1; i>0;i--)
		{
			int index = rd.nextInt(i+1);
			Comparable n = a[index];
			a[index] = a[i];
			a[i] = n;
		}
	}

	//Shell
	public void shellSort(Comparable[] a)
	{
		int N = a.length;
		int h = 1;
		while (h < N/3)
			h = 3*h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
					exch(a, j, j-h);
			}
			h = h/3;
		}
	}


	public static  boolean less(Comparable a, Comparable b)
	{
		return a.compareTo(b)<0;
	}

}

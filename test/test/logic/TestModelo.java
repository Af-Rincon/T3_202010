package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {

	private Modelo modelo;
	private Integer[] arreglo;

	@Before
	public void setUp1() {
		modelo= new Modelo();
		arreglo = new Integer[1000]; 
	}


	@Test
	public void testShellSortAleatorio()
	{
		// Datos aleatorios

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=(int)(Math.random());
		}

		modelo.shellSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testShellSortOrdenadoAscendente()
	{
		// Datos ordenados ascendentemente

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=i;
		}

		modelo.shellSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testShellSortOrdenadoDescendente()
	{
		// Datos ordenados descendentemente
		for(int i=0;i<1000;i++)
		{
			arreglo[i]=1000-i;
		}

		modelo.shellSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}

	@Test
	public void testMergeSortAleatorio()
	{
		// Datos aleatorios

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=(int)(Math.random());
		}

		modelo.sort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testMergeSortOrdenadoAscendente()
	{
		// Datos ordenados ascendentemente

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=i;
		}

		modelo.sort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testMergeSortOrdenadoDescendente()
	{
		// Datos ordenados descendentemente

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=1000-i;
		}

		modelo.sort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testQuickSortAleatorio()
	{
		// Datos aleatorios

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=(int)(Math.random());
		}

		modelo.quickSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testQuickOrdenadoAscendente()
	{
		// Datos ordenados ascendentemente

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=i;
		}

		modelo.quickSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}


	@Test
	public void testQuickSortOrdenadoDescendente()
	{
		// Datos ordenados descendentemente 

		for(int i=0;i<1000;i++)
		{
			arreglo[i]=1000-i;
		}

		modelo.quickSort(arreglo);

		for(int i=0;i<arreglo.length-1;i++)
		{
			assertTrue("El arreglo esta mal",arreglo[i].compareTo(arreglo[i+1])<=0);
		}

	}






}

package controller;

import java.util.Scanner;

import model.data_structures.Queue;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo = new Modelo();

				long start = System.currentTimeMillis();
				modelo.cargar(); 
				long end = System.currentTimeMillis();


				view.printMessage("Datos de comparendos cargados.");
				view.printMessage("Numero total de comparendos " + modelo.darTamano() + "\n---------");		
				view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);
				
				view.printMessage("Primer dato de la cola: " + modelo.darPrimeroCola() + "\n");
				view.printMessage("Ultimo dato de la cola: "+modelo.darUltimoCola()+"\n");
				break;				

			case 2: 
				
				
				Comparable copia [ ] = modelo.copiarArreglo();
				view.printMessage("Copia creada");
				
				view.printMessage("\n---------");
				view.printMessage("Primeros 10 comparendos" + "\n---------");
				for(int i = 0; i < 10 ; i++)
				{
					Comparendo aRetornar = (Comparendo)copia[i];
					view.printMessage(aRetornar.datosCluster());
				}
				
				view.printMessage("\n---------");
				view.printMessage("Ultimos 10 comparendos" + "\n---------");
				for(int j= copia.length-10; j < copia.length; j++)
				{
					Comparendo aRetornar = (Comparendo)copia[j];
					view.printMessage(aRetornar.datosCluster());
				}
				
			

				break;

			case 3:
				
				
				Comparable copia_Comparendos [ ] = modelo.copiarArreglo();
				long startTime = System.currentTimeMillis();
				modelo.shellSort(copia_Comparendos);
				long endTime = System.currentTimeMillis(); 
				long duration = endTime - startTime; 
				
				view.printMessage("Tiempo de ordenamiento: " + duration + " milisegundos");
				
				view.printMessage("\n---------");
				view.printMessage("Primeros 10 comparendos" + "\n---------");
				
				for(int i = 0; i < 10 ; i++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos [i];
					view.printMessage(aRetornar.datosCluster());
				}
				
				view.printMessage("\n---------");
				view.printMessage("Ultimos 10 comparendos" + "\n---------");
				for(int j= copia_Comparendos .length-10; j < copia_Comparendos .length; j++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos [j];
					view.printMessage(aRetornar.datosCluster());
				}

				break;
				
			case 4: 
				 
				
				Comparable copia_Comparendos_2 [ ] = modelo.copiarArreglo();
				long startTime2 = System.currentTimeMillis();
				modelo.sort(copia_Comparendos_2);
				long endTime2 = System.currentTimeMillis(); 
				long duration2 = endTime2 - startTime2; 
				
				view.printMessage("Tiempo de ordenamiento: " + duration2 + " milisegundos");
				
				view.printMessage("\n---------");
				view.printMessage("Primeros 10 comparendos" + "\n---------");
				for(int i = 0; i < 10 ; i++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos_2 [i];
					view.printMessage(aRetornar.datosCluster());
				}
				
				view.printMessage("\n---------");
				view.printMessage("Ultimos 10 comparendos" + "\n---------");
				for(int j= copia_Comparendos_2 .length-10; j < copia_Comparendos_2 .length; j++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos_2 [j];
					view.printMessage(aRetornar.datosCluster());
				}

				break;
				
				
			case 5: 
				
				
				Comparable copia_Comparendos_3 [ ] = modelo.copiarArreglo();
				long startTime3 = System.currentTimeMillis();
				modelo.sort(copia_Comparendos_3);
				long endTime3 = System.currentTimeMillis(); 
				long duration3 = endTime3 - startTime3; 
				
				view.printMessage("Tiempo de ordenamiento: " + duration3 + " milisegundos");
				
				view.printMessage("\n---------");
				view.printMessage("Primeros 10 comparendos" + "\n---------");
				for(int i = 0; i < 10 ; i++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos_3 [i];
					view.printMessage(aRetornar.datosCluster());
				}
				
				view.printMessage("\n---------");
				view.printMessage("Ultimos 10 comparendos" + "\n---------");
				for(int j= copia_Comparendos_3 .length-10; j < copia_Comparendos_3 .length; j++)
				{
					Comparendo aRetornar = (Comparendo)copia_Comparendos_3 [j];
					view.printMessage(aRetornar.datosCluster());
				}
				
				break;


			case 6:
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
	
}

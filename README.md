## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.    

Lo anterior requiere:  
* __Agregar las dependencias de Spring.__  
Las dependencias ya se encontraban en el pom.


* __Agregar la configuración de Spring.__
Se agregó el archivo applicationContext.xml para que Spring busqué automáticamente los beans.


* __Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.__  
Se agregó la anotación @Service en las clases que iban a inyectarse, o que son dependencias.

2. __Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.__  

	Se realizó la implementació correspondiente en el código.



3. __Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.__  
	
	![img.png](img/img.png)  
Al ejecutar el programa, vemos que se cumplen las funcionalidades.

4. __Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:__    

	* __(A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.__
	* __(B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.__  
   Se implementaron funcionalidades en dos nuevas clases que implementan una interfaz común denominada BluePrintFilter. Cada una de estas cumple con una de los comportamientos descritos.
   
5. __Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B).__  
Se agregaron los test correspondientes. Al poner la anotación @Primary en la clase RedundanciesBluePrintFilter, la clase BlueprintServices inyecta esta dependencia, eliminando puntos repetidos:
![img_1.png](img/img_1.png)  
Al poner la anotación @Primary en la clase SubsamplingBlueprintServices, la clase BlueprintServices inyectaba esta dependencia, eliminando puntos de manera intercalada:
![img_2.png](img/img_2.png)  


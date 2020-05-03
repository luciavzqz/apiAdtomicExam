# ApiAdtomicExam

_Nos encontramos con la necesidad de reparar una camioneta Ford Ranger modelo 2012. Para tal fin, debemos conseguir 3 partes de la misma. Tenemos 3 proveedores diferentes y queremos conseguir el mejor precio de compra._
_Cada proveedor nos hace descuentos especiales dependiendo del día, y todo está sujeto a una inflación semestral del 25%._

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Uso** para conocer cómo usar los EndPoints.

### Problema 📋

_Descripción del problema._

```
- Nos encontramos con la necesidad de reparar una camioneta Ford Ranger modelo 2012. Para tal fin, debemos conseguir 3 partes de la misma

- Las partes a conseguir son: 
		Optica delantera derecha : Con un valor de mercado de 6100 $ARS a comienzos del año 2019 (óptica)
		Parrilla frontal: Con un valor de mercado de 5200 $ARS a comienzos del año 2019 (carrocería)
		Paragolpe delantero: Con un valor de mercado de 7600 $ARS a comienzos del año 2019 (carrocería)

- En Argentina existen 3 proveedores de autopartes para dicho modelo de camioneta:
		El primero es "AutosAR"
		El segundo es "Buenos Aires Cars"
		El tercero es "Good Repair"

- Los 3 proveedores tienen diferentes promociones a lo largo del mes:
		AutosAr: El tercer sábado de cada mes ofrece un 15% de descuento en carrocería de automóviles pagando en efectivo.
		Buenos Aires Cars: A partir del 1er dia de cada mes tiene un 11% de descuento en ópticas cada 5 dias (Ejemplo : dia 1, 6, 11 ...) pagando con tarjeta de crédito.
		Good Repair: De lunes miercoles de cada mes tiene un 20% de descuento en opticas abonando con tarjeta de credito. Ademas, los dias jueves y viernes de cada mes, ofrece un 6% de descuento en carrocerias pagando en efectivo.

- IMPORTANTE: Debido a la situacion del país, se estima a partir del dia 1/7/2019 que la inflacion promedio es de un 25% semestral. Esta misma impacta a partir de los dias 1 de Enero y 1 de Julio de cada año.
```

### Cómo correr la API 🔧

1. Cloná el repositorio
2. Arrancá el servidor de MySQL
3. Corré la consola de MySQL
4. Crea el schema en la Base de Datos
5. Entrá al archivo `application.properties` y configurá los siguientes datos con los tuyos:
  ```
  #
  #  DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
  #

  spring.datasource.url = jdbc:mysql://localhost:[PORT]/[NOMBRE_DEL_SCHEMA]?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC 
  spring.datasource.username=[USERNAME]
  spring.datasource.password=[PASSWORD]

  #
  # JPA (JpaBaseConfiguration, HibernateJpaAutoConfiguration)
  #

  spring.jpa.properties.hibernate.default_schema=[NOMBRE_DEL_SCHEMA]
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  ```
6. Por último, corré el proyecto y hacé un post a la siguiente dirección `http://127.0.0.1:8080/apiAdtomic/init`.

## Uso 📦

### _Descripción de los códigos_

###### Códigos de proveedores
* AUTOS_AR("AutosAr")
* BUENOS_AIRES_CARS("Buenos Aires Cars")
* GOOD_REPAIR("Good Repair")

###### Códigos de las partes de la camioneta
* DELANTERA_DERECHA("Óptica delantera derecha")
* PARRILLA_FRONTAL("Parrilla frontal")
* PARAGOLPE_DELANTERO("Paragolpe delantero")

###### Códigos de los métodos de pago
* TARJETA_CREDITO
* TARJETA_DEBITO
* EFECTIVO
* TODOS

### _Descripción del uso de los EndPoints de la API._

#### 1. Endpoint que permite saber, dada una fecha en particular, la mejor opción de compras futuras combinando los 3 productos deseados y el monto total mismo:
###### Parámetros
- date: fecha con formato dd-MM-yyyy
###### RequestBody
-
###### Ejemplos
* Ejemplo del GET:
`http://127.0.0.1:8080/apiAdtomic/partes?date=19-05-2020`  (Utilizar formato: dd-MM-yyyy)

* Ejemplo de la respuesta:
  ```
	{
	    "timestamp": "2020-05-03T04:16:58.632+0000",
	    "status": 200,
	    "title": "Petición exitosa",
	    "message": null,
	    "data": {
		"fecha": "19-05-2020",
		"monto": 26397.22,
		"PARAGOLPE_DELANTERO": {
		    "proveedor": "GOOD_REPAIR",
		    "monto": 11347.22,
		    "metodo_pago": "TODOS"
		},
		"PARRILLA_FRONTAL": {
		    "proveedor": "GOOD_REPAIR",
		    "monto": 7763.88,
		    "metodo_pago": "TODOS"
		},
		"DELANTERA_DERECHA": {
		    "proveedor": "GOOD_REPAIR",
		    "monto": 7286.11,
		    "metodo_pago": "TARJETA_CREDITO"
		}
	    }
	}
  ```
#### 2. Endpoint que permite saber, dado un mes y un año en particular, la mejor opcion de compra (día) para los 3 productos deseados y el monto total mismo:
###### Parámetros
- date: fecha con formato MM-yyyy
###### RequestBody
- 
###### Ejemplos
*	Ejemplo del GET:
`http://127.0.0.1:8080/apiAdtomic/partes?date=05-2019`  (Utilizar formato: MM-yyyy)

* Ejemplo de la respuesta:
  ```
	{
	    "timestamp": "2020-05-03T04:19:32.468+0000",
	    "status": 200,
	    "title": "Petición exitosa",
	    "message": null,
	    "data": {
		"fecha": "18-05-2019",
		"monto": 16980.0,
		"PARAGOLPE_DELANTERO": {
		    "proveedor": "AUTOS_AR",
		    "monto": 6460.0,
		    "metodo_pago": "EFECTIVO"
		},
		"PARRILLA_FRONTAL": {
		    "proveedor": "AUTOS_AR",
		    "monto": 4420.0,
		    "metodo_pago": "EFECTIVO"
		},
		"DELANTERA_DERECHA": {
		    "proveedor": "GOOD_REPAIR",
		    "monto": 6100.0,
		    "metodo_pago": "TODOS"
		}
	    }
	}
  ```
#### 3. Endpoint que permite guardar en base de datos una compra
###### Parámetros
-
###### RequestBody
```
	{
	    "monto": 16980.0,
	    "partes": [
		    {
			"parte": "DELANTERA_DERECHA",
			"proveedor": "BUENOS_AIRES_CARS",
			"monto": 6100.0,
			"metodo_pago": "TODOS"
		    },
		    {
			"parte": "PARAGOLPE_DELANTERO",
			"proveedor": "AUTOS_AR",
			"monto": 6460.0,
			"metodo_pago": "EFECTIVO"
		    },
		    {
			"parte":"PARRILLA_FRONTAL",
			"proveedor": "AUTOS_AR",
			"monto": 4420.0,
			"metodo_pago": "EFECTIVO"
		    }
	    ]
	}
```
###### Ejemplos
* Ejemplo del POST:
`http://127.0.0.1:8080/apiAdtomic/compras`

* Ejemplo del RequestBody:
  ```
   	{
	    "monto": 16980.0,
	    "partes": [
		    {
			"parte": "DELANTERA_DERECHA",
			"proveedor": "BUENOS_AIRES_CARS",
			"monto": 6100.0,
			"metodo_pago": "TODOS"
		    },
		    {
			"parte": "PARAGOLPE_DELANTERO",
			"proveedor": "AUTOS_AR",
			"monto": 6460.0,
			"metodo_pago": "EFECTIVO"
		    },
		    {
			"parte":"PARRILLA_FRONTAL",
			"proveedor": "AUTOS_AR",
			"monto": 4420.0,
			"metodo_pago": "EFECTIVO"
		    }
	    ]
	}
  ```
 
* Ejemplo de la respuesta:
  ```
	{
	    "timestamp": "2020-05-03T04:20:59.690+0000",
	    "status": 200,
	    "title": "Petición exitosa",
	    "message": null,
	    "data": {
		"fechaCompra": 1588479658672,
		"id": 1,
		"monto": 16980.0,
		"itemsCompra": [
		    {
			"id": 1,
			"proveedor": "BUENOS_AIRES_CARS",
			"metodoPago": "TODOS",
			"parte": {
			    "id": 1,
			    "descripcionParte": "DELANTERA_DERECHA",
			    "tipoParte": "OPTICA",
			    "precioInicial": 6100.0
			},
			"monto": 6100.0
		    },
		    {
			"id": 2,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 3,
			    "descripcionParte": "PARAGOLPE_DELANTERO",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 7600.0
			},
			"monto": 6460.0
		    },
		    {
			"id": 3,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 2,
			    "descripcionParte": "PARRILLA_FRONTAL",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 5200.0
			},
			"monto": 4420.0
		    }
		]
	    }
	}
  ```
#### 4. Endpoint que permite obtener el historial de compras realizadas
###### Parámetros
-
###### RequestBody
-
###### Ejemplos
* Ejemplo del GET:
`http://127.0.0.1:8080/apiAdtomic/compras`

* Ejemplo de la respuesta (una compra realizada):
  ```
  	[
	    {
		"id": 1,
		"monto": 16980.0,
		"fechaCompra": "2020-05-03T04:20:58.672+0000",
		"itemsCompra": [
		    {
			"id": 1,
			"proveedor": "BUENOS_AIRES_CARS",
			"metodoPago": "TODOS",
			"parte": {
			    "id": 1,
			    "descripcionParte": "DELANTERA_DERECHA",
			    "tipoParte": "OPTICA",
			    "precioInicial": 6100.0
			},
			"monto": 6100.0
		    },
		    {
			"id": 2,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 3,
			    "descripcionParte": "PARAGOLPE_DELANTERO",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 7600.0
			},
			"monto": 6460.0
		    },
		    {
			"id": 3,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 2,
			    "descripcionParte": "PARRILLA_FRONTAL",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 5200.0
			},
			"monto": 4420.0
		    }
		]
	    },
	    {
		"id": 2,
		"monto": 16980.0,
		"fechaCompra": "2020-05-03T04:21:54.002+0000",
		"itemsCompra": [
		    {
			"id": 4,
			"proveedor": "BUENOS_AIRES_CARS",
			"metodoPago": "TODOS",
			"parte": {
			    "id": 1,
			    "descripcionParte": "DELANTERA_DERECHA",
			    "tipoParte": "OPTICA",
			    "precioInicial": 6100.0
			},
			"monto": 6100.0
		    },
		    {
			"id": 5,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 3,
			    "descripcionParte": "PARAGOLPE_DELANTERO",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 7600.0
			},
			"monto": 6460.0
		    },
		    {
			"id": 6,
			"proveedor": "AUTOS_AR",
			"metodoPago": "EFECTIVO",
			"parte": {
			    "id": 2,
			    "descripcionParte": "PARRILLA_FRONTAL",
			    "tipoParte": "CARROCERIA",
			    "precioInicial": 5200.0
			},
			"monto": 4420.0
		    }
		]
	    }
	]
  ```

_Nota: para correr las consultas a la API utilicé Postman (https://www.postman.com/downloads/)._

## Construida con 📌
* Spring Boot
* JPA/ Hibernate
* Java 8
* MySQL
* MAVEN

## Autor ✒️

_Lucia Maité Vázquez_

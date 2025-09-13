### Elegí GitFlow porque coincide con las ramas exigidas por el trabajo y ordena el flujo colaborativo:
- main: siempre estable.
- develop: integración de todo lo que se construye antes de pasar a main.
- feature: cada mejora se desarrolla aislada y entra a develop vía PR.
- hotfix: arreglos urgentes sobre main; tras el merge, se sincroniza main → develop.

### ¿Por qué me sirve aquí?
- Cumple exactamente con el requisito de ramas y PRs.
- Mantiene main estable y auditable.
- Facilita CI: correr pipeline en push a develop y PR hacia main.
- Aísla cambios, reduce conflictos y mejora la trazabilidad.

### Modelo de ramificación GitFlow

        +------------------+
        |                  |
        |      main        |
        |                  |
        +---------+--------+
                  |
          +-------+-------+
          |               |
          |    develop    |
          |               |
          +-------+-------+
                  |
      +-----------+-----------+
      |                       |
+-----+-----+           +-----+-----+
|           |           |           |
|  feature  |           |  hotfix   |
|           |           |           |
+-----------+           +-----------+
```                                 

## Ejecutar proyecto
Para ejecutar el proyecto, asegúrate de tener Java 17+ y Maven 3.9+ instalados. Luego, puedes usar el siguiente comando:
mvn spring-boot:run

## Commits ejecutados
- Commit inicial: Configuración básica del proyecto con Spring Boot y endpoint GET / que devuelve "Hola Mundo" y la hora actual.
- Agregado README.md con instrucciones de uso y requisitos.
- Agregado nuevo endpoint GET /fecha que devuelve la fecha actual en formato YYYY-MM-DD.
- Agregado nueva funcionalidad al endpoint GET / para agregar nombre personalizado.
- Se corrige error de seguridad que permite inyección de código en el endpoint GET /.
- Se agrega endpint GEt /timestamp que devuelve los segundos que han pasado desde 2000.
- Se agrega endpoint GET /sum que recibe dos parámetros y devuelve su suma.
- Se agrega archivo ci.yaml con las tareas a realizar con github actions.
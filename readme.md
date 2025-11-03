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

### Alternativa con Docker Compose
El archivo `docker-compose.yml` permite levantar el servicio y ejecutar los chequeos de salud expuestos en `/health`.

```bash
# Construye la imagen localmente y levanta el servicio en http://localhost:8080
docker compose up --build -d

# Smoke test manual
curl http://localhost:8080/health

# Detener los contenedores
docker compose down -v
```

Cuando el pipeline ejecuta el job **Deploy simulado**, utiliza la misma definición con la imagen publicada en GHCR.

## Trazabilidad CI/CD
Cada pipeline sigue la secuencia commit → build → scan → deploy. La tabla vincula los estados de cada etapa con el workflow `ci.yaml`:

| Commit | Build | Scan | Deploy |
| --- | --- | --- | --- |
| [Historial de commits](../../commits/main) | [![Build & Test](../../actions/workflows/ci.yaml/badge.svg?job=build-test-coverage)](../../actions/workflows/ci.yaml) | [![Snyk Scan](../../actions/workflows/ci.yaml/badge.svg?job=snyk-scan)](../../actions/workflows/ci.yaml)<br>[![Container Scan](../../actions/workflows/ci.yaml/badge.svg?job=container-scan)](../../actions/workflows/ci.yaml) | [![Deploy simulado](../../actions/workflows/ci.yaml/badge.svg?job=deploy-simulado)](../../actions/workflows/ci.yaml) |

En cada despliegue el job publica en el resumen de ejecución el commit exacto, el tag de la imagen enviada a GHCR y el entorno objetivo (`simulacion`).

## Commits ejecutados
- Commit inicial: Configuración básica del proyecto con Spring Boot y endpoint GET / que devuelve "Hola Mundo" y la hora actual.
- Agregado README.md con instrucciones de uso y requisitos.
- Agregado nuevo endpoint GET /fecha que devuelve la fecha actual en formato YYYY-MM-DD.
- Agregado nueva funcionalidad al endpoint GET / para agregar nombre personalizado.
- Se corrige error de seguridad que permite inyección de código en el endpoint GET /.
- Se agrega endpint GEt /timestamp que devuelve los segundos que han pasado desde 2000.
- Se agrega endpoint GET /sum que recibe dos parámetros y devuelve su suma.
- Se agrega archivo ci.yaml con las tareas a realizar con github actions.
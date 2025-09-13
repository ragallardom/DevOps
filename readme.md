Elegí GitFlow porque coincide con las ramas exigidas por el trabajo y ordena el flujo colaborativo:

main: siempre estable.

develop: integración de todo lo que se construye antes de pasar a main.

feature: cada mejora se desarrolla aislada y entra a develop vía PR.

hotfix: arreglos urgentes sobre main; tras el merge, se sincroniza main → develop.


#¿Por qué me sirve aquí?

Cumple exactamente con el requisito de ramas y PRs.

Mantiene main estable y auditable.

Facilita CI: correr pipeline en push a develop y PR hacia main.

Aísla cambios, reduce conflictos y mejora la trazabilidad.
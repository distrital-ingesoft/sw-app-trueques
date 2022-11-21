#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Zapato", "descripcion": "zapato negro", "enlaceImagen":"test", "precio": 150000, "disponible": true, "estadoElemento":"Nuevo", "usuario_id": 1, "categoria_id":1}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Balon", "descripcion": "Balon mundial", "enlaceImagen":"test2", "precio": 4000, "disponible": true, "estadoElemento":"Usado", "usuario_id": 1, "categoria_id":1}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/      
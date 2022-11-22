#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"Admin", "descripcion": "Hace todo", "activo": true, "permisos":[{"id":1 },{"id":2 }]}' \
    http://127.0.0.1:8080/nuevoRol/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"trocador", "descripcion": "Hace trueques", "activo": true, "permisos":[{"id":1 },{"id":2 }]}' \
    http://127.0.0.1:8080/nuevoRol/      
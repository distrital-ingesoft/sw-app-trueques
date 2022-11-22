#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"admin", "descripcion": "Usuario admin","rol_id":1}' \
    http://127.0.0.1:8080/nuevoPermiso/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Trocador", "descripcion": "Usuario trocador", "rol_id":1}' \
    http://127.0.0.1:8080/nuevoPermiso/      
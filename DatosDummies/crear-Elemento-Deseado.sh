#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Balon", "elementos_trueque_id": 1}' \
    http://127.0.0.1:8080/nuevoElementoDeseado/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Ropa", "elementos_trueque_id": 1}' \
    http://127.0.0.1:8080/nuevoElementoDeseado/      
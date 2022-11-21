#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"Deportes"}' \
    http://127.0.0.1:8080/nuevaCategoria/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"Ropa"}' \
    http://127.0.0.1:8080/nuevaCategoria/      
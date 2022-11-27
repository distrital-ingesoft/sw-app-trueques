#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{     "nombre":"Zapatos", "descripcion": "Zapatos negro", "enlaceImagen":"test", "precio": 2500, "disponible": true, "estadoElemento":"usado", "usuario":{ "id":1 }, "categoria":{ "id":2 }, "elementosDeseados":[ { "nombre":"Casa" }, { "nombre":"Tenis" }, { "nombre":"Balon" } ]}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{     "nombre":"Celular", "descripcion": "Iphone", "enlaceImagen":"test", "precio": 15900, "disponible": true, "estadoElemento":"Nuevo", "usuario":{ "id":2 }, "categoria":{ "id":3 }, "elementosDeseados":[ { "nombre":"Portatil" }, { "nombre":"Reloj" }, { "nombre":"Televisor" } ]}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/      
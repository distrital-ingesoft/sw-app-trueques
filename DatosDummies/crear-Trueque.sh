#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "estado": "finalizado", "fechaInicio":"21/11/2022", "fechaFinal": "21/11/2022", "precioLogistica": 5000, "solicitante":{ "id":1 }, "elementoDeseado":{ "id":6 } , "elementoTrueque":{ "id":1 } }' \
    http://127.0.0.1:8080/nuevoTrueque/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "estado": "finalizado", "fechaInicio":"21/11/2022", "fechaFinal": "21/11/2022", "precioLogistica": 5000, "solicitante":{ "id":1 }, "elementoDeseado":{ "id":6 } , "elementoTrueque":{ "id":1 } }' \
    http://127.0.0.1:8080/nuevoTrueque/      
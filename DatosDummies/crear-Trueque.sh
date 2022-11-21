#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"estado": "Iniciado", "fechaInicio":"21/11/2022", "fechaFinal": "21/11/2022", "precioLogistica": 5000, "usuario_id":1, "elemento_deseado_id": 1, "elemento_trueque_id":1}' \
    http://127.0.0.1:8080/nuevoTrueque/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"estado": "Finalizado", "fechaInicio":"22/11/2022", "fechaFinal": "22/11/2022", "precioLogistica": 10000, "usuario_id":1, "elemento_deseado_id": 1, "elemento_trueque_id":1}' \
    http://127.0.0.1:8080/nuevoTrueque/      
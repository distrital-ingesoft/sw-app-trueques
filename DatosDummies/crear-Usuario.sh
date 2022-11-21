#!/bin/bash

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombreCompleto":"Nicolas Rodriguez Daza", "documentoIdentificacion": 666666666, "correo": "nrodrguez@correo.com", "celular": 3004188473, "estado": true}' \
    http://127.0.0.1:8080/nuevoUsuario/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombreCompleto":"Cristian Sanchez", "documentoIdentificacion": 777777, "correo": "crsanchez@correo.com", "celular": 1234567, "estado": true}' \
    http://127.0.0.1:8080/nuevoUsuario/      
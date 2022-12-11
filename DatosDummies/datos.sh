

## PERMISOS

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Admin", "descripcion": "Usuario admin"}' \
    http://127.0.0.1:8080/nuevoPermiso/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombre":"Trocador", "descripcion": "Usuario trocador"}' \
    http://127.0.0.1:8080/nuevoPermiso/      

## ROL
curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"trocador", "descripcion": "Hace trueques", "activo": true, "permisos":[{"id":2 }]}' \
    http://127.0.0.1:8080/nuevoRol/     


curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"Admin", "descripcion": "Hace todo", "activo": true, "permisos":[{"id":1 }]}' \
    http://127.0.0.1:8080/nuevoRol/   


## USUARIO


curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombreCompleto":"Nicolas Rodriguez Daza", "documentoIdentificacion": 1234536, "correo": "nicolasrd1808@gmail.com", "celular": 3004188473,  "direccion":"Suba"  ,"ciudad":"Bogota" , "contrasena":"daza1" }' \
    http://127.0.0.1:8080/nuevoUsuario/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombreCompleto":"Cristian Sanchez", "documentoIdentificacion": 777777, "correo": "nrodriguezd@correo.udistrital.edu.co", "celular": 1234567, "direccion":"Teusaquillo"  , "ciudad":"Bogota" ,"contrasena":"cristian1" }' \
    http://127.0.0.1:8080/nuevoUsuario/    

    curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "nombreCompleto":"Cristian David Sanchez Bolanos", "documentoIdentificacion": 1234567, "correo": "crdsanchezb@correo.udistrital.edu.co", "celular": 3004188473,  "direccion":"Teusaquillo"  ,"ciudad":"Bogota" , "contrasena":"cristian1" }' \
    http://127.0.0.1:8080/nuevoAdmin/   
  

## CATEGORIA

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

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{"nombre":"Tecnologia"}' \
    http://127.0.0.1:8080/nuevaCategoria/   


## ELEMENTO TRUEQUE - ELEMENTO DESEADO 

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{     "nombre":"Zapatos", "descripcion": "Zapatos negro", "enlaceImagen":"https://bosico.vteximg.com.br/arquivos/zapatos-para-hombre-en-diferentes-colores-y-estilos-bosi.jpg?v=638019808898300000", "precio": 2500, "disponible": true, "estadoElemento":"usado" ,"usuario":{ "id":1 }, "categoria":{ "id":2 }, "elementosDeseados":[ { "nombre":"Casa" }, { "nombre":"Tenis" }, { "nombre":"Balon" } ]}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/   

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{     "nombre":"Celular", "descripcion": "Iphone", "enlaceImagen":"https://tienda.claro.com.co/wcsstore/Claro/images/catalog/equipos/646x1000/70037180.jpg", "precio": 15900, "disponible": true, "estadoElemento":"Nuevo" , "usuario":{ "id":2 }, "categoria":{ "id":3 }, "elementosDeseados":[ { "nombre":"Portatil" }, { "nombre":"Reloj" }, { "nombre":"Televisor" } ]}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/ 

    curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{     "nombre":"vaca", "descripcion": "vaca", "enlaceImagen":"https://tienda.claro.com.co/wcsstore/Claro/images/catalog/equipos/646x1000/70037180.jpg", "precio": 15900, "disponible": true, "estadoElemento":"Nuevo" , "usuario":{ "id":2 }, "categoria":{ "id":3 }, "elementosDeseados":[ { "nombre":"Portatil" }, { "nombre":"Reloj" }, { "nombre":"Televisor" } ]}' \
    http://127.0.0.1:8080/nuevoElementoTrueque/ 

##  TRUEQUE

curl  \
    --header "Content-Type: application/json" \
    --request POST \
    --data '{ "solicitante":{ "id":2 }, "elementoDeseado":{ "id":3 } , "elementoTrueque":{ "id":1 } }' \
    http://127.0.0.1:8080/nuevoTrueque/   



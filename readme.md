Este programa se debe correr con java en jdk 17, se debe tener docker instalado y correr la imagen con docker-compose

Usuario
Método GET

http://localhost:8080/usuario

Retorna:

    {
        "id": 1,
        "nombre": "Gerente1",
        "correo": "gerente1@example.com",
        "contrasena": "contrasena1",
        "tipo_usuario": 1
    }

Método POST

http://localhost:8080/usuario

Argumentos:

        {
        "nombre": "GerentePrueba",
        "correo": "gerentePrueba@example.com",
        "contrasena": "contrasenaPrueba",
        "tipo_usuario": 1
        }

Retorna:
1/0

Método PUT

http://localhost:8080/usuario/{id}

        {
        "nombre": "GerentePrueba",
        "correo": "gerentePrueba@example.com",
        "contrasena": "contrasenaPrueba",
        "tipo_usuario": 1
        }

Retorna:
1/0

Método DELETE

http://localhost:8080/usuario/{id}

Retorna:
Fila eliminada

Tabla proyecto

Método GET
http://localhost:8080/usuario/{id}/proyecto

    {
        "id":1,
        "nombre": "Proyecto_X",
        "descripcion": "Descripción del Proyecto_X",
        "fechainicio": "2024-03-02",
        "gerente": 1
    }

Retorna:
Lista proyectos

Método POST
http://localhost:8080/usuario/{id}/proyecto

    {
        "nombre": "Proyecto_X",
        "descripcion": "Descripción del Proyecto_X",
        "fechainicio": "2024-03-02",
        "gerente": 1
    }

Retorna:
Proyecto creado

Método PUT
http://localhost:8080/usuario/{id}/proyecto/{id_proyecto}

    {
        "nombre": "Proyecto_X",
        "descripcion": "Descripción del Proyecto_X",
        "fechainicio": "2024-03-02",
        "gerente": 1
    }

Retorna:
Proyecto actualizado

Método DELETE
http://localhost:8080/usuario/{id}/proyecto/{id_proyecto}

Retorna:
Proyecto eliminado

Tabla HistoriasUsuario

Método GET
/usuario/{id_usuario}/proyecto/{id_proyecto}/historias

Retorna:
    {
        "id": 5,
        "detalles": "Detalles de la historia 5",
        "criteriosAceptacion": "Criterios de aceptación de la historia 5",
        "idHistoriaUsuario": 2,
        "estado": 2,
        "idProyecto": 1
    }

Método POST
/usuario/{id_usuario}/proyecto/{id_proyecto}/historias

    {
        "detalles": "Detalles de la historia 5",
        "criteriosAceptacion": "Criterios de aceptación de la historia 5",
        "idHistoriaUsuario": 2,
        "estado": 2,
        "idProyecto": 1
    }

Retorna:
Historia de usuario creada

Método PUT
/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historia_usuario}

    {
        "detalles": "Detalles de la historia 5",
        "criteriosAceptacion": "Criterios de aceptación de la historia 5",
        "idHistoriaUsuario": 2,
        "estado": 2,
        "idProyecto": 1
    }

Retorna:
Se ha modificado la historia de usuario

Método DELETE
/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historia_usuario}

Retorna:
Se ha elimiando la historia de usuario

Tabla Tarea

Método GET

/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas

Retorna:
    {
        "id": 1,
        "descripcion": "Criterios de aceptación de la historia 5",
        "usuario_tarea_id": 1,
        "estado_tarea_id": 1,
        "historia_usuario_id": 1
    }

Método POST

/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas

    {
        "descripcion": "Criterios de aceptación de la historia 5",
        "usuario_tarea_id": 1,
        "estado_tarea_id": 1,
        "historia_usuario_id": 1
    }

Retorna:
Tarea creada

Método PUT

/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas/{id_tarea}

    {
        "descripcion": "Criterios de aceptación de la historia 5",
        "usuario_tarea_id": 1,
        "estado_tarea_id": 1,
        "historia_usuario_id": 1
    }

Retorna:
Se ha modificado la tarea

Método DELETE

/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas/{id_tarea}

Retorna:
Se ha elimiando la tarea de usuario

![Base de datos - Arquitectura Software C1](https://github.com/valsalazar28/ArquitecturaSoftwareCorte1/assets/141972527/2f2e92e2-e463-4ed3-9c6a-2f2a60298224)



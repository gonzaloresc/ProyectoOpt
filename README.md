# ProyectoOpt - Tienda Online  
## Diseño  
Nombre Tienda: Gonzalo Componentes  
Temática: Componentes Hardware  
Descripción:  
  
## Colaboradores  
**Gonzalo Rescalvo:** Rama Principal: 'https://github.com/gonzaloresc/ProyectoOpt/tree/rescalvo'  
  
**Gonzalo Marín:** Rama Principal: 'https://github.com/gonzaloresc/ProyectoOpt/tree/marin'  
  
## Clases Genericas  

| **Clase**              | **Atributos Minimos**                                                                                                                                |
|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| SeccionHardware        | id, nombre, descripcion                                                                                                                              |
| Componente             | id, sku (código único), nombre, descripcion, precio, stock, imagenUrl, activo, destacado, fechaAlta, categoria,<br> **marca**, **garantiaMeses**     |
| Usuario                | id, uid //TODO (identificador de Firebase o Supabase; con Spring Security, la contraseña cifrada), <br>email, nombre, direccionEnvio, rol, fechaAlta |
| Carrito y LineaCarrito | usuario, lineas / componente, cantidad                                                                                                               |
| Pedido                 | id, codigo (por ejemplo PED-2026-0001), usuario, fecha, estado, direccionEnvio, subtotal, gastosEnvio, total, lineas                                 |
| LineaPedido            | componente, nombreComponente, precioUnitario, cantidad, subtotal                                                                                     |
| Rol y EstadoPedido     | enumerados: CLIENTE, ADMIN / PENDIENTE, PAGADO, ENVIADO, ENTREGADO, CANCELADO                                                                        |


# Guía de autenticación con Firebase desde Postman
## Usuarios de la api:
**Usuario con permiso de administrador:**
- email: admin@gcomp.com | contraseña: admin1234

**Usuario normal:**
- email: cliente@gcomp.com | contraseña: cliente1234

## 1. Acceso a endpoints de administración
Antes de acceder a los endpoints protegidos de administración `(/api/admin/**)`, se necesita un ID Token válido de Firebase.
El ID Token tiene una duración aproximada de una hora y puede reutilizarse mientras no caduque.

## 2. Obtener un ID Token
### Paso 1. Crear una petición en Postman
Necesitas crear una petición **POST** con esta url:
```text
https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=TU_API_KEY
```

Sustituir **TU_API_KEY** por la Web API Key de Firebase.\
Para esto, entra en:
```text
configuración del proyecto -> general -> tus apps
```
[Enlace a Proyecto Firebase](https://console.firebase.google.com/u/1/project/gonzalo-componentes/settings/general/web:MGRkYzM2MGItYTkwNS00Njc4LWIyNDgtODFmNzk3ZTI5YTE5) 
### Paso 2. Configurar Headers y Body
Seleccionar **raw** y formato **JSON**.
Introduce:

```json
{
  "email": "admin@tienda.com",
  "password": "MI_CONTRASEÑA",
  "returnSecureToken": true
}
```

Utiliza el correo y la contraseña del usuario registrado en Firebase.

### Paso 3. Enviar la petición

Pulsar **Enviar** y si la autenticación es correcta, Firebase devuelve una respuesta JSON que contiene:

- `idToken`: token que utilizaré para acceder a mi API.
- `refreshToken`: token que permite renovar el ID Token.
- `expiresIn`: duración del ID Token en segundos.

Copiar el valor de `idToken`.

## 3. Acceder a los endpoints de administración

Crear una petición a mi backend.

Ejemplo:

```text
GET http://localhost:8080/api/admin/productos
```
En Postman:
1. Abrir la pestaña Authorization.
2. Seleccionar **Bearer Token**.
3. Pegar el valor de **idToken**.
4. Pulsar Send.

Esto aplica para cualquier consulta de administración mientras mantengas el token
## 4. Posibles Resultados
| Código HTTP | Significado |
|---|---|
| 200 OK | Petición realizada correctamente. |
| 401 Unauthorized | Token ausente, inválido o caducado. |
| 403 Forbidden | Usuario autenticado, pero sin permisos de administrador. |
| 404 Not Found | Endpoint inexistente. |

## Anotaciones
- El correo del administrador debe estar incluido en `tienda.admins` en `application.properties`.


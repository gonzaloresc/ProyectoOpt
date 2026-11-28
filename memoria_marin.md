# Memoria Gonzalo Marin
En este fichero explico todas las decisiones de diseño del proyecto.

## Creación de Dtos
### StockDto
Es usado para mostrar el stock en el controller, para identificar cada componente le he añadido el nombre y el sku aparte del stock.
### SeccionHardwareDto
Mismos atributos que su modelo, no tiene ningún dato que deba ser ocultado
### SeccionHardwareCrearDto
Ocultamos el id ya que el cliente no debe asignarlo
### ComponenteCrearDto
Ocultamos id, fechaAlta y activo porque será asignado en el servidor. Estudiar si el campo "destacado" debería ser también asignado por el servidor, por lo pronto está en el dto.
### ComponenteDetalleDto
Devolvemos todos los atributos interesantes para el cliente. Cambiamos SeccionHardware por SeccionHardwareDto. Mantenemos fechaAlta, estudiar si se debería devolver como String o LocalDate para el Dto, por lo pronto se mantiene como LocalDate.
### ComponenteResumenDto
Añadir solo los atributos más importantes para mostrarselos al cliente. También he decidido no mostrar detalles más tecnicos como el sku.\
id: para poder buscar un producto por id en los controller
nombre: nombre del producto\
precio: precio del producto\
imagenUrl: url a la imagen\
nombreSeccionHardware: nombre de la categoría del hardware. Considero que para un resumen simplemente su nombre está bien. Estudiar si usar SeccionHardware o su id.
---
## CategoriaService
### modificarSeccionHardware
Para modificar un libro necesitas obtener el id del libro y el cuerpo del libro que quieres cambiar.
Si por ejemplo solo necesitas modificar el nombre de una seccion, envías en un json "nombre": "Nombre que quieras" y la descripción sería null.
---
## Controladores
### CategoriaController
Recibe los endpoints orientados al público

### CategoriaAdminController
Recibe los endpoints orientados a los administradores.\
Esta distinción es para dividir las clases por rol, mayor legibilidad y que los controladores tengan responsabilidades únicas. Posteriormente cuando tengamos que asignar seguridad esta separación será útil.
### Resolver Dependencia Circular
ComponenteService depende de SeccionHardwareService. Creamos una interfaz que solo obtendrá una copia de la lista en memoria para comprobar si se puede eliminar dicha categoría.\
Para implementar esto, necesitamos la clase ObjectProvider, que permite obtener el bean solo cuando lo necesitas, evitando así la dependencia.

## Seguridad
Toda esta seccion se centrará en entender cual es el flujo del programa a nivel de seguridad.\
1. Cliente / Postman con autorización: Envia la request con el token recibido por firebase en la cabecera "authorization"
2. Filtro Autenticacion: Comprueba que existe la cabecera Authorization y empieza por Bearer y si es así extrae el token para verificarlo después
3. Firebase Admin SDK: Comprueba que el token es válido y no ha caducado
4. Con el token verificado se crea o obtiene el usuario y se le asigna su rol correspondiente según su correo y se comprueba si tiene los permisos necesarios para la petición que está usando
5. Controller: Si tiene los permisos necesarios, el controlador funciona con normalidad

## Firebase Authentication
### Firebase Console
Creamos el proyecto **Gonzalo-Componentes** desde Firebase Console.\
En Security -> Authentication:\
Sing in method: email and password\ 
users:  admin@gcomp.com,  cliente@gcomp.com\
En Settings -> General -> Service Accounts:
Java -> Generate private key
No guardamos el json generado en el repo público.\

### Spring Boot Config
Inyectamos dependencias en el pom.xml y sincronizamos el proyecto maven. (spring-boot-starter-security y firebase-admin)\

### FiltroAutenticaciónFirebase
Hereda de OncePerRequestFilter que es una clase de Spring que nos permite ejecutar nuestro filtro una vez por petición HTTP.
Este la clase usa el metodo doFilterInternal donde se encuentra toda la logica de autenticacion para cada peticion.
El metodo recibe tanto el contenido de la request como de la response y un objeto FilterChain para poder continuar el flujo del programa hacia otro filtro o directamente al controller si no hay más filtros.
Esta clase se ejecuta antes porque hereda de OncePerRequestFilter y SpringBoot le da prioridad a los filtros.
Usamos UsernamePasswordAuthenticationToken porque es el estandar usado en SpringBoot para autenticación y poder usarlo después en UsuarioActualService. Recibe el usuario, credenciales (null en este caso porque ya está verificado) y la lista de roles, o cliente o admin.
SecurityContextHolder es donde Spring Security guarda quién está autenticado y lo usaremos después en UsuarioActualService.

### UsuarioActualService
Guardamos los correos válidos en un Set<String para que no haya repetidos.\

### SecurityConfig
Modificamos el objeto HttpSecurity\
En este archivo nos aseguramos que rutas están protegidas para el filtro (**/api/admin**)
Creamos registroFiltroFirebase para que no haya conflicto con @Component FiltroAutenticacionFirebase



### Resolver Dependencia Circular
ComponenteService depende de SeccionHardwareService. Creamos una interfaz que solo obtendrá una copia de la lista en memoria para comprobar si se puede eliminar dicha categoría.\
Para implementar esto, necesitamos la clase ObjectProvider, que permite obtener el bean solo cuando lo necesitas, evitando así la dependencia.
## Preguntas A Matías
- RequestMapping(/api/categoria) || (/api) porque hay endpoints a /api/admin/categoria
- Consultar la separación de los controllers

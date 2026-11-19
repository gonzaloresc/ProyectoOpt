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

## Preguntas A Matías
- RequestMapping(/api/categoria) || (/api) porque hay endpoints a /api/admin/categoria
- Consultar la separación de los controllers
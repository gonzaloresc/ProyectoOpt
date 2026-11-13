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



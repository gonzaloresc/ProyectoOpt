package com.proyecto.tienda_online.model;

public enum EstadoPedido {

    PENDIENTE, PAGADO, ENVIADO, ENTREGADO, CANCELADO;


    // Este apartado lo he hecho por los apuntes del paso 8 (Carrito y pedidos)

    public boolean puedeCambiarA(EstadoPedido nuevo) {
        return switch (this) {
            case PENDIENTE -> nuevo == PAGADO || nuevo == CANCELADO;
            case PAGADO -> nuevo == ENVIADO || nuevo == CANCELADO;
            case ENVIADO -> nuevo == ENTREGADO;
            case ENTREGADO, CANCELADO -> false;
        };
    }

}

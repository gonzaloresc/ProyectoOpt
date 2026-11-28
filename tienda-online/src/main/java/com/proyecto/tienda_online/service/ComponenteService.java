package com.proyecto.tienda_online.service;

import com.proyecto.tienda_online.dto.*;
import com.proyecto.tienda_online.exception.ComponenteInvalidoException;
import com.proyecto.tienda_online.exception.ComponenteNoEncontradoException;
import com.proyecto.tienda_online.exception.SeccionHardwareNoEncontradaException;
import com.proyecto.tienda_online.model.Componente;
import com.proyecto.tienda_online.model.Direccion;
import com.proyecto.tienda_online.model.Orden;
import com.proyecto.tienda_online.model.SeccionHardware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ComponenteService implements ObtenerComponentes{

    private SeccionHardwareService seccionHardwareService;
    private ArrayList<SeccionHardware> secciones;
    private long siguienteId = 0;
    private final ArrayList<Componente> componentes;

    // Constructor
    public ComponenteService(SeccionHardwareService seccionHardwareService){
        this.seccionHardwareService = seccionHardwareService;
        this.secciones = seccionHardwareService.seccionesHardware;
        this.componentes = new ArrayList<>(List.of(
                // CPU
                new Componente(++siguienteId,"CPU-AMD-7800X3D","AMD Ryzen 7 7800X3D","Procesador 8 nucleos 4.2GHz Socket AM5", new BigDecimal("389.90"),15,"",true,true, LocalDate.now(),secciones.get(0),"AMD",36),
                new Componente(++siguienteId,"CPU-INT-14700K","Intel Core i7-14700K","Procesador 20 nucleos hasta 5.6GHz LGA1700", new BigDecimal("419.50"),8,"",true,false, LocalDate.now(), secciones.get(0), "Intel", 36),
                new Componente(++siguienteId, "CPU-AMD-5600", "AMD Ryzen 5 5600", "Procesador 6 nucleos 3.5GHz Socket AM4", new BigDecimal("124.99"), 22, "", true, false, LocalDate.now(), secciones.get(0), "AMD", 36),
                new Componente(++siguienteId, "CPU-INT-12400F", "Intel Core i5-12400F", "Procesador 6 núcleos sin gráficos integrados", new BigDecimal("115.00"), 0, "", true, false, LocalDate.now(), secciones.get(0), "Intel", 36),
                // Graficas
                new Componente(++siguienteId, "GPU-NV-4070S", "ASUS Dual GeForce RTX 4070 SUPER 12GB", "Tarjeta gráfica GDDR6X DLSS 3", new BigDecimal("649.00"), 5, "", true, true, LocalDate.now(), secciones.get(1), "ASUS", 36),
                new Componente(++siguienteId, "GPU-AMD-7800XT", "Sapphire PULSE Radeon RX 7800 XT 16GB", "Tarjeta gráfica GDDR6 AMD RDNA 3", new BigDecimal("519.90"), 9, "", true, true, LocalDate.now(), secciones.get(1), "Sapphire", 36),
                new Componente(++siguienteId, "GPU-NV-4060", "MSI GeForce RTX 4060 VENTUS 2X 8GB", "Tarjeta gráfica compacta doble ventilador", new BigDecimal("309.95"), 14, "", true, false, LocalDate.now(), secciones.get(1), "MSI", 36),
                new Componente(++siguienteId, "GPU-NV-3060-RET", "Gigabyte GeForce RTX 3060 Windforce 12GB", "Modelo descatalogado", new BigDecimal("285.00"), 0, "", false, false, LocalDate.now(), secciones.get(1), "Gigabyte", 24),
                // Memoria
                new Componente(++siguienteId, "RAM-COR-32DDR5", "Corsair Vengeance DDR5 6000MHz 32GB", "Memoria RAM CL30 optimizada para AMD/Intel", new BigDecimal("119.99"), 30, "", true, true, LocalDate.now(), secciones.get(2), "Corsair", 60),
                new Componente(++siguienteId, "RAM-KIN-16DDR4", "Kingston FURY Beast DDR4 3200MHz 16GB", "Memoria RAM perfil bajo CL16", new BigDecimal("42.50"), 40, "", true, false, LocalDate.now(), secciones.get(2), "Kingston", 60),
                new Componente(++siguienteId, "RAM-GSK-32DDR5", "G.Skill Trident Z5 RGB DDR5 6400MHz 32GB", "Memoria RAM con iluminación RGB", new BigDecimal("139.00"), 0, "", true, false, LocalDate.now(), secciones.get(2), "G.Skill", 60),
                new Componente(++siguienteId, "RAM-CRU-8DDR4", "Crucial Basics DDR4 2666MHz 8GB", "Módulo antiguo retirado del catálogo", new BigDecimal("19.90"), 3, "", false, false, LocalDate.now(), secciones.get(2), "Crucial", 24)

        ));
    }

    // Metodos para todos

    // Listar todos los componentes pero activos
    public ArrayList<ComponenteResumenDto> listarComponentesActivos(){
        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();

        for(Componente c : componentes){

            if(c.getActivo()){
                resultado.add(parsearAResumenDto(c));
            }
        }
        return resultado;
    }

    // Apartado 5
    // Filtros
    public ArrayList<ComponenteResumenDto> listarComponentesFiltrados(ComponenteFiltroDto filtro) {

        // El precio minimo no puede ser mayor que el precio maximo
        if(filtro.getPrecioMin() != null &&
           filtro.getPrecioMax() != null &&
           filtro.getPrecioMin().compareTo(filtro.getPrecioMax()) > 0 // Parte de investigación
        ){
            throw new ComponenteInvalidoException("El precio minimo no puede ser mayor que el maximo");
        }

        // Busqueda
        String busqueda = null;
        if(filtro.getQ() != null && !filtro.getQ().isBlank()){
            busqueda = filtro.getQ().toLowerCase().replace('á','a').replace('é','e').replace('í','i').replace('ó','o').replace('ú','u');
        }

        ArrayList<Componente> filtrados = new ArrayList<>();

        for (Componente c : componentes){

            // Variable bandera
            boolean seguir = true;

            // Si no está activo sale
            if (!c.getActivo()){seguir=false;}

            // Si su categoria es null o no coincide con el filtro, sale
            if (seguir && filtro.getCategoriaId() != null) {
                if (c.getCategoria() == null || !c.getCategoria().getId().equals(filtro.getCategoriaId())){
                    seguir = false;
                }
            }

            // Si el nombre y la descripción no son el nombre y la descripción de "busqueda", sale
            if (seguir && busqueda != null) {
                String nombreN = c.getNombre().toLowerCase().replace('á','a').replace('é','e').replace('í','i').replace('ó','o').replace('ú','u');
                String descN = c.getDescripcion().toLowerCase().replace('á','a').replace('é','e').replace('í','i').replace('ó','o').replace('ú','u');
                if (!nombreN.contains(busqueda) && !descN.contains(busqueda)){
                    seguir = false;
                }
            }

            // Filtro de precio minimo
            if (seguir && filtro.getPrecioMin() != null && c.getPrecio().compareTo(filtro.getPrecioMin()) < 0){
                seguir = false;
            }

            // Filtro por precio max
            if (seguir && filtro.getPrecioMax() != null && c.getPrecio().compareTo(filtro.getPrecioMax()) > 0){
                seguir = false;
            }

            // Disponible
            if (seguir && filtro.getSoloDisponibles() != null && filtro.getSoloDisponibles() &&  c.getStock() <= 0){
                seguir = false;
            }

            // Marca
            if (seguir && filtro.getMarca() != null && !filtro.getMarca().isBlank()) {
                if (c.getMarca() == null || !c.getMarca().equalsIgnoreCase(filtro.getMarca().trim())) {
                    seguir = false;
                }
            }

            // Si todo está bien, continua
            if (seguir) {
                filtrados.add(c);
            }

        }

        // Ordenar
        if (filtro.getOrden() != null ) {
            for (int i = 0; i < filtrados.size() - 1; i++){
                // Aquí quiero comparar los elementos con el siguiente
                for (int j = i + 1; j < filtrados.size(); j++){
                    Componente a = filtrados.get(i);
                    Componente b = filtrados.get(j);

                    // dará "<0" si a va primero, dará "0" si van iguales, y ">0" despues
                    int comparacion = 0;

                    switch (filtro.getOrden()) {
                        case NOMBRE:
                            comparacion = a.getNombre().compareToIgnoreCase(b.getNombre());
                            break;
                        case PRECIO:
                            comparacion = a.getPrecio().compareTo(b.getPrecio());
                            break;
                        case NOVEDAD:
                            comparacion = a.getFechaAlta().compareTo(b.getFechaAlta());
                            break;
                    }

                    if (filtro.getDireccion() == Direccion.DESC) {
                        // Al estar ordenado del reves se pone negativo
                        comparacion = -comparacion;
                    }

                    if(comparacion > 0){
                        filtrados.set(i,b);
                        filtrados.set(j,a);
                    }

                }
            }
        }

        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();
        for (Componente c : filtrados){
            resultado.add(parsearAResumenDto(c));
        }
        return resultado;

    }


    // Listar los componentes destacados
    public ArrayList<ComponenteResumenDto> listarComponentesDestacados(){
        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();

        for(Componente c : componentes){

            if(c.getActivo() && c.getDestacado()){
                resultado.add(parsearAResumenDto(c));
            }
        }
        return resultado;
    }

    // Obtener componentes por id pero activos
    // Uso el metodo que tengo para buscar pero aquí le añado el activo
    public ComponenteDetalleDto obtenerComponenteActivoPorId(Long id) {
        Componente componente = obtenerComponenteOriginalPorId(id);
        if (!componente.getActivo()){
            throw new ComponenteNoEncontradoException("Componente con id " +id+ " no encontrado");
        }
        return parsearADetalleDto(componente);
    }

    // Apartado 5
    // Hacer sku
    public ComponenteDetalleDto obtenerComponenteActivoPorSku(String sku) {
        for (Componente c : componentes) {
            if (c.getSku().equalsIgnoreCase(sku.trim()) && c.getActivo()){
                return parsearADetalleDto(c);
            }
        }
        throw new ComponenteNoEncontradoException("Componente con SKU: "+sku+" no encontrado");
    }

    // Apartado 5
    // Listar por seccion
    public ArrayList<ComponenteResumenDto> listarComponentesPorSeccion(Long seccionId) {

        // Comprobar si la seccion existe
        boolean existeSec = false;
        for (SeccionHardware s : secciones) {
            if (s.getId().equals(seccionId)) {
                existeSec = true;
                break;
            }
        }
        if (!existeSec){throw new SeccionHardwareNoEncontradaException("La seccion con id: " +seccionId+ " no ha sido encontrada");}

        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();
        for (Componente c : componentes) {
            if (c.getActivo() && c.getCategoria() != null && c.getCategoria().getId().equals(seccionId)) {
                resultado.add(parsearAResumenDto(c));
            }
        }
        return resultado;

    }


    // Metodos para admin

    // Listar todos los componentes
    public ArrayList<ComponenteDetalleDto> listarTodosComponentesAdmin() {
        ArrayList<ComponenteDetalleDto> resultado = new ArrayList<>();

        for (Componente c : componentes) {
            resultado.add(parsearADetalleDto(c));
        }
        return resultado;
    }

    // Apartado 5
    // Listar por stock
    public ArrayList<ComponenteDetalleDto> listarStockBajo(Integer umbral) {
        if (umbral == null || umbral < 0){
            throw new ComponenteInvalidoException("El umbral debe ser mayor que 0");
        }

        ArrayList<ComponenteDetalleDto> resultado = new ArrayList<>();
        for (Componente c : componentes) {
            if (c.getStock() <= umbral) {
                resultado.add(parsearADetalleDto(c));
            }
        }
        return resultado;
    }

    // Crear
    public ComponenteDetalleDto crearComponente(ComponenteCrearDto dto) {
        // Comprobaciones
        comprobarDatos(dto);

        String skuMayusculas = dto.getSku().trim().toUpperCase();
        for (Componente c : componentes) {
            if (c.getSku().equalsIgnoreCase(skuMayusculas)){
                throw new ComponenteInvalidoException("Ya hay un componente con ese SKU");
            }
        }

        SeccionHardware seccion = buscarSecId(dto.getSeccionHardwareId()); // Hay que saber si existe la categoría

        Componente nuevo = new Componente(
                ++siguienteId,
                skuMayusculas,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getImagenUrl(),
                true,
                dto.getDestacado(),
                LocalDate.now(),
                seccion,
                dto.getMarca(),
                dto.getGarantiaMeses()
        );

        componentes.add(nuevo);
        return parsearADetalleDto(nuevo);

    }

    // Modificar
    public ComponenteDetalleDto modificarComponente(Long id, ComponenteCrearDto dto){

        // Voy a seleccionar el componente que se modifica
        Componente com = obtenerComponenteOriginalPorId(id);

        // Ahora compruebo datos como cuando se crea un componente
        comprobarDatos(dto);

        // Hago la misma comprobación de sku que antes pero ahora tambien pongo que la id sea distinta a la seleccionada
        String skuMayusculas = dto.getSku().trim().toUpperCase();
        for (Componente c : componentes) {
            if (c.getSku().equalsIgnoreCase(skuMayusculas) && !c.getId().equals(id)){
                throw new ComponenteInvalidoException("Ya hay un componente con ese SKU");
            }
        }

        // Busco seccion
        SeccionHardware seccion = buscarSecId(dto.getSeccionHardwareId());

        // Modifico el componente
        com.setSku(skuMayusculas);
        com.setNombre(dto.getNombre());
        com.setDescripcion(dto.getDescripcion());
        com.setPrecio(dto.getPrecio());
        com.setStock(dto.getStock());
        com.setImagenUrl(dto.getImagenUrl());
        com.setDestacado(dto.getDestacado());
        com.setCategoria(seccion);
        com.setMarca(dto.getMarca());
        com.setGarantiaMeses(dto.getGarantiaMeses());

        return parsearADetalleDto(com);

    }

    // Retirar componente
    public void retirarComponente(Long id) {
        Componente componente = obtenerComponenteOriginalPorId(id);
        componente.setActivo(false);
    }

    // Reactivar componente
    public ComponenteDetalleDto reactivarComponente(Long id){
        Componente componente = obtenerComponenteOriginalPorId(id);
        componente.setActivo(true);
        return parsearADetalleDto(componente);
    }

    // Sumar o restar stock
    public StockDto modificarStock(Long id, StockDto stockDto) {
        Componente componente = obtenerComponenteOriginalPorId(id);

        if(stockDto == null || stockDto.getStock() == null){throw new ComponenteInvalidoException("Tienes que meter una cantidad");}

        int stockResultante = componente.getStock() + stockDto.getStock();
        if (stockResultante < 0) {throw new ComponenteInvalidoException("El stock no puede ser menor que 0");}

        componente.setStock(stockResultante);
        return new StockDto(componente.getSku(), componente.getNombre(), componente.getStock());

    }


    // Herramientas

    // Convertir a Resumen
    public ComponenteResumenDto parsearAResumenDto(Componente componente){
        return new ComponenteResumenDto(
                componente.getId(),
                componente.getNombre(),
                componente.getPrecio(),
                componente.getImagenUrl(),
                componente.getCategoria().getNombre()
        );
    }

    // Convertir a Detalle
    public ComponenteDetalleDto parsearADetalleDto(Componente componente){

        SeccionHardwareDto sDto = new SeccionHardwareDto(
                componente.getCategoria().getId(),
                componente.getCategoria().getNombre(),
                componente.getCategoria().getDescripcion()
        );

        return new ComponenteDetalleDto(
                componente.getId(),
                componente.getSku(),
                componente.getNombre(),
                componente.getDescripcion(),
                componente.getPrecio(),
                componente.getStock(),
                componente.getImagenUrl(),
                componente.getDestacado(),
                componente.getFechaAlta(),
                sDto,
                componente.getMarca(),
                componente.getGarantiaMeses()
        );
    }

    // Metodo para coger un componente según su id
    public Componente obtenerComponenteOriginalPorId(Long id) {
        for (Componente c : componentes) {
            if(c.getId().equals(id)) {
                return c;
            }
        }
        throw new ComponenteNoEncontradoException("Componente con id " +id+ " no encontrado");
    }

    // Metodo para buscar una de las secciones creadas al inicio
    private SeccionHardware buscarSecId(Long seccionId) {
        for (SeccionHardware s : secciones) {
            if (s.getId().equals(seccionId)){
                return s;
            }
        }
        throw new ComponenteInvalidoException("La seccion con id: "+seccionId+" no existe");
    }

    // Comprobar datos:
    private void comprobarDatos(ComponenteCrearDto dto) {

        if (dto == null){throw new ComponenteInvalidoException("Los datos no pueden estar vacios");}
        if (dto.getSku()==null || dto.getSku().isBlank()){throw new ComponenteInvalidoException("Tienes que poner un SKU");}
        if (dto.getNombre()==null || dto.getNombre().isBlank()){throw new ComponenteInvalidoException("Tienes que poner un nombre");}
        // La siguiente es investigación
        if (dto.getPrecio()==null || dto.getPrecio().compareTo(BigDecimal.ZERO) <= 0){throw new ComponenteInvalidoException("El precio tiene que ser mas que 0");}
        if (dto.getStock()==null || dto.getStock() < 0){throw new ComponenteInvalidoException("El stock no puede ser menor que 0");}
        if (dto.getSeccionHardwareId()==null){throw new ComponenteInvalidoException("Tienes que poner un id de seccion");}
    }

    // Metodo para SeccionHardwareService y evitar dependencia circular
    @Override
    public ArrayList<Componente> obtenerComponentes(){
        return new ArrayList<>(componentes);
    }

}

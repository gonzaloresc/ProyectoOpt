package com.proyecto.tienda_online.service;

import com.proyecto.tienda_online.dto.SeccionHardwareCrearDto;
import com.proyecto.tienda_online.dto.SeccionHardwareDto;
import com.proyecto.tienda_online.exception.AtributosInvalidosException;
import com.proyecto.tienda_online.exception.SeccionHardwareNoEncontradaException;
import com.proyecto.tienda_online.exception.SeccionHardwareNoVaciaException;
import com.proyecto.tienda_online.exception.SeccionHardwareYaExistenteException;
import com.proyecto.tienda_online.model.Componente;
import com.proyecto.tienda_online.model.SeccionHardware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeccionHardwareService {
    private final ObjectProvider<ObtenerComponentes> obtenerComponentesProvider;

    public SeccionHardwareService(
            ObjectProvider<ObtenerComponentes> obtenerComponentesProvider) {
        this.obtenerComponentesProvider = obtenerComponentesProvider;
    }

    private long siguienteId = 0;
    protected final ArrayList<SeccionHardware> seccionesHardware = new ArrayList<>(List.of(
            new SeccionHardware(++siguienteId, "Procesador", "Unidad central de procesamiento que ejecuta las instrucciones del sistema y las aplicaciones."),
            new SeccionHardware(++siguienteId, "Tarjeta gráfica", "Componente encargado de procesar y mostrar los gráficos y el vídeo en pantalla."),
            new SeccionHardware(++siguienteId, "Memoria RAM", "Memoria de acceso rápido donde el sistema almacena temporalmente los datos en uso.")
    ));




    public ArrayList<SeccionHardwareDto> listarSeccionesHardware(){
        ArrayList<SeccionHardwareDto> listado = new ArrayList<>();
        for (SeccionHardware seccionHardware : seccionesHardware){
            listado.add(parsearADto(seccionHardware));
        }
        return listado;
    }

    public SeccionHardwareDto obtenerSeccionHardwarePorId(Long id){
        for (SeccionHardware seccionHardware : seccionesHardware){
            if (seccionHardware.getId().equals(id)){
                return parsearADto(seccionHardware);
            }
        }
        throw new SeccionHardwareNoEncontradaException("Sección Hardware con id " + id + " no encontrada");
    }

    public SeccionHardwareDto crearSeccionHardware(SeccionHardwareCrearDto seccionHardwareCrearDto){

        validar(seccionHardwareCrearDto);
        if (seccionHardwareCrearDto.getNombre() == null){
            throw new AtributosInvalidosException("El nombre de la sección no puede ser null");
        }
        if (seccionHardwareCrearDto.getDescripcion() == null){
            throw new AtributosInvalidosException("La descripción de la sección no puede ser null");
        }

        SeccionHardware seccionHardware = new SeccionHardware(
                ++siguienteId,
                seccionHardwareCrearDto.getNombre(),
                seccionHardwareCrearDto.getDescripcion()
        );
        seccionesHardware.add(seccionHardware);
        return parsearADto(seccionHardware);
    }

    public SeccionHardwareDto modificarSeccionHardware(Long id , SeccionHardwareCrearDto seccionHardwareCrearDto){
        validar(seccionHardwareCrearDto);
        SeccionHardware seccionHardwareAModificar =  obtenerSeccionHardwareOriginalPorId(id);
        if (seccionHardwareCrearDto.getNombre() != null){
            seccionHardwareAModificar.setNombre(seccionHardwareCrearDto.getNombre());
        }
        if (seccionHardwareCrearDto.getDescripcion() != null){
            seccionHardwareAModificar.setDescripcion(seccionHardwareCrearDto.getDescripcion());
        }
        return parsearADto(seccionHardwareAModificar);
    }

    public SeccionHardwareDto eliminarSeccionHardware(Long id){
        SeccionHardware seccionHardwareAEliminar = obtenerSeccionHardwareOriginalPorId(id);
        ArrayList<Componente>  componentes = obtenerComponentesProvider.getObject().obtenerComponentes();
        for (Componente componente : componentes){
            if (componente.getCategoria().getId().equals(seccionHardwareAEliminar.getId())){
                throw new SeccionHardwareNoVaciaException("Esta seccion hardware aún tiene componentes asignados");
            }
        }
        seccionesHardware.remove(seccionHardwareAEliminar);
        return parsearADto(seccionHardwareAEliminar);
    }

    private SeccionHardware obtenerSeccionHardwareOriginalPorId(Long id){
        for (SeccionHardware seccionHardware : seccionesHardware){
            if (seccionHardware.getId().equals(id)){
                return seccionHardware;
            }
        }
        throw new SeccionHardwareNoEncontradaException("Sección Hardware con id " + id + " no encontrada");
    }

    public void validar(SeccionHardwareCrearDto seccionHardware){
        for (SeccionHardware seccionHardwareLista : seccionesHardware){
            if (seccionHardwareLista.getNombre().equals(seccionHardware.getNombre())){
                throw new SeccionHardwareYaExistenteException("Ya existe una Sección Hardware con nombre " + seccionHardware.getNombre());
            }
        }
    }

    public SeccionHardwareDto parsearADto(SeccionHardware seccionHardware){
        SeccionHardwareDto seccionHardwareDto = new SeccionHardwareDto(
                seccionHardware.getId(),
                seccionHardware.getNombre(),
                seccionHardware.getDescripcion()
        );
        return seccionHardwareDto;
    }


}

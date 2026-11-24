package com.proyecto.tienda_online.service;

import com.proyecto.tienda_online.dto.ComponenteDetalleDto;
import com.proyecto.tienda_online.dto.ComponenteResumenDto;
import com.proyecto.tienda_online.dto.SeccionHardwareDto;
import com.proyecto.tienda_online.model.Componente;
import com.proyecto.tienda_online.model.SeccionHardware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ComponenteService {


    private final SeccionHardwareService seccionHardwareService;

    private long siguienteId = 0;
    private final ArrayList<Componente> componentes = new ArrayList<>();

    // Hago el constructor
    public ComponenteService(SeccionHardwareService seccionHardwareService) {

        // Aquí cojo la seccion de hardware
        this.seccionHardwareService = seccionHardwareService;

        // Aquí quiero coger las secciones por id, pero devuelven un dto así que los guardo como dto
        SeccionHardwareDto dtoProcesadores = seccionHardwareService.obtenerSeccionHardwarePorId(1L);
        SeccionHardwareDto dtoGraficas = seccionHardwareService.obtenerSeccionHardwarePorId(2L);
        SeccionHardwareDto dtoMemorias = seccionHardwareService.obtenerSeccionHardwarePorId(3L);

        // Y aquí uso ese dto de antes para crear unas nuevas secciones de harware con los datos que tenian los dto
        SeccionHardware procesadores = new SeccionHardware(dtoProcesadores.getId(),dtoProcesadores.getNombre(),dtoProcesadores.getDescripcion());
        SeccionHardware graficas = new SeccionHardware(dtoGraficas.getId(),dtoGraficas.getNombre(),dtoGraficas.getDescripcion());
        SeccionHardware memorias = new SeccionHardware(dtoMemorias.getId(),dtoMemorias.getNombre(),dtoMemorias.getDescripcion());

        componentes.addAll(List.of(
                // CPU
                new Componente(++siguienteId,"CPU-AMD-7800X3D","AMD Ryzen 7 7800X3D","Procesador 8 nucleos 4.2GHz Socket AM5", new BigDecimal("389.90"),15,"",true,true, LocalDate.now(),procesadores,"AMD",36),
                new Componente(++siguienteId,"CPU-INT-14700K","Intel Core i7-14700K","Procesador 20 nucleos hasta 5.6GHz LGA1700", new BigDecimal("419.50"),8,"",true,false, LocalDate.now(), procesadores, "Intel", 36),
                new Componente(++siguienteId, "CPU-AMD-5600", "AMD Ryzen 5 5600", "Procesador 6 nucleos 3.5GHz Socket AM4", new BigDecimal("124.99"), 22, "", true, false, LocalDate.now(), procesadores, "AMD", 36),
                new Componente(++siguienteId, "CPU-INT-12400F", "Intel Core i5-12400F", "Procesador 6 núcleos sin gráficos integrados", new BigDecimal("115.00"), 0, "", true, false, LocalDate.now(), procesadores, "Intel", 36),
                // Graficas
                new Componente(++siguienteId, "GPU-NV-4070S", "ASUS Dual GeForce RTX 4070 SUPER 12GB", "Tarjeta gráfica GDDR6X DLSS 3", new BigDecimal("649.00"), 5, "", true, true, LocalDate.now(), graficas, "ASUS", 36),
                new Componente(++siguienteId, "GPU-AMD-7800XT", "Sapphire PULSE Radeon RX 7800 XT 16GB", "Tarjeta gráfica GDDR6 AMD RDNA 3", new BigDecimal("519.90"), 9, "", true, true, LocalDate.now(), graficas, "Sapphire", 36),
                new Componente(++siguienteId, "GPU-NV-4060", "MSI GeForce RTX 4060 VENTUS 2X 8GB", "Tarjeta gráfica compacta doble ventilador", new BigDecimal("309.95"), 14, "", true, false, LocalDate.now(), graficas, "MSI", 36),
                new Componente(++siguienteId, "GPU-NV-3060-RET", "Gigabyte GeForce RTX 3060 Windforce 12GB", "Modelo descatalogado", new BigDecimal("285.00"), 0, "", false, false, LocalDate.now(), graficas, "Gigabyte", 24),
                // Memoria
                new Componente(++siguienteId, "RAM-COR-32DDR5", "Corsair Vengeance DDR5 6000MHz 32GB", "Memoria RAM CL30 optimizada para AMD/Intel", new BigDecimal("119.99"), 30, "", true, true, LocalDate.now(), memorias, "Corsair", 60),
                new Componente(++siguienteId, "RAM-KIN-16DDR4", "Kingston FURY Beast DDR4 3200MHz 16GB", "Memoria RAM perfil bajo CL16", new BigDecimal("42.50"), 40, "", true, false, LocalDate.now(), memorias, "Kingston", 60),
                new Componente(++siguienteId, "RAM-GSK-32DDR5", "G.Skill Trident Z5 RGB DDR5 6400MHz 32GB", "Memoria RAM con iluminación RGB", new BigDecimal("139.00"), 0, "", true, false, LocalDate.now(), memorias, "G.Skill", 60),
                new Componente(++siguienteId, "RAM-CRU-8DDR4", "Crucial Basics DDR4 2666MHz 8GB", "Módulo antiguo retirado del catálogo", new BigDecimal("19.90"), 3, "", false, false, LocalDate.now(), memorias, "Crucial", 24)
        ));

    }

    // Metodos

    public ArrayList<ComponenteResumenDto> listarComponentesActivos(){
        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();

        for(Componente c : componentes){

            if(c.getActivo()){
                resultado.add(parsearAResumenDto(c));
            }
        }
        return resultado;
    }

    public ArrayList<ComponenteResumenDto> listarComponentesDestacados(){
        ArrayList<ComponenteResumenDto> resultado = new ArrayList<>();

        for(Componente c : componentes){

            if(c.getActivo() && c.getDestacado()){
                resultado.add(parsearAResumenDto(c));
            }
        }
        return resultado;
    }




    // Herramientas

    // Convertir a Resumen
    private ComponenteResumenDto parsearAResumenDto(Componente componente){
        return new ComponenteResumenDto(
                componente.getId(),
                componente.getNombre(),
                componente.getPrecio(),
                componente.getImagenUrl(),
                componente.getCategoria().getNombre()
        );
    }

    // Convertir a Detalle
    private ComponenteDetalleDto parsearADetalleDto(Componente componente){

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


}

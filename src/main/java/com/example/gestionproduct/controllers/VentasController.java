package com.example.gestionproduct.controllers;

import com.example.gestionproduct.models.Product;
import com.example.gestionproduct.models.Ventas;
import com.example.gestionproduct.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class VentasController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/listar")
    public List<Ventas> List() {
        return ventaService.listar();
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Ventas save(@RequestBody  Ventas venta) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Formatear la fecha y hora según un patrón específico
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        venta.setFechaHora(LocalDateTime.parse(fechaHoraFormateada, formatter));
        return ventaService.guardar(venta);
    }


}

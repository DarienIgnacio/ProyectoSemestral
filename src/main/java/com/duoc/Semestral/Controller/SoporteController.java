package com.duoc.Semestral.Controller;

import com.duoc.Semestral.Model.Soporte;
import com.duoc.Semestral.Service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soportes")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public List<Soporte> getSoportes() {
        return soporteService.getSoportes();
    }

    @GetMapping("/{id}")
    public Soporte getSoporteById(@PathVariable int id) {
        return soporteService.getSoporteById(id);
    }

    @PostMapping
    public String createSoporte(@RequestBody Soporte soporte) {
        return soporteService.addSoporte(soporte);
    }

    @PutMapping("/{id}")
    public String updateSoporte(@PathVariable int id, @RequestBody Soporte soporte) {
        return soporteService.updateSoporte(soporte);
    }

    @DeleteMapping("/{id}")
    public String deleteSoporte(@PathVariable int id) {
        return soporteService.deleteSoporte(id);
    }
}

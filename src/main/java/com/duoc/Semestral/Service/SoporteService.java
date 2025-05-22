package com.duoc.Semestral.Service;

import com.duoc.Semestral.Model.Soporte;
import com.duoc.Semestral.Repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {
    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> getSoportes() {
        return soporteRepository.findAll();
    }

    public Soporte getSoporteById(int id) {
        return soporteRepository.getById(id);
    }

    public String addSoporte(Soporte soporte) {
        soporteRepository.save(soporte);
        return soporte.toString();
    }

    public String updateSoporte(Soporte soporte) {
        soporteRepository.save(soporte);
        return "Soporte actualizado con exito!";
    }

    public String deleteSoporte(int id) {
        soporteRepository.deleteById(id);
        return "Soporte eliminado con exito!";
    }
}

package org.example.service;

import org.example.entity.assembler.RetoAssembler;
import org.example.entity.domain.RetoDO;
import org.example.entity.dto.RetoDTO;

import java.util.*;

public class RetoService {
    private final Map<Long, RetoDO> retos = new HashMap<>();

    public RetoService() {
    }

    public List<RetoDO> getRetos() {
        return new ArrayList<>(this.retos.values());
    }

    public Optional<RetoDO> getReto(Long id) {
        if(retos.containsKey(id)) {
            return Optional.of(retos.get(id));
        }
        return Optional.empty();
    }

    public void crearReto(RetoDTO retoDto) {
        RetoDO reto = RetoAssembler.getDO(retoDto);
        reto.id = Long.valueOf(retos.size());
        retos.put(reto.id, reto);
        System.out.println("Reto creado: " + reto.nombre + " | " + reto.id.toString());
    }
}

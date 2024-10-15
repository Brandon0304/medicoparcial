package org.example.aplication.service;

import org.example.domain.Medico;

import java.util.List;

public interface MedicoService {
    List<Medico> findAll();
    Medico findById(int id);
    void save(Medico medico);
    void update(Medico medico);
    void delete(int id);
}
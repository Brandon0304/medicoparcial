package org.example.aplication.service;

import org.example.domain.Medico;
import org.example.interfaces.MedicoRepository;

import java.util.List;

public class MedicoServiceImpl implements MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico findById(int id) {
        return medicoRepository.findById(id);
    }

    @Override
    public void save(Medico medico) {
        validarMedico(medico);
        medicoRepository.save(medico);
    }

    @Override
    public void update(Medico medico) {
        validarMedico(medico);
        medicoRepository.update(medico);
    }

    @Override
    public void delete(int id) {
        medicoRepository.delete(id);
    }

    private void validarMedico(Medico medico) {
        if (medico.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del medico no puede estar vacío");
        }
        if (medico.getEdad() <= 0) {
            throw new IllegalArgumentException("La edad del medico debe ser mayor a cero");
        }
    }
}

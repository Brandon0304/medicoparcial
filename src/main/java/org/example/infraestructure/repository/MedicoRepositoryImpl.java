package org.example.infraestructure.repository;

import org.example.domain.Medico;
import org.example.interfaces.MedicoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicoRepositoryImpl implements MedicoRepository {
    private static final String FILE_NAME = "medicos.dat";

    @Override
    public List<Medico> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Medico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Medico findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Medico medico) {
        List<Medico> medicos = findAll();
        medicos.add(medico);
        saveAll(medicos);
    }

    @Override
    public void update(Medico medico) {
        List<Medico> medicos = findAll();
        medicos = medicos.stream()
                .map(p -> p.getId() == medico.getId() ? medico : p)
                .collect(Collectors.toList());
        saveAll(medicos);
    }

    @Override
    public void delete(int id) {
        List<Medico> medicos = findAll();
        medicos = medicos.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(medicos);
    }

    private void saveAll(List<Medico> medicos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(medicos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

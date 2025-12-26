package com.example.demo.service.impl;

import com.example.demo.entity.Program;
import com.example.demo.entity.University;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProgramRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final UniversityRepository universityRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, UniversityRepository universityRepository) {
        this.programRepository = programRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Program createProgram(Program program) {
        University university = universityRepository.findById(program.getUniversity().getId())
                .orElseThrow(() -> new ResourceNotFoundException("University", program.getUniversity().getId()));

        programRepository.findByUniversityAndName(university, program.getName())
                .ifPresent(p -> { throw new BadRequestException("Program already exists for this university"); });

        program.setUniversity(university);
        return programRepository.save(program);
    }

    @Override
    public Program getById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program", id));
    }

    @Override
    public List<Program> getByUniversityId(Long universityId) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("University", universityId));
        return programRepository.findByUniversity(university);
    }
}

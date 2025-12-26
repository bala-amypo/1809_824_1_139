package com.example.demo.service;

import com.example.demo.entity.Program;
import java.util.List;

public interface ProgramService {
    Program createProgram(Program program);
    Program getById(Long id);
    List<Program> getByUniversityId(Long universityId);
}

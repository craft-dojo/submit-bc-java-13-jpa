package com.cybersoft.assign_13_jpa.services.imp;

import com.cybersoft.assign_13_jpa.entity.StudentEntity;
import com.cybersoft.assign_13_jpa.repository.StudentRepository;
import com.cybersoft.assign_13_jpa.request.InsertStudentRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateStudentRequestDTO;
import com.cybersoft.assign_13_jpa.response.StudentResponseDTO;
import com.cybersoft.assign_13_jpa.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponseDTO> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDTO::toDTO)
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        StudentEntity studentExisting = studentRepository.findById(id).orElse(null);

        return studentExisting != null ? StudentResponseDTO.toDTO(studentExisting) : null;
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Student with id %s not found", id));
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO addStudent(InsertStudentRequestDTO dto) {
        if (studentRepository.findByEmail(dto.getEmail()).isEmpty()) {
            throw new EntityNotFoundException(String.format("Student with email %s already exists", dto.getEmail()));
        }

        StudentEntity newStudent = StudentEntity.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .major(dto.getMajor())
                .build();

        StudentEntity insertedStudent = studentRepository.save(newStudent);
        return StudentResponseDTO.toDTO(insertedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(UpdateStudentRequestDTO dto) {
        StudentEntity studentExisting = studentRepository
                .findById(dto.getId())
                .orElseThrow(() -> {
                    String errMes = String.format("Student with id %s not found", dto.getId());
                    return new EntityNotFoundException(errMes);
                });

        if (!studentExisting.getEmail().equals(dto.getEmail())
                && studentRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EntityNotFoundException(String.format("Student with email %s already exists", dto.getEmail()));
        }

        StudentEntity updateStudent = StudentEntity.builder()
                .id(studentExisting.getId())
                .email(dto.getEmail())
                .name(dto.getName())
                .major(dto.getMajor())
                .build();

        StudentEntity updatedStudent = studentRepository.save(updateStudent);
        return StudentResponseDTO.toDTO(updatedStudent);
    }
}

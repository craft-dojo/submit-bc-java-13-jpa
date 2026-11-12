package com.cybersoft.assign_13_jpa.services;

import com.cybersoft.assign_13_jpa.request.InsertStudentRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateStudentRequestDTO;
import com.cybersoft.assign_13_jpa.response.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getStudents();

    StudentResponseDTO getStudentById(Long id);

    void deleteStudentById(Long id);

    StudentResponseDTO addStudent(InsertStudentRequestDTO dto);

    StudentResponseDTO updateStudent(UpdateStudentRequestDTO dto);
}

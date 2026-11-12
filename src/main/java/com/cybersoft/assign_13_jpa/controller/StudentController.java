package com.cybersoft.assign_13_jpa.controller;

import com.cybersoft.assign_13_jpa.request.InsertStudentRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateStudentRequestDTO;
import com.cybersoft.assign_13_jpa.response.BaseResponse;
import com.cybersoft.assign_13_jpa.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllStudents() {
        BaseResponse res = BaseResponse.success(studentService.getStudents());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getStudentById(@PathVariable Long id) {
        BaseResponse res = BaseResponse.success(studentService.getStudentById(id));

        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> insertStudent(@RequestBody InsertStudentRequestDTO dto) {
        BaseResponse res = BaseResponse.success(studentService.addStudent(dto));
        return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateStudent(@RequestBody UpdateStudentRequestDTO dto) {
        BaseResponse res = BaseResponse.success(studentService.updateStudent(dto));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);

        return ResponseEntity.ok(BaseResponse.success(null));
    }

}

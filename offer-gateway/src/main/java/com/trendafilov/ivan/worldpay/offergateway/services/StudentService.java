package com.trendafilov.ivan.worldpay.offergateway.services;

import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.StudentRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudents();

    StudentResponse findStudentByStudentId(String studentId);

    StudentResponse createStudentByStudentRequest(StudentRequest studentRequest);
}

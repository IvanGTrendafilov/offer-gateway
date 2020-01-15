package com.trendafilov.ivan.worldpay.offergateway.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.StudentRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.StudentResponse;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private static final String STUDENT_URL = "http://OFFER-SERVICE:8025/student/v1/";
    @Autowired
    private IRestClient restClient;

    @Override
    public List<StudentResponse> getAllStudents() {
        final TypeReference<List<StudentResponse>>
            typeReference =
            new TypeReference<List<StudentResponse>>() {
            };

        final List<StudentResponse>
            studentResponses =
            restClient.exchange(STUDENT_URL, null,
                                HttpMethod.GET, null,
                                typeReference
            );
        return studentResponses;
    }

    @Override
    public StudentResponse findStudentByStudentId(final String studentId) {
        final StudentResponse studentResponse =
            restClient.exchange(STUDENT_URL + studentId, null,
                                HttpMethod.GET, null,
                                StudentResponse.class
            );
        return studentResponse;
    }

    @Override
    public StudentResponse createStudentByStudentRequest(final StudentRequest studentRequest) {
        final StudentResponse studentResponse =
            restClient.exchange(STUDENT_URL, null,
                                HttpMethod.POST, studentRequest,
                                StudentResponse.class
            );
        return studentResponse;
    }
}

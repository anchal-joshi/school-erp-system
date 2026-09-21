package com.example.schoolerp.service;

import com.example.schoolerp.dto.ExamRequest;
import com.example.schoolerp.dto.ExamResponse;
import com.example.schoolerp.entity.Exam;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.exception.ExamNotFoundException;
import com.example.schoolerp.repository.ExamRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final CurrentUserService currentUserService;

    public ExamService(ExamRepository examRepository, CurrentUserService currentUserService) {
        this.examRepository = examRepository;
        this.currentUserService = currentUserService;
    }

    public ExamResponse createExam(ExamRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Exam exam = new Exam();
        exam.setName(request.getName());
        exam.setYear(request.getYear());
        exam.setSchool(currentUser.getSchool());

        Exam saved = examRepository.save(exam);

        ExamResponse response = new ExamResponse(
                saved.getId(),
                saved.getName(),
                saved.getYear()
        );

        return response;
    }

    public ExamResponse getExamById(Long examId){

        User currentUser = currentUserService.getCurrentUser();

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: "+ examId));

        if (!currentUser.getSchool().getId().equals(exam.getSchool().getId())){
            throw new IllegalArgumentException("You can only access exams of your own school");
        }

        ExamResponse response = new ExamResponse(
                exam.getId(),
                exam.getName(),
                exam.getYear()
        );

        return response;
    }

    public ExamResponse updateExam(Long examId, ExamRequest request){
        User currentUser = currentUserService.getCurrentUser();

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: "+ examId));

        if (!currentUser.getSchool().getId().equals(exam.getSchool().getId())){
            throw new IllegalArgumentException("You can only update exams of your own school");
        }

        exam.setName(request.getName());
        exam.setYear(request.getYear());

        Exam savedExam = examRepository.save(exam);

        ExamResponse response = new ExamResponse(
                savedExam.getId(),
                savedExam.getName(),
                savedExam.getYear()
        );

        return response;
    }

    public void delete(Long examId){
        User currentUser = currentUserService.getCurrentUser();

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: "+ examId));

        if (!currentUser.getSchool().getId().equals(exam.getSchool().getId())){
            throw new IllegalArgumentException("You can only delete exams of your own school");
        }

        examRepository.deleteById(examId);
    }
}

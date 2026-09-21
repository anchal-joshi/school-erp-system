package com.example.schoolerp.service;

import com.example.schoolerp.dto.MarksRequest;
import com.example.schoolerp.dto.MarksResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.ExamNotFoundException;
import com.example.schoolerp.exception.MarksNotFoundException;
import com.example.schoolerp.exception.StudentNotFoundException;
import com.example.schoolerp.exception.SubjectNotFoundException;
import com.example.schoolerp.repository.ExamRepository;
import com.example.schoolerp.repository.MarksRepository;
import com.example.schoolerp.repository.StudentRepository;
import com.example.schoolerp.repository.SubjectRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class MarksService {

    private final MarksRepository marksRepository;
    private final CurrentUserService currentUserService;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;

    public MarksService(MarksRepository marksRepository, CurrentUserService currentUserService, StudentRepository studentRepository, SubjectRepository subjectRepository, ExamRepository examRepository) {
        this.marksRepository = marksRepository;
        this.currentUserService = currentUserService;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
    }

    public MarksResponse createMarks(MarksRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ request.getStudentId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with id: "+ request.getSubjectId()));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: "+ request.getExamId()));

        if (!currentUser.getSchool().getId().equals(student.getUser().getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        if (!currentUser.getSchool().getId().equals(subject.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        if (!currentUser.getSchool().getId().equals(exam.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        if(marksRepository.existsByStudentIdAndSubjectIdAndExamId(student.getId(), subject.getId(), exam.getId())){
            throw new IllegalArgumentException("Marks already exists");
        }

        Marks marks = new Marks();

        marks.setStudent(student);
        marks.setFullMarks(request.getFullMarks());
        marks.setExam(exam);
        marks.setSubject(subject);
        marks.setSchool(currentUser.getSchool());

        if (request.getMarks() > request.getFullMarks()){
            throw new IllegalArgumentException("Marks cannot be greater than full marks");
        }

        marks.setMarks(request.getMarks());

        Marks savedMarks = marksRepository.save(marks);

        MarksResponse response = new MarksResponse(
                savedMarks.getId(),
                savedMarks.getStudent().getId(),
                savedMarks.getSubject().getId(),
                savedMarks.getExam().getId(),
                savedMarks.getMarks(),
                savedMarks.getFullMarks()
        );

        return response;
    }

    public MarksResponse getMarks(Long marksId){

        User currentUser = currentUserService.getCurrentUser();

        Marks marks = marksRepository.findById(marksId)
                .orElseThrow(() -> new MarksNotFoundException("Marks not found with id: "+ marksId));

        if (!marks.getSchool().getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school");
        }

        MarksResponse response = new MarksResponse(
                marks.getId(),
                marks.getStudent().getId(),
                marks.getSubject().getId(),
                marks.getExam().getId(),
                marks.getMarks(),
                marks.getFullMarks()
        );

        return response;
    }

    public MarksResponse updateMarks(Long marksId, MarksRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ request.getStudentId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with id: "+ request.getSubjectId()));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: "+ request.getExamId()));

        if (!currentUser.getSchool().getId().equals(student.getUser().getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        if (!currentUser.getSchool().getId().equals(subject.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        if (!currentUser.getSchool().getId().equals(exam.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        Marks marks = marksRepository.findById(marksId)
                .orElseThrow(() -> new MarksNotFoundException("Marks not found with id: "+ marksId));

        if (!currentUser.getSchool().getId().equals(marks.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        marks.setStudent(student);
        marks.setFullMarks(request.getFullMarks());
        marks.setExam(exam);
        marks.setSubject(subject);
        marks.setSchool(currentUser.getSchool());

        if (request.getMarks() > request.getFullMarks()){
            throw new IllegalArgumentException("Marks cannot be greater than full marks");
        }

        marks.setMarks(request.getMarks());

        Marks savedMarks = marksRepository.save(marks);

        MarksResponse response = new MarksResponse(
                savedMarks.getId(),
                savedMarks.getStudent().getId(),
                savedMarks.getSubject().getId(),
                savedMarks.getExam().getId(),
                savedMarks.getMarks(),
                savedMarks.getFullMarks()
        );

        return response;
    }

    public void delete(Long marksId){

        User currentUser = currentUserService.getCurrentUser();

        Marks marks = marksRepository.findById(marksId)
                .orElseThrow(() -> new MarksNotFoundException("Marks not found with id: "+ marksId));

        if (!currentUser.getSchool().getId().equals(marks.getSchool().getId())){
            throw new IllegalArgumentException("Wrong school!");
        }

        marksRepository.deleteById(marksId);
    }
}

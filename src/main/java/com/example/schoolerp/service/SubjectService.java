package com.example.schoolerp.service;

import com.example.schoolerp.dto.SectionResponse;
import com.example.schoolerp.dto.SubjectRequest;
import com.example.schoolerp.dto.SubjectResponse;
import com.example.schoolerp.entity.Subject;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.exception.SubjectNotFoundException;
import com.example.schoolerp.repository.SubjectRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final CurrentUserService currentUserService;

    public SubjectService(SubjectRepository subjectRepository, CurrentUserService currentUserService) {
        this.subjectRepository = subjectRepository;
        this.currentUserService = currentUserService;
    }

    public List<SubjectResponse> getAllSubjects(){
        Long schoolId = currentUserService.getCurrentSchoolId();

        List<Subject> subjects = subjectRepository.findBySchool_Id(schoolId);

        List<SubjectResponse> responses = subjects.stream()
                .map(subject -> new SubjectResponse(
                        subject.getId(),
                        subject.getName(),
                        subject.getSchool().getId()
                )).toList();

        return responses;
    }

    public SubjectResponse createSubject(SubjectRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Subject subject = new Subject();

        subject.setName(request.getName());
        subject.setSchool(currentUser.getSchool());

        Subject savedSubject = subjectRepository.save(subject);

        return new SubjectResponse(savedSubject.getId(),
                savedSubject.getName(),
                savedSubject.getSchool().getId()
        );
    }

    public SubjectResponse getSubjectById(Long id){
        User currentUser = currentUserService.getCurrentUser();

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(
                        "Subject not found with id: "+ id)
                );

        if (!subject.getSchool().getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("You can only access subjects from your own school");
        }

        return new SubjectResponse(
                subject.getId(),
                subject.getName(),
                subject.getSchool().getId()
        );
    }
}

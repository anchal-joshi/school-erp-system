package com.example.schoolerp.service;

import com.example.schoolerp.dto.SectionSubjectRequest;
import com.example.schoolerp.dto.SectionSubjectResponse;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.exception.SectionNotFoundException;
import com.example.schoolerp.exception.SectionSubjectNotFoundException;
import com.example.schoolerp.exception.SubjectNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import com.example.schoolerp.repository.SectionRepository;
import com.example.schoolerp.repository.SectionSubjectRepository;
import com.example.schoolerp.repository.SubjectRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionSubjectService {

    private final SectionSubjectRepository sectionSubjectRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final CurrentUserService currentUserService;
    private final SchoolRepository schoolRepository;

    public SectionSubjectService(SectionSubjectRepository sectionSubjectRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, CurrentUserService currentUserService, SchoolRepository schoolRepository) {
        this.sectionSubjectRepository = sectionSubjectRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.currentUserService = currentUserService;
        this.schoolRepository = schoolRepository;
    }

    public SectionSubjectResponse createSectionSubject(SectionSubjectRequest request){

        User currentUser = currentUserService.getCurrentUser();

        School currentSchool = schoolRepository.findById(currentUser.getSchool().getId())
                .orElseThrow(() -> new SchoolNotFoundException("School not found with this id"));

        Section section = sectionRepository.findById(request.getSectionId())
                .orElseThrow(() -> new SectionNotFoundException(
                        "Section not found with id: " + request.getSectionId()));

        School sectionSchool = section.getSchool();

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(
                        "Subject not found with id: " + request.getSubjectId()));

        School subjectSchool = subject.getSchool();

        if (!sectionSchool.getId().equals(currentSchool.getId())){
            throw new IllegalArgumentException(
                    "School id of this section doesn't match with current user's school id");
        }

        if (!subjectSchool.getId().equals(currentSchool.getId())){
            throw new IllegalArgumentException(
                    "School id of this subject doesn't match with current user's school id");
        }

        SectionSubject sectionSubject = new SectionSubject();

        sectionSubject.setSection(section);
        sectionSubject.setSubject(subject);
        sectionSubject.setSchool(currentSchool);

        SectionSubject saved = sectionSubjectRepository.save(sectionSubject);

        return new SectionSubjectResponse(
                saved.getId(),
                saved.getSection().getId(),
                saved.getSubject().getId(),
                saved.getSchool().getId()
        );
    }

    public List<SectionSubjectResponse> getSubjectsBySection(Long id){
        User currentUser = currentUserService.getCurrentUser();

        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException("Section not foud with id: "+ id));

        if (!section.getSchool().getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("You can only get subjects from your own school");
        }

        Long schoolId = currentUser.getSchool().getId();

        List<SectionSubject> sectionSubjects = sectionSubjectRepository.findAllBySchool_IdAndSection_Id(schoolId, id);

        List<SectionSubjectResponse> responses = sectionSubjects.stream()
                .map(sectionSubject -> new SectionSubjectResponse(
                        sectionSubject.getId(),
                        sectionSubject.getSection().getId(),
                        sectionSubject.getSubject().getId(),
                        sectionSubject.getSchool().getId()
                )).toList();

        return responses;
    }

    public void deleteById(Long id){
        User currentUser = currentUserService.getCurrentUser();

        SectionSubject sectionSubject = sectionSubjectRepository.findById(id)
                .orElseThrow(() -> new SectionSubjectNotFoundException("SectionSubject not found with id "+ id));

        if (!sectionSubject.getSchool().getId().equals(currentUser.getSchool().getId())){
            throw new IllegalArgumentException("You can only delete SectionSubject for your own school");
        }

        sectionSubjectRepository.deleteById(id);
    }

}

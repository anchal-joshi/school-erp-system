package com.example.schoolerp.service;

import com.example.schoolerp.dto.SectionRequest;
import com.example.schoolerp.dto.SectionResponse;
import com.example.schoolerp.entity.Class;
import com.example.schoolerp.entity.Section;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.exception.ClassNotFoundException;
import com.example.schoolerp.exception.SectionNotFoundException;
import com.example.schoolerp.repository.ClassRepository;
import com.example.schoolerp.repository.SectionRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {


    private final CurrentUserService currentUserService;
    private final ClassRepository classRepository;
    private final SectionRepository sectionRepository;

    public SectionService(CurrentUserService currentUserService, ClassRepository classRepository, SectionRepository sectionRepository) {
        this.currentUserService = currentUserService;
        this.classRepository = classRepository;
        this.sectionRepository = sectionRepository;
    }

    public SectionResponse createSection(SectionRequest request){
        User currentUser = currentUserService.getCurrentUser();

        Long schoolId = currentUserService.getCurrentSchoolId();

        Class aClass = classRepository.findById(request.getClassId())
                .orElseThrow(()-> new ClassNotFoundException("Class not found with id: "+ request.getClassId()));

        if (!schoolId.equals(aClass.getSchool().getId())){
            throw new IllegalArgumentException("You can only create sections for your own school");
        }

        Section section = new Section();
        section.setName(request.getName());
        section.setaClass(aClass);
        section.setSchool(currentUser.getSchool());

        Section savedSection = sectionRepository.save(section);

        return new SectionResponse(
                savedSection.getId(),
                savedSection.getName(),
                savedSection.getaClass().getId(),
                savedSection.getSchool().getId()
        );
    }

    public List<SectionResponse> getAllSections(){
        Long schoolId = currentUserService.getCurrentSchoolId();;

        List<Section> sections = sectionRepository.findBySchool_Id(schoolId);

        return sections.stream()
                .map(section -> new SectionResponse(
                        section.getId(),
                        section.getName(),
                        section.getaClass().getId(),
                        section.getSchool().getId()
                    )
                )
                .toList();
    }

    public void deleteById(Long id){
        Long schoolId = currentUserService.getCurrentSchoolId();

        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException("Section not found with id: "+ id));

        if (!schoolId.equals(section.getSchool().getId())){
            throw new IllegalArgumentException("You can only delete sections from your own school");
        }

        sectionRepository.deleteById(id);
    }

    public List<SectionResponse> getSectionByClass(Long id){
        Long schoolId = currentUserService.getCurrentSchoolId();

        Class aClass = classRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("Class not found with id: "+ id));

        if (!schoolId.equals(aClass.getSchool().getId())){
            throw new IllegalArgumentException("You can only access classes and their sections of your own school");
        }

        List<Section> sections = sectionRepository.findBySchool_IdAndAClass_Id(schoolId, aClass.getId());

        return sections.stream()
                .map(section -> new SectionResponse(
                                section.getId(),
                                section.getName(),
                                section.getaClass().getId(),
                                section.getSchool().getId()
                        )
                )
                .toList();
    }


}

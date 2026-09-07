package com.example.schoolerp.service;

import com.example.schoolerp.dto.SchoolRequest;
import com.example.schoolerp.dto.SchoolResponse;
import com.example.schoolerp.entity.School;
import com.example.schoolerp.entity.SchoolStatus;
import com.example.schoolerp.exception.SchoolNotFoundException;
import com.example.schoolerp.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public SchoolResponse registerSchool(SchoolRequest schoolRequest){

        School school = new School();
        school.setName(schoolRequest.getName());
        school.setAddress(schoolRequest.getAddress());
        school.setContactEmail(schoolRequest.getContactEmail());
        school.setContactPhone(schoolRequest.getContactPhone());
        School savedSchool = schoolRepository.save(school);
        return new SchoolResponse(
                savedSchool.getId(),
                savedSchool.getName(),
                savedSchool.getAddress(),
                savedSchool.getContactEmail(),
                savedSchool.getContactPhone(),
                savedSchool.getStatus(),
                savedSchool.getCreatedAt()
        );
    }

    public SchoolResponse updateStatus(Long id, SchoolStatus status){
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found"));
        school.setStatus(status);
        School updatedSchool = schoolRepository.save(school);
        return new SchoolResponse(
                updatedSchool.getId(),
                updatedSchool.getName(),
                updatedSchool.getAddress(),
                updatedSchool.getContactEmail(),
                updatedSchool.getContactPhone(),
                updatedSchool.getStatus(),
                updatedSchool.getCreatedAt()
        );
    }

    public List<SchoolResponse> getAllSchools(){
        List<School> schools = schoolRepository.findAll();

        return schools
                .stream()
                .map(school -> new SchoolResponse(
                        school.getId(),
                        school.getName(),
                        school.getAddress(),
                        school.getContactEmail(),
                        school.getContactPhone(),
                        school.getStatus(),
                        school.getCreatedAt()
                )).toList();
    }
}

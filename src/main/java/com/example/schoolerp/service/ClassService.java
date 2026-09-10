package com.example.schoolerp.service;

import com.example.schoolerp.dto.ClassRequest;
import com.example.schoolerp.dto.ClassResponse;
import com.example.schoolerp.entity.Class;
import com.example.schoolerp.entity.User;
import com.example.schoolerp.repository.ClassRepository;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;
import com.example.schoolerp.exception.ClassNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final CurrentUserService currentUserService;
    private final ClassRepository classRepository;

    public ClassService(CurrentUserService currentUserService, ClassRepository classRepository) {
        this.currentUserService = currentUserService;
        this.classRepository= classRepository;
    }

    public ClassResponse createClass(ClassRequest request){

        User currentUser = currentUserService.getCurrentUser();

        Class newClass = new Class();

        newClass.setName(request.getName());
        newClass.setSchool(currentUser.getSchool());

        Class savedClass = classRepository.save(newClass);

        return new ClassResponse(
                savedClass.getId(),
                savedClass.getName(),
                savedClass.getSchool().getId()
        );
    }

    public List<ClassResponse> getAllClasses(){
        User currentUser = currentUserService.getCurrentUser();

        List<Class> classes = classRepository.findBySchool_Id(
                currentUser.getSchool().getId()
        );

        List<ClassResponse> allClasses = classes
                .stream()
                .map(c -> new ClassResponse(
                        c.getId(),
                        c.getName(),
                        c.getSchool().getId()
                    )
                )
                .collect(Collectors.toList());

        return allClasses;
    }

    public ClassResponse getClassById(Long id) throws ClassNotFoundException {
        Long schoolId = currentUserService.getCurrentSchoolId();

        Class c = classRepository.findById(id)
                .orElseThrow(() -> new com.example.schoolerp.exception.ClassNotFoundException("Class not found"));

        if (!c.getSchool().getId().equals(schoolId)){
            throw new IllegalArgumentException("You can access classes only from your own school");
        }

        return new ClassResponse(
                c.getId(),
                c.getName(),
                c.getSchool().getId()
        );
    }

    public void deleteClassById(Long id){
        User currentUser = currentUserService.getCurrentUser();

        Class existingClass = classRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("Class not found with id: "+ id));

        if (currentUser.getSchool().getId().equals(
                existingClass.getSchool().getId())
        ){
            classRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("You can only delete class from your own school");
        }
    }
}

package com.example.schoolerp.service;

import com.example.schoolerp.dto.*;
import com.example.schoolerp.entity.*;
import com.example.schoolerp.exception.StudentNotFoundException;
import com.example.schoolerp.repository.*;
import com.example.schoolerp.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentDashboardService {

    private final CurrentUserService currentUserService;
    private final StudentRepository studentRepository;
    private final SectionSubjectRepository sectionSubjectRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;

    public StudentDashboardService(CurrentUserService currentUserService, StudentRepository studentRepository, SectionSubjectRepository sectionSubjectRepository, AttendanceRepository attendanceRepository, MarksRepository marksRepository) {
        this.currentUserService = currentUserService;
        this.studentRepository = studentRepository;
        this.sectionSubjectRepository = sectionSubjectRepository;
        this.attendanceRepository = attendanceRepository;
        this.marksRepository = marksRepository;
    }

    public StudentDashboardResponse getDashboard(){

        User user = currentUserService.getCurrentUser();

        Student student = studentRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        StudentResponse profile = new StudentResponse(
                student.getId(),
                student.getUser().getId(),
                student.getUser().getEmail(),
                student.getName(),
                student.getPhone(),
                student.getSection().getId()
        );

        List<SectionSubject> subjects = sectionSubjectRepository.findAllBySection_Id(student.getSection().getId());

        List<SubjectResponse> subjectResponses = subjects.stream()
                .map(subject -> new SubjectResponse(
                        subject.getId(),
                        subject.getSubject().getName(),
                        subject.getSchool().getId()
                )).toList();

        List<Attendance> attendanceList = attendanceRepository.findAllByStudentId(student.getId());

        int totalDays = attendanceList.size();
        int presentDays = 0;
        int absentDays = 0;

        for (Attendance attendance : attendanceList){
            if ((attendance.getStatus()).equals(AttendanceStatus.PRESENT)){
                presentDays++;
            }
            else if((attendance.getStatus()).equals(AttendanceStatus.ABSENT)) {
                absentDays++;
            }
        }

        AttendanceSummaryResponse attendanceSummaryResponse = new AttendanceSummaryResponse(
                totalDays,
                presentDays,
                absentDays
        );

        List<Marks> marks = marksRepository.findAllByStudentId(student.getId());

        Map<Long, ExamResultResponse> resultMap = new HashMap<>();

        for (Marks m : marks){
            SubjectMarksResponse subjectMarks = new SubjectMarksResponse(
                    m.getSubject().getId(),
                    m.getSubject().getName(),
                    m.getMarks(),
                    m.getFullMarks()
            );

            ExamResultResponse examResult = resultMap.get(m.getExam().getId());

            if (examResult == null){
                List<SubjectMarksResponse> subjectList = new ArrayList<>();
                subjectList.add(subjectMarks);

                examResult = new ExamResultResponse(
                        m.getExam().getId(),
                        m.getExam().getName(),
                        m.getExam().getYear(),
                        subjectList
                );

                resultMap.put(m.getExam().getId(), examResult);
            } else {
                examResult.getSubjectsMarks().add(subjectMarks);
            }
        }

        List<ExamResultResponse> results = new ArrayList<>(resultMap.values());

        return new StudentDashboardResponse(
                profile,
                subjectResponses,
                attendanceSummaryResponse,
                results
        );




    }
}

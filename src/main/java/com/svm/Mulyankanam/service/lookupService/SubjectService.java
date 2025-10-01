package com.svm.Mulyankanam.service.lookupService;

import com.svm.Mulyankanam.model.lookup.Subject;
import com.svm.Mulyankanam.repository.lookup.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject createSubject(String subjectName, String description) {
        Subject dbSubject = subjectRepository.findBySubjectName(subjectName);

        if (dbSubject != null) {
            return dbSubject; // Subject already exists, return it
        }

        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subject.setDescription(description);

        subjectRepository.save(subject);

        return subject;
    }

    public Optional<Subject> getSubjectById(String subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject updateSubject(String subjectId, Subject subjectDetails) {
        Subject examType = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        examType.setSubjectName(subjectDetails.getSubjectName());
        examType.setDescription(subjectDetails.getDescription());

        return subjectRepository.save(examType);
    }

    public void deleteSubject(String subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
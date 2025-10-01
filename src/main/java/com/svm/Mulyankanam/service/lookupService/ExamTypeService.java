package com.svm.Mulyankanam.service.lookupService;

import com.svm.Mulyankanam.model.lookup.ExamType;
import com.svm.Mulyankanam.repository.lookup.ExamTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamTypeService {

    private final ExamTypeRepository examTypeRepository;

    public ExamType createExamType(String examTypeName, String description) {
        ExamType dbExamType = examTypeRepository.findByExamTypeName(examTypeName);

        if (dbExamType != null) {
            return dbExamType; // ExamType already exists, return it
        }

        ExamType examType = new ExamType();
        examType.setExamTypeName(examTypeName);
        examType.setDescription(description);

        examTypeRepository.save(examType);

        return examType;
    }

    public Optional<ExamType> getExamTypeById(String examTypeId) {
        return examTypeRepository.findById(examTypeId);
    }

    public List<ExamType> getAllExamTypes() {
        return examTypeRepository.findAll();
    }

    public ExamType updateExamType(String examTypeId, ExamType examTypeDetails) {
        System.out.println("Hello World");
//        ExamType examType = examTypeRepository.findByExamTypeName(examTypeDetails.getExamTypeName());
        ExamType examType = examTypeRepository.findById(examTypeId)
                .orElseThrow(() -> new RuntimeException("ExamType not found with id: " + examTypeId));

        examType.setExamTypeName(examTypeDetails.getExamTypeName());
        examType.setDescription(examTypeDetails.getDescription());

        return examTypeRepository.save(examType);
    }

    public void deleteExamType(String examTypeId) {
        System.out.println("Hello World");
        System.out.println(examTypeId);
        examTypeRepository.deleteById(examTypeId);
    }
}
package com.svm.Mulyankanam.service.lookupService;

import com.svm.Mulyankanam.model.lookup.Standard;
import com.svm.Mulyankanam.repository.lookup.StandardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardService {

    private final StandardRepository standardRepository;

    public Standard createStandard(String standard) {
        Standard dbStandard = standardRepository.findByStandard(standard);

        if (dbStandard != null) {
            return dbStandard; // Standard already exists, return it
        }

        Standard std = new Standard();
        std.setStandard(standard);

        standardRepository.save(std);

        return std;
    }

    public Optional<Standard> getStandardById(String examTypeId) {
        return standardRepository.findById(examTypeId);
    }

    public List<Standard> getAllStandards() {
        return standardRepository.findAll();
    }

    public Standard updateStandard(String standardId, Standard standard) {
        Standard examType = standardRepository.findById(standardId)
                .orElseThrow(() -> new RuntimeException("Standard not found with id: " + standardId));

        examType.setStandard(standard.getStandard());

        return standardRepository.save(examType);
    }

    public void deleteStandard(String standardId) {
        standardRepository.deleteById(standardId);
    }
}
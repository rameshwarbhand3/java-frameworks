package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface HistoryGradeDao extends CrudRepository<HistoryGrade, Integer> {

    Iterable<HistoryGrade> findGradeByStudentId(int id);
}

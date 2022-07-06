package kr.human.di.service;

import org.joda.time.LocalDate;

public interface DateService {
	 
    LocalDate getNextAssessmentDate();
}
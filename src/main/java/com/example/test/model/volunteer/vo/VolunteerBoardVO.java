package com.example.test.model.volunteer.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class VolunteerBoardVO {

	private Long volunteerBoardNo;
	private String volunteerBoardTitle;
	private String volunteerBoardContent;
	private String volunteerDocMajor;
	private int volunteers;
	private int volunteerApplicants;
	private String volunteerBoardImage;
	private String volunteerArea;
	private String volunteerLocation;
	private String volunteerPeriod;
	private String volunteerRecruitmentPeriod;
	private int recruitmentStatus;
	private int accrue;
}

/**
 * 
 */
package com.dbms.kungfu.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbms.kungfu.dao.StudentDao;
import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.AttendanceAttributes;
import com.dbms.kungfu.entity.FinanceAttributes;
import com.dbms.kungfu.entity.ProgressAttributes;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.dbms.kungfu.entity.StudentProgress;
import com.dbms.kungfu.entity.TimeTable;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author pavan
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;

	@Override
	@Transactional
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		
		List<Student> students=studentDao.getStudents();
		return students;
	}

	@Override
	@Transactional
	public Student saveStudent(Student theStudent,int rank, String level) {
		// TODO Auto-generated method stub
		
		Date currentDate = getCurrentDate();
		
		theStudent.setJoinDate(currentDate);
		
		theStudent = studentDao.saveStudent(theStudent,rank,level);
		
		return theStudent;
	}
	
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();

		Date currentDate = new Date(calendar.getTime().getTime());
		return currentDate;
	}

	@Override
	@Transactional
	public String recordAttendance(int studentId) {
		
		String today = getToday();
		int timeNow = getCurrentTime();

		List<TimeTable> todayTimeTable = studentDao.getTodayTimeTable(today);
		
		for(TimeTable schedule : todayTimeTable){

				
				String time=schedule.getTime();
				int fromTime=Integer.parseInt(time.substring(0,2));
				int toTime=Integer.parseInt(time.substring(6,time.length()-3));
				
				if(timeNow==fromTime || timeNow==toTime){
					
					Boolean checkDuplicateAttendance = studentDao.checkDuplicateAttendance(studentId,schedule.getId());
					
					if(!checkDuplicateAttendance){					
						studentDao.recordAttendance(studentId,schedule);
						return "Attendance Recorded";
					}else{
						return "Attendance already Recorded";
					}			
					
				}	
			
		}
		
		return "Out of Class hours";
		
		//studentDao.saveStudent(studentId);
		
	}
	
	public static String getToday(){
		Calendar c = Calendar.getInstance();
		String today=c.getTime().toString().substring(0,3);
		return today;
	}
	
	public static int getCurrentTime(){
		Calendar c = Calendar.getInstance();
		int timeNow=Integer.parseInt(c.getTime().toString().substring(11,13));
		return timeNow;
	}

	@Override
	@Transactional
	public List<Student> searchStudent(String searchString) {
		// TODO Auto-generated method stub
		
		List<Student> students= studentDao.searchStudent(searchString);
		
		return students;
	}

	@Override
	@Transactional
	public Student getStudentDetails(int studentId) {
		// TODO Auto-generated method stub
	
		Student student = studentDao.getStudentDetails(studentId);
		
		return student;
	}

	@Override
	@Transactional
	public String addFinance(int studentId, int serviceCatalogueId) {

		String result = studentDao.addFinance(studentId,serviceCatalogueId);
		
		return "Finance Added for: "+result;
	}

	@Override
	@Transactional
	public List<FinanceAttributes> getStudentAccountSummary(Student theStudent) {
		// TODO Auto-generated method stub
		
		List<AccountSummary> theAccountSummary = studentDao.getStudentAccountSummary(theStudent);
		
		List<FinanceAttributes> finances = new ArrayList<>();
		
		for (AccountSummary acc : theAccountSummary) {

			FinanceAttributes tmpFin = new FinanceAttributes();
			tmpFin.setStudentId(theStudent.getId());
			tmpFin.setCategory(acc.getServiceCatalogue().getCategory());
			tmpFin.setSubCategory(acc.getServiceCatalogue().getSubCategory());
			tmpFin.setFees(acc.getServiceCatalogue().getFees());
			tmpFin.setDatePaid(acc.getDatePaid());
			finances.add(tmpFin);

		}
		
		return finances;
	}

	@Override
	@Transactional
	public List<AttendanceAttributes> getStudentAttendanceSummary(Student theStudent) {
		// TODO Auto-generated method stub
		
		List<StudentAttendance> theStudentAttendance = studentDao.getStudentAttendanceSummary(theStudent);
		
		List<AttendanceAttributes> attendance = new ArrayList<>();
		
		for (StudentAttendance attend : theStudentAttendance) {

			AttendanceAttributes tmpAtt = new AttendanceAttributes();
			tmpAtt.setLevel(attend.getTimeTable().getLevel());
			tmpAtt.setRank(attend.getTimeTable().getRank());
			tmpAtt.setDay(attend.getTimeTable().getDay());
			tmpAtt.setTime(attend.getTimeTable().getTime());
			tmpAtt.setDateAttended(attend.getDateAttended());
			attendance.add(tmpAtt);
		}
		
		return attendance;
	}

	@Override
	public List<ProgressAttributes> getStudentProgressSummary(Student theStudent) {
		// TODO Auto-generated method stub
		
		List<StudentProgress> progress = theStudent.getStudentProgress();		
		
		List<ProgressAttributes> theProgress = new ArrayList<>();
		
		for (StudentProgress prog : progress) {
			ProgressAttributes tmpProg = new ProgressAttributes();
			tmpProg.setStudentId(theStudent.getId());
			tmpProg.setProgressId(prog.getId());
			tmpProg.setRank(prog.getRank().getBelt());
			tmpProg.setLevel(prog.getLevel());
			tmpProg.setDateAwarded(prog.getDateAwarded());
			theProgress.add(tmpProg);
		}
		
		
		return theProgress;
	}

	@Override
	@Transactional
	public String awardBelt(int studentId, int progressId) throws MySQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		
		String result = studentDao.awardBelt(studentId, progressId);

		return result;
	}

	@Override
	@Transactional
	public List<FinanceAttributes> searchFinanceRange(Student theStudent,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub

		List<AccountSummary> theAccountSummary = studentDao.searchFinanceRange(theStudent, fromDate, toDate);

		List<FinanceAttributes> finances = new ArrayList<>();

		for (AccountSummary acc : theAccountSummary) {

			FinanceAttributes tmpFin = new FinanceAttributes();
			tmpFin.setStudentId(theStudent.getId());
			tmpFin.setCategory(acc.getServiceCatalogue().getCategory());
			tmpFin.setSubCategory(acc.getServiceCatalogue().getSubCategory());
			tmpFin.setFees(acc.getServiceCatalogue().getFees());
			tmpFin.setDatePaid(acc.getDatePaid());
			finances.add(tmpFin);

		}

		return finances;

	}

	@Override
	public Boolean verifyAge(String dob) {
		// TODO Auto-generated method stub
		String date = dob.substring(0, 10);

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date newDate = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(newDate);

			int age = getAge(cal);

			if (age > 18) {
				return false;
			} else {
				return true;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	//returns age if provided a date
	public static int getAge(Calendar dob) throws Exception {

		Calendar today = Calendar.getInstance();

		int curYear = today.get(Calendar.YEAR);

		int dobYear = dob.get(Calendar.YEAR);

		int age = curYear - dobYear;

		// if dob is month or day is behind today's month or day

		// reduce age by 1

		int curMonth = today.get(Calendar.MONTH);

		int dobMonth = dob.get(Calendar.MONTH);

		if (dobMonth > curMonth) { // this year can't be counted!

			age--;

		} else if (dobMonth == curMonth) { // same month? check for day

			int curDay = today.get(Calendar.DAY_OF_MONTH);

			int dobDay = dob.get(Calendar.DAY_OF_MONTH);

			if (dobDay > curDay) { // this year can't be counted!

				age--;

			}

		}

		return age;

	}
		
		}



/**
 * 
 */
package com.dbms.kungfu.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbms.kungfu.dao.StudentDao;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.TimeTable;

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
	public void saveStudent(Student theStudent,int rank, String level) {
		// TODO Auto-generated method stub
		
		studentDao.saveStudent(theStudent,rank,level);
	}

	@Override
	@Transactional
	public String recordAttendance(int studentId) {
		
		String today = "Fri";//getToday();
		int timeNow = 9;//getCurrentTime();

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

}

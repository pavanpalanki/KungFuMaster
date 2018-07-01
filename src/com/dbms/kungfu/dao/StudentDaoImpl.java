package com.dbms.kungfu.dao;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.Rank;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.dbms.kungfu.entity.StudentProgress;
import com.dbms.kungfu.entity.TimeTable;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Student> theQuery = currentSession.createQuery("from Student order by firstName,lastName", Student.class);

		List<Student> students = theQuery.getResultList();

		return students;
	}

	@Override
	public Student saveStudent(Student theStudent, int rank, String level) {
		// TODO Auto-generated method stub

		//Date currentDate = getCurrentDate();

		Session currentSession = sessionFactory.getCurrentSession();

		Rank theRank = currentSession.get(Rank.class, rank);

		/*Student student = new Student(theStudent.getFirstName(), theStudent.getLastName(), theStudent.getDob(),
				currentDate, theStudent.getMobile(), theStudent.getEmail(), theStudent.getDoorNo(),
				theStudent.getStreet(), theStudent.getCity(), theStudent.getProvince(), theStudent.getPostalCode(),
				theStudent.getPrimaryContact(), theStudent.getPrimaryContactMobile(),
				theStudent.getPrimaryContactEmail(), theStudent.getSecondaryContact(),
				theStudent.getSecondaryContactMobile(), theStudent.getSecondaryContactEmail());*/
		
		if(theStudent.getId()==0){

		StudentProgress studentProgress = new StudentProgress(level);

		studentProgress.setRank(theRank);

		theStudent.addStudentProgress(studentProgress);

		currentSession.saveOrUpdate(studentProgress);
		
		
		}
		
		currentSession.saveOrUpdate(theStudent);
		
		return theStudent;
	}

	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();

		Date currentDate = new Date(calendar.getTime().getTime());
		return currentDate;
	}

	@Override
	public List<TimeTable> getTodayTimeTable(String today) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("from TimeTable t where t.day like :day");

		query.setParameter("day", "%"+today+"%");

		List<TimeTable> timeTable = query.getResultList();

		return timeTable;

	}

	@Override
	public void recordAttendance(int studentId, TimeTable schedule) {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();

		Student theStudent = currentSession.get(Student.class, studentId);

		StudentAttendance theStudentAttendance = new StudentAttendance(getCurrentDate());

		theStudentAttendance.setTimeTable(schedule);
		
		theStudent.addStudentAttendance(theStudentAttendance);

		currentSession.save(theStudentAttendance);

		currentSession.save(theStudent);

	}

	@Override
	public List<Student> searchStudent(String searchString) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("from Student where firstName like :search "+
				" OR lastName like :search "+
				" OR mobile like :search "+
				" OR email like :search "+
				" OR city like :search "+
				" OR province like :search "+
				" OR postalCode like :search "+
				" OR dob like :search "+
				" OR joinDate like :search order by firstName,lastName"
				);

		query.setParameter("search", "%"+searchString+"%");
		
		List<Student> students = query.getResultList();
		
		return students;
		
	}

	@Override
	public Boolean checkDuplicateAttendance(int studentId, int timeTableId) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Student theStudent = currentSession.get(Student.class, studentId);
		
		TimeTable theTimeTable = currentSession.get(TimeTable.class, timeTableId);
		
		Query query = currentSession.createQuery("from StudentAttendance sa where sa.student like :studentId "
				+ " and sa.timeTable like :timeTableId and dateAttended like :currentDate");

		query.setParameter("studentId", theStudent);
		query.setParameter("timeTableId", theTimeTable);
		query.setParameter("currentDate", getCurrentDate());

		List<StudentAttendance> studentAttendance = query.getResultList();
		
		if(studentAttendance.isEmpty())		
			return false;		
		else
			return true;
	}

	@Override
	public Student getStudentDetails(int studentId) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Student student = currentSession.get(Student.class, studentId);
		
		return student;
	}

	@Override
	public String addFinance(int studentId, int serviceCatalogueId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Student theStudent = currentSession.get(Student.class, studentId);
		
		ServiceCatalogue theServiceCatalogue = currentSession.get(ServiceCatalogue.class, serviceCatalogueId);
		
		AccountSummary accountSummary = new AccountSummary(getCurrentDate());
		
		accountSummary.setServiceCatalogue(theServiceCatalogue);
		
		theStudent.addAccountSummary(accountSummary);
		
		currentSession.save(accountSummary);
		
		currentSession.save(theStudent);
		
		return theStudent.getFirstName()+" "+theStudent.getLastName();
	}

	@Override
	public List<AccountSummary> getStudentAccountSummary(Student theStudent) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("from AccountSummary where studentInfo = :student ");
		
		query.setParameter("student", theStudent);
		
		List<AccountSummary> accounts = query.getResultList();
		
		return accounts;
	}

	@Override
	public List<StudentAttendance> getStudentAttendanceSummary(Student theStudent) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("from StudentAttendance sa where sa.student = :student ");
		
		query.setParameter("student", theStudent);
		
		List<StudentAttendance> attendance = query.getResultList();

		return attendance;
	}

	@Override
	public String awardBelt(int studentId, int progressId)
			throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();

		StudentProgress theProgress = currentSession.get(StudentProgress.class, progressId);

		theProgress.setDateAwarded(getCurrentDate());

		theProgress.setLevel("Advanced");

		int rankId = theProgress.getRank().getId() + 1;

		if (rankId < 10) {

			Student theStudent = currentSession.get(Student.class, studentId);

			StudentProgress newProgress = new StudentProgress("Beginner");

			Rank newRank = currentSession.get(Rank.class, theProgress.getRank().getId() + 1);
			newProgress.setRank(newRank);
			theStudent.addStudentProgress(newProgress);

			currentSession.save(newProgress);

			currentSession.save(theStudent);

		}

		return "Rank Awarded";

	}

	@Override
	public List<AccountSummary> searchFinanceRange(Student theStudent, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub

		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("from AccountSummary where studentInfo = :student and "
				+ "	datePaid >= :fromDate and datePaid <= :toDate ");

		query.setParameter("student", theStudent);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		List<AccountSummary> accounts = query.getResultList();

		return accounts;
	}

}

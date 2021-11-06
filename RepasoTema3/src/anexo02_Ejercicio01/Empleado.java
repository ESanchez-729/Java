package anexo02_Ejercicio01;

import java.sql.Date;

public class Empleado {
	
	private int emp_no;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private double sal;
	private double comm;
	private int dept_no;
	
	
	
	public Empleado() {}



	public Empleado(int emp_no, String ename, String job, int mgr, Date hiredate, double sal, double comm, int dept_no) {
		
		this.emp_no = emp_no;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.dept_no = dept_no;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	@Override
	public String toString() {
		return "Empleado [emp_no=" + emp_no + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate="
				+ hiredate + ", sal=" + sal + ", comm=" + comm + ", dept_no=" + dept_no + "]";
	}

}

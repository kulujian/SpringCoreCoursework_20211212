package pers.kulujian.coursework.coursework_3.entity;

import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Emp {
	// 欄位
	private Integer eid;
	private String ename;
	private Integer age;
	private Date createtime;
	
	// 關係
	private List<Job> job;  //一個 emp 對應多個 job
	
	
	public Emp() {
//		super();
	}
	
	
	public Emp(String ename, Integer age) {
		super();
		this.ename = ename;
		this.age = age;
	}


	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Job> getJobs() {
		return job;
	}

	public void setJobs(List<Job> job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", age=" + age + ", createtime=" + createtime + ", job=" + 
				(job == null ? "" : job.stream().map(Job::getJname).collect(toList())) 
				+ "]"; //因為import為static 所以可以直接調用toList()


	}
	
	
	

}

package pers.kulujian.coursework.coursework_3.template;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;


//@Repository
@Component
public class EmpDao_homework {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// 多筆查詢 1
	public List<Map<String, Object>> queryAll(){
		
		String sql = "select eid, ename, age, createtime from emp";
		List<Map<String, Object>> emps = jdbcTemplate.queryForList(sql);
		return emps;
	}
	
	public String searchNameById(String Id) {
		try {
			String ename = queryAll().stream()
					.filter(e->(e.get("eid")+"").equals(Id))	//Map取得資料要使用get()。Map是Object要轉字串
					.findFirst()
					.get()		// 取得所有資料(eid、ename、age、createtime)之後還是 Map(Object)
					.get("ename")+"";	// Map取得資料要使用get()。取得後要在轉成字串，才會正常顯示
			return ename;
		} catch (Exception e2) {
			return "undefine";
		}
	}
	
	// HomeWork
		// 單筆新增 1
		public int addLog(String method_name) {
			String sql = "insert into log(method_name) values(?)";
			int rowcount = jdbcTemplate.update(sql, method_name);
			return rowcount;
		}
		// 多筆查詢 1
		public List<Map<String, Object>> showLog(){
			
			String sql = "select * from log";
			List<Map<String, Object>> logs = jdbcTemplate.queryForList(sql);
			return logs;
		}
	// HomeWork
	
	
	// 修改
	public int updateByid(Integer eid, String ename, Integer age) {
		String sql = "update emp set ename=?, age=? where eid=?";
		return jdbcTemplate.update(sql,ename, age, eid);
	}
	
	// 刪除
	public int deleteById(Integer eid) {
		String sql = "delete from emp where eid=?";
		return jdbcTemplate.update(sql, eid);
	}
}

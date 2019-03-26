package com.hsh.struts2;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hsh.dao.ProjectDao;
import com.hsh.entity.Project1;

public class HelloWorldAction{
	   private String name;

	   public String execute() throws Exception {
		  SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  Project1 p = new Project1();
		  p.setpName("test2");
		  p.setCreate(fmt.format(new Date()));
		  p.setCreateBy(1);
		  p.setHeadId(2);
		  p.setModifyTime(fmt.format(new Date()));
		  p.setStatus("start");
		  System.out.println(p.getCreate());
		  ProjectDao pd = new ProjectDao();
		  pd.insert(p);
	      return "success";
	   }
	   
	   public String getName() {
	      return name;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }
	}
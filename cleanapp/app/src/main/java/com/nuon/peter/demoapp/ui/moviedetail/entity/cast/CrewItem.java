package com.nuon.peter.demoapp.ui.moviedetail.entity.cast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewItem{

	@SerializedName("credit_id")
	@Expose
	private String creditId;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("profile_path")
	@Expose
	private String profilePath;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("department")
	@Expose
	private String department;

	@SerializedName("job")
	@Expose
	private String job;

	public void setCreditId(String creditId){
		this.creditId = creditId;
	}

	public String getCreditId(){
		return creditId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getDepartment(){
		return department;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}
}
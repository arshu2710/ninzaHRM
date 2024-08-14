
package com.ninza.hrm.api.baseClass;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification respRespObj;

	@BeforeSuite
	public void configBS() throws SQLException, IOException {
		dbLib.getDbconnection();
		System.out.println("==Connect to db====");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
	//	builder.setAuth(basic("username", "password"));
		builder.addHeader("", "");
		builder.setBaseUri(fLib.getDataFromPropertiesFile("BASEUri"));
		specReqObj = builder.build();
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		respRespObj = resBuilder.build();
	}

	@AfterSuite
	public void configAS() {
		
		dbLib.closeDbconnection();
		System.out.println("======Disconnectdb=====");
	}
}

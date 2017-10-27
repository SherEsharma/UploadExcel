package com.xenture.test;

import java.io.*;
/*
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
*/

import org.apache.poi.openxml4j.opc.OPCPackage;

/*
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
*/
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.xenture.connection.DConnection;

public class ExcelUploadTest {

	static Connection con;
	static PreparedStatement pstmt;

	public static void main(String[] args) throws Exception {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			PreparedStatement sql_statement = null;

			FileInputStream input = new FileInputStream("src/Test.xlsx");
			OPCPackage pkg = OPCPackage.open(input);
			XSSFWorkbook wb = new XSSFWorkbook(pkg);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();

			String sql = "insert into emp_details values(?,?)";
			con = (Connection) DConnection.getConnection();
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			int i=0;
			for ( i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow XSSFRow = sheet.getRow(i);
				XSSFRow row = XSSFRow;

				String firstName = row.getCell(0).getStringCellValue();
				String lastName = row.getCell(1).getStringCellValue();

				pstmt.setString(1, firstName);
				pstmt.setString(2, lastName);
				pstmt.executeUpdate();
				System.out.println(firstName + "\t" + lastName);
			}
			System.out.println("Import rows " + i);
			con.close();
			input.close();
			System.out.println("Success import excel to mysql table");
//shubham
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
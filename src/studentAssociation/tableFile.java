package studentAssociation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class tableFile {
	int recordNum = 0;
	final int primaryKey = 0;
	final int maxStudentNum = 100;
	String dataFilePath = "data";
	String dataFileName;
	String lineRecord;
	String[][] data;
	File dataFile;
	FileInputStream fis;
	FileOutputStream fos;
	BufferedReader br;
	BufferedWriter bw;
	InputStreamReader isr;
	OutputStreamWriter osw;
	
	

	public void readData() {

		if(dataFile.exists()){
			try {
				fis = new FileInputStream(dataFile);
				isr = new InputStreamReader(fis,"GBK");
				br = new BufferedReader(isr);
				
				
				//!!!!!!!!!!!!!!!!!!!!! clear before
				int tempNum = 0;
				String[][] tempData = new String[maxStudentNum][];
				while((lineRecord = br.readLine()) != null) {
					String[] record = lineRecord.split(" ");
					tempData[tempNum++] = record;
				}
				
				recordNum = tempNum;
				data = tempData;
				
				br.close();
				isr.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("new a file");
			try {
				fos = new FileOutputStream(dataFile);
				osw = new OutputStreamWriter(fos,"GBK");
				bw = new BufferedWriter(osw);
				
				
				bw.close();
				osw.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void writeData() {
		try {
			fos = new FileOutputStream(dataFile);
			osw = new OutputStreamWriter(fos,"GBK");
			bw = new BufferedWriter(osw);

			this.orderByPk();
			
			for(int line = 0; line<recordNum; line++) {
				for(String value:data[line]) {
					bw.write(value + " ");
				}
				bw.write("\r\n");
			}
			
			
			bw.close();
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	///²åÈëÓÅ»¯£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡
	boolean addData(String newRecordValue ) {
		String[] newRecord = newRecordValue.split(" ");
		try {			
			data[recordNum++] = newRecord;		
			orderByPk();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	boolean deleteData(String primaryKeyValue) {
		int resultPos = getDataPos(primaryKeyValue);
		if(resultPos >= 0) {
			for(int row = resultPos; row < recordNum-1; row++) {
				data[row] = data[row+1];
			}
			recordNum--;
			return true;
		}else {
			System.out.println(primaryKeyValue + " É¾³ýÊ§°Ü£¡");
			return false;
		}
		
	}
	
	//simply bubbleSort
	boolean setData(String newRecordValue ){
		String[] newRecord = newRecordValue.split(" ");
		String primaryKeyValue = newRecord[primaryKey];
		
		int resultPos = getDataPos(primaryKeyValue);
		
		if(resultPos >= 0) {
			data[resultPos] = newRecord;
			return true;
		}else {
			System.out.println("set failed");
			return false;
		}
	}

	int getDataPos(String primaryKeyValue){
		int low = 0,high = recordNum-1; 
		
		while(low <= high ){
			int mid = (low+high)/2;
			if(data[mid][primaryKey].compareTo( primaryKeyValue) == 0){
				return mid;
			}else if(data[mid][primaryKey].compareTo( primaryKeyValue) > 0){
				high = mid -1;
			}else {
				low = mid +1;
			}
		}
		return -1;
	}
	
	
	

	
	// !~~~~~~~~~~~~~~~~~~~~~no use
//	boolean searchData(String keyWord){
//		//reWrite to student
////		Pattern pattern = Pattern.compile("[0-9]*");
////		Matcher isNum = pattern.matcher(keyWord);
////		if(isNum.matches()){
////			
////		}
//		int resultPos = getDataPos(keyWord);
//		if(resultPos >= 0) {
////			System.out.println(data[]);
//			for(String value:data[resultPos]){
//				System.out.print(value + " ");
//			}
//			return true;
//		}else {
//			System.out.println("search failed");
//			return false;
//		}
//		
//		
//	}
//	
	int getRecordNum() {
		return recordNum;
	}
	String[] getRecordByPk(String primaryKeyValue){
		int resultPos = this.getDataPos(primaryKeyValue);
		return data[resultPos];
	}
	String[] getRecordByPpos(int pos){
		return data[pos];
	}
	
	String[][] getData(){
		return data;
	}
	
	void updataRecord(int pos, String[] newRecord){
		data[pos] = newRecord;
	}
	
	int searchData(String keyWord){
		//reWrite to student
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(keyWord);
		if(isNum.matches()){
			int resultPos = getDataPos(keyWord);
			if(resultPos >= 0) {
				return resultPos;
			}else {
				System.out.println("search failed");
				return -1;
			}
		}else {
			for(int i = 0; i<recordNum; i++){
				if(data[i][1].compareTo( keyWord ) == 0){
//					
//					for(String value:data[i]){
//						System.out.print(value + " ");
//					}
					
					return i;
				}
			}
			return -1;
		}
		
		
	}
	
	
	
	private void orderByPk(){
//		System.out.println(data[2][0].compareTo(data[1][0])); 
		for(int row = 0; row <recordNum; row++){
			for(int idIndex = row; idIndex < recordNum; idIndex ++){
				if(data[idIndex][primaryKey].compareTo(data[row][primaryKey]) < 0 ){
					String[] temp = data[row];
					data[row] = data[idIndex];
					data[idIndex] = temp;
				}
			}
		}
	}
	
	void showData() {
		
		this.orderByPk();

		for(int line = 0; line<recordNum; line++) {
//			System.out.println(data[line].length + " ");
			for(String value:data[line]) {
				System.out.print(value + " ");
			}
			System.out.println();
		}
	}

	
	
//	abstract void readData();
//	abstract void writeData();
//	abstract void closeFile();
//	abstract void orderByPrimaryKey(int PrimaryKey);
//	abstract void setData(String data);
//	abstract void addData(String data);
//	abstract void deleteData(String data);
//	abstract void searchData(String info);
//	abstract void searchData(String info,String method);
	
}

package studentAssociation;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class studentTable extends tableFile{
	studentTable(){
		data = new String[maxStudentNum][];
		dataFileName = "studentTable.txt";
		dataFile = new File(dataFilePath,dataFileName);
	}
	String getNameByPk(String primaryKeyValue){
		int resultPos = this.getDataPos(primaryKeyValue);
		if(resultPos >= 0){
			String[] resultData = data[resultPos];
			return resultData[1];
		}else{
			System.out.println("get name failed");
			return null;
		}
	}
	
//
//	boolean searchData(String keyWord){
//		//reWrite to student
//		Pattern pattern = Pattern.compile("[0-9]*");
//		Matcher isNum = pattern.matcher(keyWord);
//		if(isNum.matches()){
//			int resultPos = getDataPos(keyWord);
//			if(resultPos >= 0) {
////				System.out.println(data[]);
//				for(String value:data[resultPos]){
//					System.out.print(value + " ");
//				}
//				return true;
//			}else {
//				System.out.println("search failed");
//				return false;
//			}
//		}else {
//			int low = 0,high = recordNum-1; 
//			
//			while(low <= high ){
//				int mid = (low+high)/2;
//				if(data[mid][1].compareTo( keyWord ) == 0){
//					
//					for(String value:data[mid]){
//						System.out.print(value + " ");
//					}
//					
//					return true;
//				}else if(data[mid][1].compareTo( keyWord ) > 0){
//					high = mid -1;
//				}else {
//					low = mid +1;
//				}
//			}
////		
//			return false;
//		}
//		
//		
//	}
//	
	
	
	
//	
//	public void readData() {
//
//		if(dataFile.exists()){
//			try {
//				fis = new FileInputStream(dataFile);
//				isr = new InputStreamReader(fis,"UTF-8");
//				br = new BufferedReader(isr);
//				
//				
//				//!!!!!!!!!!!!!!!!!!!!! clear before
//				int tempStuNum = 0;
//				String[][] tempData = new String[maxStudentNum][];
//				while((lineRecord = br.readLine()) != null) {
//					String[] record = lineRecord.split(" ");
//					tempData[tempStuNum++] = record;
//				}
//				
//				recordNum = tempStuNum;
//				data = tempData;
//				
////				data[1][1] = "sb";
//				br.close();
//				isr.close();
//				fis.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else {
//			System.out.println("no Files");
//		}
//			
//	}
//	
//	public void writeData() {
//		try {
//			fos = new FileOutputStream(dataFile);
//			osw = new OutputStreamWriter(fos,"UTF-8");
//			bw = new BufferedWriter(osw);
//
//			this.orderByPk();
//			
//			for(int line = 0; line<recordNum; line++) {
//				for(String value:data[line]) {
//					bw.write(value + " ");
//				}
//				bw.write("\r\n");
//			}
//			
////			bw.write("201503164338 zxx f 2015 computer");
////			bw.write(" \r\n");
////			bw.write("201503164312 xxm m 2016 mc ");
////			bw.write(" \r\n");
////			bw.write("201503164124 lgc f 2015 car ");
//			
//			bw.close();
//			osw.close();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	//simply bubbleSort
//	boolean setData(String newRecordValue ){
//		String[] newRecord = newRecordValue.split(" ");
//		String primaryKeyValue = newRecord[primaryKey];
//		
//		int recordPos = searchData(primaryKeyValue);
//		
//		if(recordPos >= 0) {
//			data[recordPos] = newRecord;
//			return true;
//		}else {
//			System.out.println("set failed");
//			return false;
//		}
//	}
//	
//	boolean addData(String newRecordValue ) {
//		String[] newRecord = newRecordValue.split(" ");
//		try {			
//			data[recordNum++] = newRecord;		
//			orderByPk();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;		
//	}
//	
//	boolean deleteData(String primaryKeyValue) {
//		int recordPos = searchData(primaryKeyValue);
//		if(recordPos >= 0) {
////			data[recordPos] = newRecord;
//			for(int row = recordPos; row < recordNum-1; row++) {
//				data[row] = data[row+1];
//			}
//			recordNum--;
//			return true;
//		}else {
//			System.out.println("delete failed");
//			return false;
//		}
//		
//	}
//
//	private void orderByPk(){
////		System.out.println(data[2][0].compareTo(data[1][0])); 
//		for(int row = 0; row <recordNum; row++){
//			for(int idIndex = row; idIndex < recordNum; idIndex ++){
//				if(data[idIndex][primaryKey].compareTo(data[row][primaryKey]) < 0 ){
//					String[] temp = data[row];
//					data[row] = data[idIndex];
//					data[idIndex] = temp;
//				}
//			}
//		}
//	}
//	
//	int searchData(String primaryKeyValue){
//		int low = 0,high = recordNum-1; 
//		
//		while(low <= high ){
//			int mid = (low+high)/2;
//			if(data[mid][primaryKey].compareTo( primaryKeyValue) == 0){
//				return mid;
//			}else if(data[mid][primaryKey].compareTo( primaryKeyValue) > 0){
//				high = mid -1;
//			}else {
//				low = mid +1;
//			}
//		}
//		return -1;
//	}
//	
//	void showData() {
//		
//		this.orderByPk();
//
//		for(int line = 0; line<recordNum; line++) {
//			for(String value:data[line]) {
//				System.out.print(value + " ");
//			}
//			System.out.println();
//		}
//	}

}
/*
 * 
//		for(int row = 0; row < stuNum; row ++){
//			if(data[row][primaryKey].equals( newRecord[primaryKey] )){
//				data[row] = newRecord;
//				return true;
//			}
//		}
//		
//		
//		
//		return false;
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
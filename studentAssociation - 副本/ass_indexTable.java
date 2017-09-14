package studentAssociation;

import java.io.File;

public class ass_indexTable extends tableFile{
	ass_indexTable(){
		data = new String[maxStudentNum][];
		dataFileName = "ass_indexTable.txt";// name stuNames
		dataFile = new File(dataFilePath,dataFileName);
	}
	
	
}

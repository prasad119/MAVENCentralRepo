package com.sample.nga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class Test {
	static String expectedPDF = "C:\\Users\\sailakshmipu\\Downloads\\_20161103_094018.pdf";
	static String actualPDF = "C:\\Users\\sailakshmipu\\Downloads\\_20161103_094018.pdf";
	static String expTxt = "D:\\Expected.txt";
	static String actTxt = "D:\\Actual.txt";


	public static void main(String a[]){		
		createNewTxtFile(expTxt);
		createNewTxtFile(actTxt);
		String actPDFData = pdfReader(actualPDF);
		String expPDFData = pdfReader(expectedPDF);
		wirteToTxt(expPDFData,expTxt);
		wirteToTxt(actPDFData,actTxt);		
		boolean status = textFilesCompare(expTxt,actTxt,false);
		System.out.println(status);
		deleteFile(actTxt);
		deleteFile(expTxt);
	}	
	//	Creates new text file
	public static void createNewTxtFile(String txtFilePath){
		try{
			File expFile = new File(txtFilePath);
			if(!expFile.exists()){
				expFile.createNewFile();
				System.out.println("New file created");
			}
			else{
				if(expFile.delete()){
					expFile.createNewFile();
					System.out.println("File deleted and new one created");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	//Counts number of lines in a txt file
	public static int linenumberReader(String fileToReadLineNumbers){
		try{
			LineNumberReader lr = new LineNumberReader(new FileReader(new File(fileToReadLineNumbers)));
			lr.skip(Long.MAX_VALUE);
			int i = lr.getLineNumber()+1;
			lr.close();
			return i;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	//Compared two txt files
	public static boolean textFilesCompare(String file1, String file2, boolean compareLinestatus){
		try{
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			String x = "";
			String y = "";
			int i = 0;
			FileReader fr1 = new FileReader(new File(file1));
			FileReader fr2 = new FileReader(new File(file2));
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
			if(compareLinestatus){
				if(linenumberReader(file1)!=linenumberReader(file2)){
					System.out.println("The files are not equal in lines count");
									
				}
			}
			while((x = br1.readLine())!=null){
				list1.add(x);
			}
			while((y = br2.readLine())!=null){
				list2.add(y);
			}
			int ret = compareTwoLists(list1,list2);
			if(ret==-1){
				return false;
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//Compares two lists
	public static int compareTwoLists(List<String> actualList, List<String> expectedList) throws Exception {
		if (actualList.size() == expectedList.size()) {
			for (int counter = 0; counter < expectedList.size(); counter++) {
				if (!actualList.get(counter).equalsIgnoreCase(expectedList.get(counter)))
					return -1;
			}
		} else
			return -1;
		return 0;
	}

	public static String pdfReader(String pdfFile){
		try{
			File f = new File(pdfFile);
			PDDocument pdf = PDDocument.load(f);
			PDFTextStripper stripper = new PDFTextStripper();
			String plainText = stripper.getText(pdf);
			return plainText;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	//Writes the given data to text files
	public static void wirteToTxt(String string, String textFile){
		try {	

			File f = new File(textFile);
			FileWriter fw = new FileWriter(f);
			fw.write(string);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteFile(String filePath){
		try{
			File f = new File(filePath);
			f.delete();
			System.out.println("File deleted");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}



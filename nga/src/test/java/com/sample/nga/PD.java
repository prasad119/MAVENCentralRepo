package com.sample.nga;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PD {
	public static void main(String g[]) throws IOException{
		String f1 = "C:\\Users\\sailakshmipu\\Downloads\\_20161103_094018.pdf";
		String f2 = "C:\\Users\\sailakshmipu\\Downloads\\_20161108_102115.pdf";
		String p1 = pdfReader(f1);
		String p2 = pdfReader(f2);
		if(p1.equalsIgnoreCase(p2)){
			System.out.println("Equal");
		}
		else{
			System.out.println("Not equal");
		}

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

}

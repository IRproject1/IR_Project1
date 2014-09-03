/**
 * 
 */
package edu.buffalo.cse.irf14.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

/**
 * @author nikhillo
 * Class that parses a given file into a Document
 */
public class Parser {
	/**
	 * Static method to parse the given file into the Document object
	 * @param filename : The fully qualified filename to be parsed
	 * @return The parsed and fully loaded Document object
	 * @throws ParserException In case any error occurs during parsing
	 */
	public static Document parse(String filename) throws ParserException {
		// TODO YOU MUST IMPLEMENT THIS
		File f = new File(filename);
		String fileName = f.getName();
		String parentDirPath = f.getParent();
		String category = parentDirPath.substring(parentDirPath.lastIndexOf('\\')+1);
		System.out.println(category);
		Document document= new Document();		
		
		document.setField(FieldNames.FILEID, fileName);
		document.setField(FieldNames.CATEGORY, category);
//		boolean exists = f.exists();
//		boolean canRead = f.canRead();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			// first 2 lines in the file is empty			
			br.readLine();
			br.readLine();
			
			String title = ParseTitle(br);
			document.setField(FieldNames.TITLE, title);	
			
			String author = ParseAuthorField(br);
			document.setField(FieldNames.AUTHOR, author);
			
			String place = ParsePlace(br);
			document.setField(FieldNames.PLACE, place);			
			
			String date = ParseDate(br);
			document.setField(FieldNames.NEWSDATE, date);
			
			String content = ParseContent(br);
			document.setField(FieldNames.CONTENT, content);
			
			br.close();
		} 	catch (Exception e) 
			{	
				e.printStackTrace();
			}	
		
		return null;
	}
	
	private static String ParseAuthorField(BufferedReader br)
	{
		return null;		
	}
	
	private static String ParseTitle(BufferedReader br)
	{
		try 
		{
			return br.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String ParseDate(BufferedReader br)
	{
		char c;
		StringBuilder date = new StringBuilder();
		
		try
		{
			while( (c = (char) br.read()) != '-')
			{
				date.append(c);
			}
		}
		catch(Exception e)
		{
			
		}
		
		return date.toString().trim();
	}
	
	private static String ParsePlace(BufferedReader br)
	{		
		char i;
		StringBuilder Place = new StringBuilder();
		
		try
		{
			br.read();
			while( (i = (char)br.read()) == ' ')
			{
				
			}						
			
			do
			{
				Place.append(i);
			}while((i = (char)br.read()) != ',');
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return Place.toString();
	}
	
	private static String ParseContent(BufferedReader br)
	{
		StringBuilder content = new StringBuilder();
		String l;
		
		try
		{
			while((l = br.readLine()) != null)
			{
				content.append(l);
			}
		}
		catch(Exception e)
		{
			
		}
		
		return content.toString().trim();
	}
}

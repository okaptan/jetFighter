package MyFighter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Files {
	static BufferedWriter writer;
	FileReader reader;
	static int count = 0;
	static FileInputStream fstream;	
	static DataInputStream in;
	static BufferedReader br;
	
	
	public static void save(String name, String password) {
		try (Writer writer = new BufferedWriter(new FileWriter("Users.txt", true))) {
			Scanner scan = new Scanner(new File("Users.txt"));
			if(scan.hasNextLine()) {
				((BufferedWriter) writer).newLine();	
			}
			writer.write(name);
			((BufferedWriter) writer).newLine();
			writer.write(password);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkName(String Str) {
		try {
			String str;
			fstream = new FileInputStream("Users.txt");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			while ((str = br.readLine()) != null)   {
				if(count % 2 == 0) {
					if(str.equals(Str)) {
						return false;
					}
				}
				count ++;
			}
			count = 0;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	
	}
	
	public static boolean search(String name, String password) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("Users.txt"));

		while(scan.hasNext()){ 
		     if(scan.nextLine().equals(name)){
		          if(scan.nextLine().equals(password)){
		                   JOptionPane.showMessageDialog(null, "Log in successful", "Log in Successful", JOptionPane.INFORMATION_MESSAGE);
		                   return true;
		          }
		          else{
		        	  JOptionPane.showMessageDialog(null, "Log in unsuccessful", "Log in unsuccessful", JOptionPane.ERROR_MESSAGE);
		          }
		     }
		}
		return false;
	
	}
	
	public static void highScoreWrite(String name, int score) {
		try (Writer writer = new BufferedWriter(new FileWriter("HighScores.txt", true))) {
			Scanner scan = new Scanner(new File("HighScores.txt"));
			if(scan.hasNextLine()) {
				((BufferedWriter) writer).newLine();	
			}
			writer.write(name);
			((BufferedWriter) writer).newLine();
			String sscore = String.valueOf(score);
			writer.write(sscore);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	
}

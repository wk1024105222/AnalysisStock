package wk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AyGxzFile {

	
	public static void main(String[] args) throws FileNotFoundException {
//		File sour = new File("062852015062722300200.ret");
//		List<String> list = AyGxzFile.getListFromFile(sour);
//		PrintWriter pWriter = new PrintWriter(new File(sour.getName().substring(0, 21)+".tmp"));
//		for(String a : list){
//
//			byte[] tmp = a.getBytes();
//			tmp[498]=48;
//			pWriter.println(new String(tmp));
//		}
//		pWriter.flush();
//		pWriter.close();
		
		File sour = new File("d:\\all.txt");
		List<String> list = AyGxzFile.getListFromFile(sour);
		List<Txn> ts = new ArrayList<Txn>();
		for(String a : list ) {
			byte[] tmp = a.getBytes();
			Txn t = new Txn(new String(Arrays.copyOfRange(tmp, 0, 8)),new String(Arrays.copyOfRange(tmp, 65, 79)));
			ts.add(t);
			//System.out.println(t);
		} 
		
		for(int i=0; i!=ts.size(); i++) {
			for(int j=i+1; j!=ts.size(); j++) {
				if(ts.get(i).equals(ts.get(j))) {
					System.out.println(i+"\t"+ts.get(i));
				}
			}
		}
		System.out.println("½áÊø");

	}
	
	
	
	public static ArrayList<String> getListFromFile(File file) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

}

class Txn{
	public String termid;
	public String date;
	public Txn(String termid, String date) {

		super();
		this.termid = termid;
		this.date = date;
	}
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((termid == null) ? 0 : termid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {

		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Txn other = (Txn) obj;
		
		return termid.equals(other.termid) && date.equals(other.date);
	}
	@Override
	public String toString() {

		return "Txn [date=" + date + ", termid=" + termid + "]";
	}
	
	
	
	
}

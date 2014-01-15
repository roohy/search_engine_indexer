package me.roohy.search.indexer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void index(String path ){
		
		 TotalSearchClass searchEngine = new TotalSearchClass();
//		 InitializationParameters initParams = new InitializationParameters(false, false);
		 searchEngine.init();
		//
		 String json = new String();
		 try{
			  json = readFile(path, Charset.defaultCharset());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 searchEngine.addOrUpdateDocuments(json);
	}
	public static void search(String path , String q){
		TotalSearchClass searchEngine = new TotalSearchClass();
		searchEngine.init(path);
		
		SearchQuery testquery = new SearchQuery(null,
				 null, null, q, null, null);
		 testquery.fromDate = 1386837481;
		 testquery.toDate = 1386837481;
		 searchEngine.searchFor(testquery); 
//		 SearchResult s1 = searchEngine.searchFor(someQuery);
		// searchEngine.addOrUpdateDocuments("Another JSON-formatted document collection");
		// SearchResult s2 = searchEngine.searchFor(anotherQuery);
		// run lots of tests, and evaluate them
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
			}
	public static void main(String[] args)throws Exception {
		//checking to see if it is an index or search 
		if(args.length <= 0 ){
			System.out.println("error error, arguments");
			return;
		}
		if( args[0].contains("index")){
			if( args.length <= 1){
				System.out.println("error error, arguments to short to index");
				return;
			}
			else{
				index(args[1]);
			}
		}
		else if(args[0].contains("search")){
			if( args.length <= 2){
				System.out.println("error error, arguments to short to index");
				return;
			}
			else{
				String query = "";
				for(int i = 2 ; i<args.length ; i++){
					query = query + args[i]+ " "; 
					
				}
				search(args[1], query);
			}
		}
		else{
			System.out.println("Error error, no such command available!!!");
			for ( String st: args){
				System.out.println(st);
			}
		}
		 /*
		*/
		
	}
}

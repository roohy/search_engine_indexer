package me.roohy.search.indexer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Searcher {
//	private static IndexSearcher searcher;	
	
	public static List<Integer> search(String indexDir , SearchQuery searchQuery)throws Exception{
		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);

		// making the query string 
		String queryString = "(";
		
		if( searchQuery.getUrl() != null && searchQuery.getUrl() != ""){
			queryString +="body:"+searchQuery.getUrl()+" ";
		}
		if( searchQuery.getQ() != null && searchQuery.getQ() != ""){
			queryString +="body:"+searchQuery.getQ()+" title:"+
					searchQuery.getQ()+" ";
		}
		
		
		//from now there are boolean rules
		queryString += ")";
		BooleanQuery finalQuery = new BooleanQuery();
		/*		if( searchQuery.getViews() != null && searchQuery.getViews() != 0){
			Query viewQuery = NumericRangeQuery.newIntRange("score", 2, null, true, true);
			finalQuery.add(viewQuery,Occur.MUST);
		}
		
//		Query q1 = new TermRangeQuery("views", "2", "100", false, false);
		Query q2 = new TermQuery(new Term("views", "32"));

		
		finalQuery.add(qKOS,Occur.SHOULD);
	*/	
		
		
		
		
		if( searchQuery.getAccepted() != null ){
			queryString+= "AND is_answered:"+String.valueOf(searchQuery.getAccepted());
		}
		
		
		QueryParser parser = new QueryParser(Version.LUCENE_30, "body"
				, new StandardAnalyzer(Version.LUCENE_30));
		Query oquery = parser.parse(queryString);
		finalQuery.add(oquery,Occur.SHOULD);
		long start = System.currentTimeMillis();
		TopDocs hits = searcher.search(oquery, 10);
		long end = System.currentTimeMillis();
		
		/*Term term = new Term("body","is");
		Query q = new TermQuery(term);
		TopDocs hits = searcher.search(q, 10);*/
		List<Integer> result = new ArrayList<Integer>();
		System.err.println("Found "+hits.totalHits +" document(s) (in "/*+(end-start)*/
				+"milliseconds) that matched query '"+queryString+"':");
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = searcher.doc(scoreDoc.doc);
			result.add(Integer.parseInt(doc.get("serialNumber")));
			System.out.println(doc.get("title"));
		}
		searcher.close();
		return result;
	}
	
}

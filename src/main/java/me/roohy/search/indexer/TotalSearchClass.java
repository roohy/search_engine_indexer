package me.roohy.search.indexer;

import java.util.List;

import com.google.gson.Gson;



public class TotalSearchClass {

	Indexer indexer;
	String indexDir;
//	@Override
	public void init() {
		// TODO Auto-generated method stub
		indexDir = "indexDIR";
		try{
				indexer = new Indexer(indexDir,true);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init(String path){
		indexDir = path;
		try{
			indexer = new Indexer(indexDir,true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return;
	}
	public static SearchItemClass Json2Doc(String json){

		Gson gson = new Gson();
		SearchItemClass items = gson.fromJson(json, SearchItemClass.class);
//		System.out.println("the string json "+items.content.get(0).title);
		return items;
	}

//	@Override
	public void addOrUpdateDocuments(String documents) {
		// TODO Auto-generated method stub

//		System.out.println("the string json "+ documents);
		SearchItemClass items = Json2Doc(documents);
//		System.out.println("the string json "+items.content.get(0).title);
		try{
			indexer.indexItems(items);
			indexer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//	@Override
	public List<Integer> searchFor(SearchQuery query) {
		// TODO Auto-generated method stub
		List<Integer> result = null;
		try{
//			System.out.println("searching for "+query.getQ());
			result = Searcher.search(indexDir, query);
//			System.out.println("haha directory for  search "+indexDir);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}



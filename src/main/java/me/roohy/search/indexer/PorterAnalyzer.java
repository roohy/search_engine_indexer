package me.roohy.search.indexer;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;

public class PorterAnalyzer extends Analyzer {
	
	public TokenStream tokenStream(String fieldName, Reader reader) {
		LowerCaseTokenizer tknz = new LowerCaseTokenizer(reader);
//		stopFilter	.setEnablePositionIncrements(true);
		return new PorterStemFilter(tknz);
	}
}


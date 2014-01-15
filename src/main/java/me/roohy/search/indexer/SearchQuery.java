package me.roohy.search.indexer;

import java.io.Serializable;


public final class SearchQuery implements Serializable {
	private static final long serialVersionUID = -6746759309992037605L;

	Boolean accepted;
	Integer views;
	String url;
	String q;
	Integer fromDate, toDate;

	public SearchQuery(Boolean accepted, Integer views, String url, String q, Integer fromDate,
			Integer toDate) {
		this.accepted = accepted;
		this.views = views;
		this.url = url;
		this.q = q;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public Integer getViews() {
		return views;
	}

	public String getUrl() {
		return url;
	}

	public String getQ() {
		return q;
	}


	public Integer getFromDate() {
		return fromDate;
	}

	public Integer getToDate() {
		return toDate;
	}
}
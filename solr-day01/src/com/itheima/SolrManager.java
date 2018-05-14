package com.itheima;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solr��CRUD
 * 
 * @author qiuda
 *
 */
public class SolrManager {

	@Test
	public void solrAdd() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8585/solr/collection1");
		for (int i = 11; i < 22; i++) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", i + ""); // solr�����Զ�����id��
			doc.addField("name", "solr��ӱ���" + i);
			doc.addField("content", "solr�������" + i);
			solrServer.add(doc);
		}
		solrServer.commit();
	}

	@Test
	public void solrUpdate() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8585/solr/collection1");
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "1"); // solr��ľ�и��µķ��� ֻ�и���
		doc.addField("name", "solr�޸ĺ����ӱ���");
		doc.addField("content", "solr�޸ĺ���������");
		solrServer.add(doc);
		solrServer.commit();
	}

	@Test
	public void solrDelete() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8585/solr/collection1");
		SolrInputDocument doc = new SolrInputDocument();
		/* solrServer.deleteByQuery(query) */
		// doc.clear();
		// solrServer.deleteById("name:solr��ӱ���3");
		solrServer.deleteById("20");
		solrServer.deleteById("21");
		solrServer.commit();
	}

	@Test
	public void solrQuery() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://localhost:8585/solr/collection1");
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "solr");
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList documentList = queryResponse.getResults();

		System.out.println("������Ϊ" + documentList.getNumFound()); // ��ӡ������

		for (SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("name"));
			System.out.println(solrDocument.get("content"));
		}
		solrServer.commit();
	}
}

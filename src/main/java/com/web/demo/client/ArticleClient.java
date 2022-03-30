package com.web.demo.client;

import com.web.demo.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ArticleClient extends WebServiceGatewaySupport  {

    private String url = "http://localhost:8082/soapws";
    public GetArticleByIdResponse getArticleById(long articleId) {
        GetArticleByIdRequest request = new GetArticleByIdRequest();
        request.setArticleId(articleId);
        GetArticleByIdResponse response = (GetArticleByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(url+"/getArticleByIdRequest"));
        return response;
    }
    public GetAllArticlesResponse getAllArticles() {
        GetAllArticlesRequest request = new GetAllArticlesRequest();
        GetAllArticlesResponse response = (GetAllArticlesResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(url+"/getAllArticlesRequest"));
        return response;
    }
    public AddArticleResponse addArticle(String title, String category) {
        AddArticleRequest request = new AddArticleRequest();
        request.setTitle(title);
        request.setCategory(category);
        AddArticleResponse response = (AddArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(url+"/addArticleRequest"));
        return response;
    }
    public UpdateArticleResponse updateArticle(ArticleInfo articleInfo) {
        UpdateArticleRequest request = new UpdateArticleRequest();
        request.setArticleInfo(articleInfo);
        UpdateArticleResponse response = (UpdateArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(url+"/updateArticleRequest"));
        return response;
    }
    public DeleteArticleResponse deleteArticle(long articleId) {
        DeleteArticleRequest request = new DeleteArticleRequest();
        request.setArticleId(articleId);
        DeleteArticleResponse response = (DeleteArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(url+"/deleteArticleRequest"));
        return response;
    }
}

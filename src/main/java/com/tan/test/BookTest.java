package com.tan.test;

/**
 * Created by xuhao on 15/7/24.
 */
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class BookTest {
    private String url = "http://localhost:8088/book/operation";

    @Test
    public void testDelete() {
        Client client = Client.create();
        WebResource webResource = client.resource(url + "/delete/1");
        ClientResponse response = webResource.delete(ClientResponse.class);

        System.out.println("Response for delete request: " + response.getStatus());
    }

    @Test
    public void testPut() {
        Client client = Client.create();
        WebResource webResource = client.resource(url + "/update");
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("id", "2");
        queryParams.add("name", "nametest");
        queryParams.add("author", "authortest");
        ClientResponse response = webResource.queryParams(queryParams).put(ClientResponse.class, "foo:test");
        System.out.println("Response for put request: " + response.getStatus());
    }
}


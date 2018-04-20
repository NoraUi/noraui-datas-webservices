/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.datas.web.services.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import com.github.noraui.data.rest.DataModel;
import com.google.gson.Gson;

/**
 * Basic integration tests for NoraUiDatasControllerController.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class NoraUiDatasControllerTests extends AbstractTestNGSpringContextTests {

    @LocalServerPort
    private int port;

    @Test
    public void getHelloColumns() {
        ResponseEntity<DataModel> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/hello/columns", DataModel.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getColumns().size()).isEqualTo(7);
        assertThat(entity.getBody().getColumns().get(0)).isEqualTo("author");
        assertThat(entity.getBody().getColumns().get(1)).isEqualTo("zip");
        assertThat(entity.getBody().getColumns().get(2)).isEqualTo("city");
        assertThat(entity.getBody().getColumns().get(3)).isEqualTo("element");
        assertThat(entity.getBody().getColumns().get(4)).isEqualTo("element2");
        assertThat(entity.getBody().getColumns().get(5)).isEqualTo("date");
        assertThat(entity.getBody().getColumns().get(6)).isEqualTo("title");
    }

    @Test
    public void getHelloColumnsError() {
        ResponseEntity<DataModel> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/fake/columns", DataModel.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void getHelloNbLines() {
        ResponseEntity<Integer> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/hello/nbLines", Integer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(8);
    }

    @Test
    public void getHelloReadValue() {
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/hello/column/1/line/1", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Jenkins T1");
    }

    @Test
    public void getHelloReadValueOnResult() {
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/hello/column/8/line/1", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void getHelloReadLine() {
        ResponseEntity<DataModel> entity = new RestTemplate().getForEntity("http://localhost:" + port + "/noraui/api/hello/line/1", DataModel.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getRows().size()).isEqualTo(1);
        assertThat(entity.getBody().getRows().get(0).getColumns().get(0)).isEqualTo("Jenkins T1");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(1)).isEqualTo("35000");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(2)).isEqualTo("Rennes");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(3)).isEqualTo("smile");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(4)).isEqualTo("smile");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(5)).isEqualTo("16/01/2020");
        assertThat(entity.getBody().getRows().get(0).getColumns().get(6)).isEqualTo("");
        assertThat(entity.getBody().getRows().get(0).getResult()).isEqualTo(null);
    }

    @Test
    public void getHelloWriteValue() {
        int column = 6;
        int line = 1;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        DataModel dataModel = restTemplate.patchForObject("http://localhost:" + port + "/noraui/api/hello/column/" + column + "/line/" + line, "MY TITLE", DataModel.class);
        System.out.println(new Gson().toJson(dataModel));
        assertThat(dataModel.getRows().get(line - 1).getColumns().get(column - 1)).isEqualTo("MY TITLE");
    }

    @Test
    public void getHelloWriteValueOnResult() {
        int column = 7;
        int line = 1;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        DataModel dataModel = restTemplate.patchForObject("http://localhost:" + port + "/noraui/api/hello/column/" + column + "/line/" + line, "OK", DataModel.class);
        assertThat(dataModel.getRows().get(line - 1).getResult()).isEqualTo("OK");
    }

}

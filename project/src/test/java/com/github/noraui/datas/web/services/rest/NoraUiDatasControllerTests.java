/* Copyright (c) 2017 NoraUi Organization https://github.com/NoraUi
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.noraui.datas.web.services.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import noraui.data.rest.DataModel;

/**
 * @author sgrillon
 *         Basic integration tests for NoraUiDatasControllerController.
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
        int column = 7;
        int line = 1;
        DataModel dataModel = new TestRestTemplate().patchForObject("http://localhost:" + port + "/noraui/api/hello/column/" + column + "/line/" + line, "MY TITLE", DataModel.class);
        assertThat(dataModel.getRows().get(line - 1).getColumns().get(column - 1)).isEqualTo("MY TITLE");
    }

    @Test
    public void getHelloWriteValueOnResult() {
        int column = 8;
        int line = 1;
        DataModel dataModel = new TestRestTemplate().patchForObject("http://localhost:" + port + "/noraui/api/hello/column/" + column + "/line/" + line, "OK", DataModel.class);
        assertThat(dataModel.getRows().get(line - 1).getResult()).isEqualTo("OK");
    }

}

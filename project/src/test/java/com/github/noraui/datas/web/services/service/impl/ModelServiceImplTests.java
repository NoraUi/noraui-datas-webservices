/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.datas.web.services.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.github.noraui.data.rest.DataModel;
import com.github.noraui.datas.web.services.service.ModelService;

/**
 * Basic unit tests for ModelService.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ModelServiceImplTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private ModelService modelService;

    @Test
    public void getHelloColumns() {
        DataModel actualDataModel = modelService.getColumns("hello");
        assertThat(actualDataModel.getColumns().size()).isEqualTo(7);
        assertThat(actualDataModel.getColumns().get(0)).isEqualTo("author");
        assertThat(actualDataModel.getColumns().get(1)).isEqualTo("zip");
        assertThat(actualDataModel.getColumns().get(2)).isEqualTo("city");
        assertThat(actualDataModel.getColumns().get(3)).isEqualTo("element");
        assertThat(actualDataModel.getColumns().get(4)).isEqualTo("element2");
        assertThat(actualDataModel.getColumns().get(5)).isEqualTo("date");
        assertThat(actualDataModel.getColumns().get(6)).isEqualTo("title");
    }

    @Test
    public void getHelloColumnsError() {
        DataModel actualDataModel = modelService.getColumns("fake");
        assertThat(actualDataModel).isNull();
    }
    
    @Test
    public void readLineHeader() {
        DataModel actualDataModel = modelService.readLine("hello", 0);
        
        assertThat(actualDataModel.getColumns().size()).isEqualTo(7);
        assertThat(actualDataModel.getColumns().get(0)).isEqualTo("author");
        assertThat(actualDataModel.getColumns().get(1)).isEqualTo("zip");
        assertThat(actualDataModel.getColumns().get(2)).isEqualTo("city");
        assertThat(actualDataModel.getColumns().get(3)).isEqualTo("element");
        assertThat(actualDataModel.getColumns().get(4)).isEqualTo("element2");
        assertThat(actualDataModel.getColumns().get(5)).isEqualTo("date");
        assertThat(actualDataModel.getColumns().get(6)).isEqualTo("title");
        
        assertThat(actualDataModel.getRows()).isNull();
    }

    @Test
    public void readLine() {
        DataModel actualDataModel = modelService.readLine("hello", 8);
        
        assertThat(actualDataModel.getColumns().size()).isEqualTo(7);
        assertThat(actualDataModel.getColumns().get(0)).isEqualTo("author");
        assertThat(actualDataModel.getColumns().get(1)).isEqualTo("zip");
        assertThat(actualDataModel.getColumns().get(2)).isEqualTo("city");
        assertThat(actualDataModel.getColumns().get(3)).isEqualTo("element");
        assertThat(actualDataModel.getColumns().get(4)).isEqualTo("element2");
        assertThat(actualDataModel.getColumns().get(5)).isEqualTo("date");
        assertThat(actualDataModel.getColumns().get(6)).isEqualTo("title");
        
        assertThat(actualDataModel.getRows().get(0).getColumns().size()).isEqualTo(7);
        assertThat(actualDataModel.getRows().get(0).getColumns().get(0)).isEqualTo("Jenkins T8");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(1)).isEqualTo("");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(2)).isEqualTo("Rennes");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(3)).isEqualTo("smile");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(4)).isEqualTo("smile");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(5)).isEqualTo("");
        assertThat(actualDataModel.getRows().get(0).getColumns().get(6)).isEqualTo("");
        assertThat(actualDataModel.getRows().get(0).getErrorStepIndex()).isEqualTo(54);
        assertThat(actualDataModel.getRows().get(0).getResult()).isNull();
    }

    @Test
    public void readLineError() {
        DataModel actualDataModel = modelService.readLine("hello", 9);
        assertThat(actualDataModel).isNull();
    }

    @Test
    public void readValue() {
        String actual = modelService.readValue("hello", 1, 8);
        assertThat(actual).isEqualTo("Jenkins T8");
    }

    @Test
    public void readValueColumnError() {
        String actual = modelService.readValue("hello", 9, 8);
        assertThat(actual).isNull();
    }

    @Test
    public void readValueLineError() {
        String actual = modelService.readValue("hello", 1, 9);
        assertThat(actual).isNull();
    }

}

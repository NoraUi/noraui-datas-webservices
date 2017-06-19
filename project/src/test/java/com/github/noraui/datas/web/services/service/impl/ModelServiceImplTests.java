/*
 * Copyright (c) 2017 NoraUi Organization https://github.com/NoraUi
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the <organization> nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
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
package com.github.noraui.datas.web.services.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.github.noraui.datas.web.services.service.ModelService;

import noraui.data.rest.DataModel;

/**
 * @author sgrillon
 *         Basic unit tests for ModelService.
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

}

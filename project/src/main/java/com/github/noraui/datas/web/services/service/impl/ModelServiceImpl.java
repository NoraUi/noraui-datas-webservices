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
package com.github.noraui.datas.web.services.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.noraui.datas.web.services.service.ModelService;

import noraui.data.rest.DataModel;
import noraui.data.rest.Row;

/**
 * @author sgrillon
 */
@Component
public class ModelServiceImpl implements ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

    private DataModel hello;

    public ModelServiceImpl() {
        hello = new DataModel();
        List<String> columns = new ArrayList<>();
        columns.add("author");
        columns.add("zip");
        columns.add("city");
        columns.add("element");
        columns.add("element2");
        columns.add("date");
        columns.add("title");
        hello.setColumns(columns);
        List<Row> rows = new ArrayList<>();
        rows.add(new Row(Arrays.asList("Jenkins T1", "35000", "Rennes", "smile", "smile", "16/01/2020", "")));
        rows.add(new Row(Arrays.asList("Jenkins T2", "75000", "Paris", "smile", "smile", "", ""), 24));
        rows.add(new Row(Arrays.asList("Jenkins T3", "56100", "Lorient", "smile", "smile", "", ""), 18));
        rows.add(new Row(Arrays.asList("Jenkins T4", "35000", "Rennes", "noExistElement", "smile", "", "")));
        rows.add(new Row(Arrays.asList("Jenkins T5", "35000", "Rennes", "smile", "smile", "", ""), 29));
        rows.add(new Row(Arrays.asList("Jenkins T6", "35000", "", "", "", "", ""), 2));
        rows.add(new Row(Arrays.asList("Jenkins T7", "35000", "Rennes", "", "", "", ""), 3));
        rows.add(new Row(Arrays.asList("Jenkins T8", "", "Rennes", "smile", "smile", "", ""), 44));
        hello.setRows(rows);
    }

    @Override
    public DataModel getAll(String model) {
        if ("hello".equals(model)) {
            return hello;
        }
        return null;
    }

    @Override
    public DataModel getColumns(String model) {
        if ("hello".equals(model)) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(hello.getColumns());
            return dataModel;
        }
        return null;
    }

    @Override
    public Integer getNbLines(String model) {
        if ("hello".equals(model)) {
            return hello.getRows().size();
        }
        return 0;
    }

    @Override
    public String readValue(String model, int colIndex, int line) {
        if ("hello".equals(model)) {
            if (colIndex - 1 == hello.getRows().get(line - 1).getColumns().size()) {
                int esi = hello.getRows().get(line - 1).getErrorStepIndex();
                if (esi != -1) {
                    return String.valueOf(esi);
                }
            } else {
                return hello.getRows().get(line - 1).getColumns().get(colIndex - 1);
            }
        }
        return null;
    }

    @Override
    public DataModel readLine(String model, int line) {
        if ("hello".equals(model)) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(hello.getColumns());
            dataModel.setRows(Arrays.asList(new Row(hello.getRows().get(line - 1).getColumns(), hello.getRows().get(line - 1).getErrorStepIndex())));
            return dataModel;
        }
        return null;
    }

    @Override
    public DataModel writeValue(String model, int colIndex, int line, String value) {
        if ("hello".equals(model)) {
            if (colIndex - 1 == hello.getRows().get(line - 1).getColumns().size()) {
                hello.getRows().get(line - 1).setResult(value);
            } else {
                hello.getRows().get(line - 1).getColumns().set(colIndex - 1, value);
            }
            return hello;
        }
        return null;
    }

}

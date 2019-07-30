/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.datas.web.services.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.noraui.data.rest.DataModel;
import com.github.noraui.data.rest.Row;
import com.github.noraui.datas.web.services.service.ModelService;

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
        rows.add(new Row(Arrays.asList("Jenkins T2", "75000", "Paris", "smile", "smile", "", ""), 30));
        rows.add(new Row(Arrays.asList("Jenkins T3", "56100", "Lorient", "smile", "smile", "", ""), 24));
        rows.add(new Row(Arrays.asList("Jenkins T4", "35000", "Rennes", "noExistElement", "smile", "", "")));
        rows.add(new Row(Arrays.asList("Jenkins T5", "35000", "Rennes", "smile", "smile", "", ""), 38));
        rows.add(new Row(Arrays.asList("Jenkins T6", "35000", "", "", "", "", ""), 2));
        rows.add(new Row(Arrays.asList("Jenkins T7", "35000", "Rennes", "", "", "", ""), 4));
        rows.add(new Row(Arrays.asList("Jenkins T8", "", "Rennes", "smile", "smile", "", ""), 54));
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
        if ("hello".equals(model) && hello.getRows().size() > line - 1 ) {
            if (colIndex - 1 == hello.getRows().get(line - 1).getColumns().size()) {
                int esi = hello.getRows().get(line - 1).getErrorStepIndex();
                if (esi != -1) {
                    return String.valueOf(esi);
                }
            } else if (colIndex - 1 < hello.getRows().get(line - 1).getColumns().size()) {
                return hello.getRows().get(line - 1).getColumns().get(colIndex - 1);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public DataModel readLine(String model, int line) {
        if ("hello".equals(model) && hello.getRows().size() > line - 1) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(hello.getColumns());
            dataModel.setRows(Arrays.asList(new Row(hello.getRows().get(line - 1).getColumns(), hello.getRows().get(line - 1).getErrorStepIndex(), hello.getRows().get(line - 1).getResult())));
            return dataModel;
        }
        return null;
    }

    @Override
    public DataModel writeValue(String model, int colIndex, int line, String value) {
        if ("hello".equals(model)) {
            if (colIndex == hello.getRows().get(line - 1).getColumns().size()) {
                hello.getRows().get(line - 1).setResult(value);
            } else {
                hello.getRows().get(line - 1).getColumns().set(colIndex - 1, value);
            }
            return hello;
        }
        return null;
    }

}

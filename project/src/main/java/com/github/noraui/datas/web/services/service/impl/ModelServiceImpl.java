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
    private DataModel bonjour;
    private DataModel blog;

    public ModelServiceImpl() {
        hello = new DataModel();
        List<String> helloColumns = new ArrayList<>();
        helloColumns.add("author");
        helloColumns.add("zip");
        helloColumns.add("city");
        helloColumns.add("element");
        helloColumns.add("element2");
        helloColumns.add("date");
        helloColumns.add("title");
        hello.setColumns(helloColumns);

        bonjour = new DataModel();
        List<String> bonjourColumns = new ArrayList<>();
        bonjourColumns.add("auteur");
        bonjourColumns.add("codepostal");
        bonjourColumns.add("ville");
        bonjourColumns.add("élément");
        bonjourColumns.add("élément2");
        bonjourColumns.add("date");
        bonjourColumns.add("titre");
        bonjour.setColumns(bonjourColumns);
        
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
        bonjour.setRows(rows);
        
        blog = new DataModel();
        List<String> blogColumns = new ArrayList<>();
        blogColumns.add("Title");
        blogColumns.add("Text");
        blogColumns.add("Author");
        blogColumns.add("Note");
        blog.setColumns(blogColumns);
        
        List<Row> blogRows = new ArrayList<>();
        blogRows.add(new Row(Arrays.asList("Blog 1", "Article 1", "text 1", "Peter", "7")));
        blogRows.add(new Row(Arrays.asList("Blog 1", "Article 2", "text 2", "Peter", "10")));
        blogRows.add(new Row(Arrays.asList("Blog 2", "Article 3", "text 3", "Peter", "8")));
        blogRows.add(new Row(Arrays.asList("", "", "", "", "")));
        blogRows.add(new Row(Arrays.asList("Blog 2", "Article 4", "text 4", "anonymous", "2"),3));
        blogRows.add(new Row(Arrays.asList("Blog 1", "Article 5", "text 5", "Peter", "9")));
        
        blog.setRows(blogRows);
    }

    @Override
    public DataModel getAll(String model) {
        if ("hello".equals(model)) {
            return hello;
        } else if ("bonjour".equals(model)) {
            return bonjour;
        } else if ("blog".equals(model)) {
            return blog;
        }
        return null;
    }

    @Override
    public DataModel getColumns(String model) {
        if ("hello".equals(model)) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(hello.getColumns());
            return dataModel;
        } else if ("bonjour".equals(model)) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(bonjour.getColumns());
            return dataModel;
        } else if ("blog".equals(model)) {
            DataModel dataModel = new DataModel();
            dataModel.setColumns(blog.getColumns());
            return dataModel;
        }
        return null;
    }

    @Override
    public Integer getNbLines(String model) {
        if ("hello".equals(model) || "bonjour".equals(model)) {
            return hello.getRows().size();
        } else if ("blog".equals(model)) {
            return blog.getRows().size();
        }
        return 0;
    }

    @Override
    public String readValue(String model, int colIndex, int line) {
        if (("hello".equals(model) || "bonjour".equals(model)) && hello.getRows().size() > line - 1) {
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
        } else if ("blog".equals(model)  && blog.getRows().size() > line - 1) {
            if (colIndex - 1 == blog.getRows().get(line - 1).getColumns().size()) {
                int esi = blog.getRows().get(line - 1).getErrorStepIndex();
                if (esi != -1) {
                    return String.valueOf(esi);
                }
            } else if (colIndex - 1 < blog.getRows().get(line - 1).getColumns().size()) {
                return blog.getRows().get(line - 1).getColumns().get(colIndex - 1);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public DataModel readLine(String model, int line) {
        if (("hello".equals(model) || "bonjour".equals(model)) && hello.getRows().size() > line - 1) {
            DataModel dataModel = new DataModel();
            if ("hello".equals(model)) {
                dataModel.setColumns(hello.getColumns());
            } else if ("bonjour".equals(model)) {
                dataModel.setColumns(bonjour.getColumns());
            }
            dataModel.setRows(Arrays.asList(new Row(hello.getRows().get(line - 1).getColumns(), hello.getRows().get(line - 1).getErrorStepIndex(), hello.getRows().get(line - 1).getResult())));
            return dataModel;
        } else if ("blog".equals(model)  && blog.getRows().size() > line - 1) {
            DataModel dataModel = new DataModel();
            if ("blog".equals(model)) {
                dataModel.setColumns(blog.getColumns());
            } else if ("blog".equals(model)) {
                dataModel.setColumns(blog.getColumns());
            }
            dataModel.setRows(Arrays.asList(new Row(blog.getRows().get(line - 1).getColumns(), blog.getRows().get(line - 1).getErrorStepIndex(), blog.getRows().get(line - 1).getResult())));
            return dataModel;
        }
        return null;
    }

    @Override
    public DataModel writeValue(String model, int colIndex, int line, String value) {
        if ("hello".equals(model) || "bonjour".equals(model)) {
            if (colIndex == hello.getRows().get(line - 1).getColumns().size()) {
                hello.getRows().get(line - 1).setResult(value);
            } else {
                hello.getRows().get(line - 1).getColumns().set(colIndex - 1, value);
            }
            return hello;
        } else if ("blog".equals(model) ) {
            if (colIndex == blog.getRows().get(line - 1).getColumns().size()) {
                blog.getRows().get(line - 1).setResult(value);
            } else {
                blog.getRows().get(line - 1).getColumns().set(colIndex - 1, value);
            }
            return blog;
        }
        return null;
    }

}

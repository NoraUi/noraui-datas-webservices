/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.datas.web.services.service;

import com.github.noraui.data.rest.DataModel;

public interface ModelService {

    DataModel getAll(String model);

    DataModel getColumns(String model);

    Integer getNbLines(String model);

    String readValue(String model, int colIndex, int line);

    DataModel writeValue(String model, int colIndex, int line, String value);

    DataModel readLine(String model, int line);

}

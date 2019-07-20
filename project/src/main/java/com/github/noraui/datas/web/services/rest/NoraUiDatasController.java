/**
 * NoraUi is licensed under the license GNU AFFERO GENERAL PUBLIC LICENSE
 * 
 * @author Nicolas HALLOUIN
 * @author Stéphane GRILLON
 */
package com.github.noraui.datas.web.services.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.noraui.data.rest.DataModel;
import com.github.noraui.datas.web.services.service.ModelService;

@Controller
@RequestMapping("/noraui/api")
public class NoraUiDatasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoraUiDatasController.class);

    @Autowired
    private ModelService modelService;

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/columns", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DataModel> getColumnsModel(@PathVariable String model) {
        LOGGER.info("getColumnsModel : model[{}]", model);
        DataModel dataModel = modelService.getColumns(model);
        return Optional.ofNullable(dataModel).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/nbLines", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNbLines(@PathVariable String model) {
        LOGGER.info("getColumnsModel : model[{}]", model);
        Integer nbLines = modelService.getNbLines(model);
        return Optional.ofNullable(nbLines).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/column/{colIndex}/line/{line}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> readValue(@PathVariable String model, @PathVariable int colIndex, @PathVariable int line) {
        LOGGER.info("readValue : model[{}{}{}]", model, colIndex, line);
        String value = modelService.readValue(model, colIndex, line);
        return Optional.ofNullable(value).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/column/{colIndex}/line/{line}", method = RequestMethod.PATCH, consumes = MediaType.ALL_VALUE,
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DataModel> writeValue(@RequestBody String value, @PathVariable String model, @PathVariable int colIndex, @PathVariable int line) {
        LOGGER.info("writeValue : model[{}{}{}]", model, colIndex, line);
        DataModel dataModel = modelService.writeValue(model, colIndex, line, value);
        return ResponseEntity.ok().body(dataModel);
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/line/{line}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DataModel> readLine(@PathVariable String model, @PathVariable int line) {
        LOGGER.info("getColumnsModel : model[{}{}]", model, line);
        DataModel dataModel = modelService.readLine(model, line);
        return Optional.ofNullable(dataModel).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}

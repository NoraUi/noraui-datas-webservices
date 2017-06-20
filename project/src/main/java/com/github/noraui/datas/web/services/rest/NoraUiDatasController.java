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

import com.github.noraui.datas.web.services.service.ModelService;

import noraui.data.rest.DataModel;

/**
 * @author sgrillon
 */
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
        LOGGER.debug("getColumnsModel : model[{}]", model);
        DataModel dataModel = modelService.getColumns(model);
        return Optional.ofNullable(dataModel).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/nbLines", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNbLines(@PathVariable String model) {
        LOGGER.debug("getColumnsModel : model[{}]", model);
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
        LOGGER.debug("readValue : model[{}{}{}]", model, colIndex, line);
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
        LOGGER.debug("writeValue : model[{}{}{}]", model, colIndex, line);
        DataModel dataModel = modelService.writeValue(model, colIndex, line, value);
        return ResponseEntity.ok().body(dataModel);
    }

    /**
     * @param model
     * @return all columns of model
     */
    @RequestMapping(value = "/{model}/line/{line}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DataModel> readLine(@PathVariable String model, @PathVariable int line) {
        LOGGER.debug("getColumnsModel : model[{}{}]", model, line);
        DataModel dataModel = modelService.readLine(model, line);
        return Optional.ofNullable(dataModel).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}

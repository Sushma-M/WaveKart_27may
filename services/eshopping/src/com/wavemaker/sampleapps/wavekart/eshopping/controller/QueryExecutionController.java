/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.wavemaker.sampleapps.wavekart.eshopping.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.wavemaker.sampleapps.wavekart.eshopping.service.EshoppingQueryExecutorService;
import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;
import com.wordnik.swagger.annotations.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController(value = "Eshopping.QueryExecutionController")
@RequestMapping("/eshopping/queryExecutor")
@Api(description = "Controller class for query execution", value = "QueryExecutionController")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private EshoppingQueryExecutorService queryService;

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/_Total_Price", method = RequestMethod.GET)
    public Page<Object> execute_Total_Price(@RequestParam(value = "data", required = false) java.lang.String data, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query _Total_Price");
        Page<Object> result = queryService.execute_Total_Price(pageable, data);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/_Total_InCart", method = RequestMethod.GET)
    public Page<Object> execute_Total_InCart(Pageable pageable) {
        LOGGER.debug("Executing named query _Total_InCart");
        Page<Object> result = queryService.execute_Total_InCart(pageable);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/_OrderedLists", method = RequestMethod.GET)
    public Page<Object> execute_OrderedLists(@RequestParam(value = "LoggedinUserID", required = false) java.lang.Integer LoggedinUserID, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query _OrderedLists");
        Page<Object> result = queryService.execute_OrderedLists(pageable, LoggedinUserID);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @RequestMapping(value = "/queries/wm_custom", method = RequestMethod.POST)
    @ApiOperation(value = "Process request to execute customer queries")
    public Page<Object> executeWMCustomQuery(@RequestBody CustomQuery query, Pageable pageable) {
        Page<Object> result = queryService.executeWMCustomQuerySelect(query, pageable);
        LOGGER.debug("got the result {}" + result);
        return result;
    }

    @RequestMapping(value = "/queries/Ordered", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Process request to execute queries")
    public int executeOrdered(@RequestParam(value = "data", required = false) java.lang.String data, @RequestParam(value = "data2", required = false) java.lang.String data2) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query Ordered");
        int result = queryService.executeOrdered(data, data2);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @RequestMapping(value = "/queries/cancelled", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Process request to execute queries")
    public int executeCancelled(@RequestParam(value = "data", required = false) java.lang.String data, @RequestParam(value = "data1", required = false) java.lang.Integer data1) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query cancelled");
        int result = queryService.executeCancelled(data, data1);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @RequestMapping(value = "/queries/wm_custom_update", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Process request to execute customer queries")
    public int executeWMCustomQuery(@RequestBody CustomQuery query) {
        int result = queryService.executeWMCustomQueryUpdate(query);
        LOGGER.debug("got the result {}" + result);
        return result;
    }
}

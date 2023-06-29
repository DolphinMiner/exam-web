package com.whc.webapi;


import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.service.QueryService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 13:22
 * @Description:
 */
@RestController
public class ServiceController {

    QueryService queryService;

    public ServiceController(QueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * add a piece of data to database
     */
    @RequestMapping(value = "/query/", method = RequestMethod.POST)
    public ServiceResponse put(@RequestBody ServiceRequest request) {
        return null;
    }

    /**
     * delete specific id data
     */
    @RequestMapping(value = "/query/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {

        return "hello";
    }

    /**
     * modify specific id data
     */
    @RequestMapping(value = "/query/{id}", method = RequestMethod.PUT)
    public String modify(@PathVariable("id") int id, @RequestBody ServiceRequest data) {

        return "hello";
    }

    /**
     * query specific id data
     */
    @RequestMapping(value = "/query/{queryType}/{id}", method = RequestMethod.GET)
    public ServiceResponse query(@PathVariable("queryType") int queryType , @PathVariable("id") long id) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        return queryService.process(serviceRequest, queryType);
    }


}



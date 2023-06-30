package com.whc.webapi;


import com.whc.data.dto.ModifyContentRequest;
import com.whc.data.dto.ModifyContentResponse;
import com.whc.data.dto.ServiceRequest;
import com.whc.data.dto.ServiceResponse;
import com.whc.service.business.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 13:22
 * @Description:
 */
@RestController
public class ServiceController {

    QueryService queryService;
    DeleteService deleteService;
    InsertService insertService;
    UpdateService updateService;

    ModifyContentService modifyContentService;

    public ServiceController(QueryService queryService,
                             DeleteService deleteService,
                             InsertService insertService,
                             UpdateService updateService) {
        this.queryService = queryService;
        this.deleteService = deleteService;
        this.insertService = insertService;
        this.updateService = updateService;
    }

    /**
     * insert a piece of data
     *
     * @param request request
     * @return response
     */
    @RequestMapping(value = "/insert/", method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody ServiceRequest request) {
        return insertService.process(request);
    }

    /**
     * delete
     *
     * @param deleteType 1 delete specific id
     * @param id         id
     * @return result
     */
    @RequestMapping(value = "/delete/{deleteType}/{id}", method = RequestMethod.DELETE)
    public ServiceResponse delete(@PathVariable("deleteType") int deleteType, @PathVariable("id") int id) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        return deleteService.process(serviceRequest, deleteType);
    }

    /**
     * update
     *
     * @param updateType 1 update specific id
     * @param request    request
     * @return result
     */
    @RequestMapping(value = "/update/{updateType}", method = RequestMethod.PUT)
    public ServiceResponse update(@PathVariable("updateType") int updateType, @RequestBody ServiceRequest request) {
        return updateService.process(request, updateType);
    }


    /**
     * query
     *
     * @param queryType 1 query specific id, 0 query all
     * @param id        id
     * @return result
     */
    @RequestMapping(value = "/query/{queryType}/{id}", method = RequestMethod.GET)
    public ServiceResponse query(@PathVariable("queryType") int queryType, @PathVariable("id") long id) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        return queryService.process(serviceRequest, queryType);
    }

    /**
     * modify incorrect words in English text
     * @param request
     * @return
     */
    @RequestMapping(value =  "/modifyContent/", method = RequestMethod.POST)
    public ModifyContentResponse modifyContent(@RequestBody ModifyContentRequest request){
        return modifyContentService.process(request);
    }



}



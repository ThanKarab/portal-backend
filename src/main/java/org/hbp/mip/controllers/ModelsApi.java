/**
 * Created by mirco on 04.12.15.
 */

package org.hbp.mip.controllers;


import io.swagger.annotations.*;
import org.hbp.mip.MIPApplication;
import org.hbp.mip.model.Model;
import org.hbp.mip.model.Query;
import org.hbp.mip.model.User;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/models", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/models", description = "the models API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-01-07T07:38:20.227Z")
public class ModelsApi {


    @ApiOperation(value = "Get models", notes = "", response = Model.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")})
    @RequestMapping(value = "", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Model>> getModels(
            @ApiParam(value = "Max number of results") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Only ask own models") @RequestParam(value = "own", required = false) Boolean own,
            @ApiParam(value = "Only ask models from own team") @RequestParam(value = "team", required = false) Boolean team,
            @ApiParam(value = "Only ask valid models") @RequestParam(value = "valid", required = false) Boolean valid,
            Principal principal) throws NotFoundException {

        User user = MIPApplication.getUser(principal);

        String queryString = "select m from Model m, User u where m.createdBy=u.id";

        if(own != null)
        {
            if(own)
            {
                queryString += " and u.username= :username";
            }
            else
            {
                queryString += " and u.username!= :username";
            }
        }

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        org.hibernate.Query query = session.createQuery(queryString);
        if(own != null)
        {
            query.setString("username", user.getUsername());
        }
        List<Model> models = query.list();
        session.getTransaction().commit();
        return new ResponseEntity<List<Model>>(HttpStatus.OK).ok(models);
    }


    @ApiOperation(value = "Create a model", notes = "", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Model created")})
    @RequestMapping(value = "", produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<Void> addAModel(
            @RequestBody @ApiParam(value = "Model to create", required = true) Model model, Principal principal) throws NotFoundException {
        User user = MIPApplication.getUser(principal);

        model.setSlug(model.getTitle().toLowerCase());
        model.setValid(true);
        model.setCreatedBy(user);
        model.setCreatedAt(new Date());
        Query q = new Query();
        Query qIn = model.getQuery();
        q.setRequest(qIn.getRequest());
        //q.setVariables(qIn.getVariables());
        q.setCovariables(qIn.getCovariables());
        q.setGrouping(qIn.getGrouping());
        q.setFilters(qIn.getFilters());
        model.setQuery(q);

        System.out.println(model);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "Get a model", notes = "", response = Model.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found"),
            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = "/{slug}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Model> getAModel(
            @ApiParam(value = "slug", required = true) @PathVariable("slug") String slug) throws NotFoundException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        org.hibernate.Query query = session.createQuery("from Model where slug= :slug");
        query.setString("slug", slug);
        Model model = (Model) query.uniqueResult();
        session.getTransaction().commit();
        return new ResponseEntity<Model>(HttpStatus.OK).ok(model);
    }


    @ApiOperation(value = "Update a model", notes = "", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Model updated")})
    @RequestMapping(value = "/{slug}", produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAModel(
            @ApiParam(value = "slug", required = true) @PathVariable("slug") String slug,
            @RequestBody @ApiParam(value = "Model to update", required = true) Model model) throws NotFoundException {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete a model", notes = "", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Model deleted")})
    @RequestMapping(value = "/{slug}", produces = {"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAModel(
            @ApiParam(value = "slug", required = true) @PathVariable("slug") String slug) throws NotFoundException {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
